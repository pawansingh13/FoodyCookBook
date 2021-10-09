package com.app.foodycookbook.feature.search;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;
import com.app.foodycookbook.apputils.BaseUtils;
import com.app.foodycookbook.baseui.BaseActivity;
import com.app.foodycookbook.baseui.BaseViewModelFactory;
import com.app.foodycookbook.feature.meal.Meals;
import com.app.foodycookbook.feature.meal.MealsAdapter;
import com.app.foodycookbook.feature.mealdetails.MealDetailsActivity;
import com.app.foodycookbook.listeners.ItemClickListener;
import com.app.foodycookbook.networking.Resource;
import com.app.foodycookbook.utills.Const;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends BaseActivity implements ItemClickListener {

    private EditText mEtSearch;
    private ArrayList<Meals.Meal> meals = new ArrayList<>();
    private SearchViewModel searchViewModel;
    @Inject
    BaseViewModelFactory mBaseViewModelFactory;
    MealsAdapter mealsAdapter;
    LinearLayoutManager linearLayoutManager;
    CompositeDisposable disposable = new CompositeDisposable();
    RecyclerView mRvSearchMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initializeView();
    }

    private void setAdapter() {
        mealsAdapter = new MealsAdapter(this, this);
        linearLayoutManager = new LinearLayoutManager(this);
        mRvSearchMeals.setLayoutManager(linearLayoutManager);
        mRvSearchMeals.setAdapter(mealsAdapter);
    }

    private void initializeView() {
        setUpToolbar(true, false, true, false);
        mEtSearch = findViewById(R.id.et_search);
        mRvSearchMeals = findViewById(R.id.rv_search_meals);
        FoodyCookBookApplication.getApp().getDaggerAppComponent().provideIn(this);
        searchViewModel = ViewModelProviders.of(this, mBaseViewModelFactory).get(SearchViewModel.class);
        setAdapter();
        observeData();
        search();

    }

    private void search() {
        disposable.add(RxTextView.textChangeEvents(mEtSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchMoodsTextWatcher()));
    }

    private DisposableObserver<TextViewTextChangeEvent> searchMoodsTextWatcher() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                doSearch(textViewTextChangeEvent.getText().toString().trim());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void doSearch(String searchKeyword) {
        if (!BaseUtils.checkNetwork(this)) {
            View.OnClickListener onOkClickListener = v -> {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                dismissMessageAlertDialog();
            };
            showAlertMessageDialog(getString(R.string.ALERT_MESAAGE), getResources().getString(R.string.please_check_your_network_connection), onOkClickListener, getString(R.string.OK), getString(R.string.CANCEL), null);
        } else {
            searchViewModel.doSearch(searchKeyword);
        }
    }

    private void observeData() {
        searchViewModel.getSearchMeals().observe(this, mealsModelResource ->
                getSearchMealsData(mealsModelResource));

    }

    private void getSearchMealsData(Resource<Meals> mealsModelResource) {
        switch (mealsModelResource.mStatus) {
            case LOADING:
                showProgressDialog(Const.PLEASE_WAIT);
                break;
            case SUCCESS:
                dismissProgressDialog();
                if (mealsModelResource.mData == null) {
                    return;
                } else {
                    if (mealsModelResource.mData.getMeals() != null) {
                        meals = mealsModelResource.mData.getMeals();
                        mealsAdapter.updateList(meals);

                    }
                }
                break;
            case ERROR:
                dismissProgressDialog();
                // doSessionExpired(BaseUtils.parseError(mealsResource.mError));
                break;

        }
    }

    @Override
    public void OnItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.cv_meal_item:
                Intent mIntent = new Intent(this, MealDetailsActivity.class);
                mIntent.putExtra("data", meals.get(position));
                startActivity(mIntent);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}