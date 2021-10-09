package com.app.foodycookbook.feature.meal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;
import com.app.foodycookbook.apputils.BaseUtils;
import com.app.foodycookbook.baseui.BaseFragment;
import com.app.foodycookbook.baseui.BaseViewModelFactory;
import com.app.foodycookbook.database.DatabaseClient;
import com.app.foodycookbook.feature.container.HomeActivity;
import com.app.foodycookbook.feature.mealdetails.MealDetailsActivity;
import com.app.foodycookbook.listeners.ItemClickListener;
import com.app.foodycookbook.networking.Resource;

import java.util.ArrayList;

import javax.inject.Inject;

public class MealFragment extends BaseFragment implements View.OnClickListener, ItemClickListener, HomeActivity.RemoveDb {
    private View mRootView;
    private MealsViewModel mealsViewModel;
    boolean isApiHits;
    @Inject
    BaseViewModelFactory mBaseViewModelFactory;
    RecyclerView rvMeals;
    MealsAdapter mealsAdapter;
    LinearLayoutManager linearLayoutManager;
    private ArrayList<Meals.Meal> meals;

    public static MealFragment newInstance() {
        return new MealFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_expenses, container, false);
        initializeView();
        ((HomeActivity) getActivity()).setRegisterRemoveDb(this);
        return mRootView;
    }

    private void initializeView() {
        meals = new ArrayList<>();
        rvMeals = mRootView.findViewById(R.id.rv_meals);
        FoodyCookBookApplication.getApp().getDaggerAppComponent().provideIn(this);
        mealsViewModel = ViewModelProviders.of(this, mBaseViewModelFactory).get(MealsViewModel.class);
        observeData();
        setAdapter();
    }

    private void setAdapter() {
        mealsAdapter = new MealsAdapter(requireContext(), this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvMeals.setLayoutManager(linearLayoutManager);
        rvMeals.setAdapter(mealsAdapter);


    }

    private void observeData() {
        mealsViewModel.getMeals().observe(requireActivity(), mealsModelResource ->
                getMealsData(mealsModelResource));

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isApiHits) {
            doGetMeals();
        }
    }

    private void getMealsData(Resource<Meals> mealsResource) {
        switch (mealsResource.mStatus) {
            case LOADING:
                showProgressDialog();
                break;
            case SUCCESS:
                dismissProgressDialog();
                if (mealsResource.mData == null) {
                    return;
                } else {
                    if (mealsResource.mData.getMeals() != null) {
                        meals = mealsResource.mData.getMeals();
                        FatchData fatchData = new FatchData(meals);
                        fatchData.execute();
                        //mealsAdapter.updateList(meals);
                        isApiHits = true;
                    }
                }
                break;
            case ERROR:
                dismissProgressDialog();
                // doSessionExpired(BaseUtils.parseError(mealsResource.mError));
                break;

        }
    }


    private void doGetMeals() {
        if (!BaseUtils.checkNetwork(requireActivity())) {
            View.OnClickListener onOkClickListener = v -> {
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                dismissMessageAlertDialog();
            };
            showAlertMessageDialog(getString(R.string.ALERT_MESAAGE), getResources().getString(R.string.please_check_your_network_connection), onOkClickListener, getString(R.string.OK), getString(R.string.CANCEL), null);
        } else {
            mealsViewModel.doGetMeals();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }


    @Override
    public void OnItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.cv_meal_item:
                Intent mIntent = new Intent(requireContext(), MealDetailsActivity.class);
                mIntent.putExtra("data", meals.get(position));
                startActivity(mIntent);
                break;
            case R.id.iv_fav:
                if (!meals.get(position).isFav()) {
                    SaveTask st = new SaveTask(meals.get(position));
                    st.execute();
                } else {
                    RemoveData st = new RemoveData(meals);
                    st.execute();
                }


                break;
        }
    }

    @Override
    public void isRemoveDb(boolean isRemove) {
        meals.get(0).setFav(false);
        mealsAdapter.notifyDataSetChanged();
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {
        private final Meals.Meal meal;

        public SaveTask(Meals.Meal meal) {
            this.meal = meal;

        }

        @Override
        protected Void doInBackground(Void... v) {
            //adding to database
            meal.setFav(true);
            DatabaseClient.getInstance(getActivity()).getAppDatabase()
                    .mealsDao()
                    .insert(meal);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mealsAdapter.notifyDataSetChanged();
            customSnack(getView(), getString(R.string.saved_successfully));
        }
    }

    class FatchData extends AsyncTask<Void, Void, Integer> {
        ArrayList<Meals.Meal> meal;

        public FatchData(ArrayList<Meals.Meal> meal) {
            this.meal = meal;
        }

        @Override
        protected Integer doInBackground(Void... v) {
            //adding to database
            Integer value = DatabaseClient.getInstance(getActivity()).getAppDatabase()
                    .mealsDao()
                    .getTask(meal.get(0).getIdMeal());
            return value;
        }

        @Override
        protected void onPostExecute(Integer value) {
            super.onPostExecute(value);
            if (value != 1) {
                meals.get(0).setFav(false);
            } else {
                meals.get(0).setFav(true);
            }
            mealsAdapter.updateList(meals);
        }
    }

    class RemoveData extends AsyncTask<Void, Void, Integer> {
        ArrayList<Meals.Meal> meal;

        public RemoveData(ArrayList<Meals.Meal> meal) {
            this.meal = meal;
        }

        @Override
        protected Integer doInBackground(Void... v) {
            //adding to database
            Integer value = DatabaseClient.getInstance(getActivity()).getAppDatabase()
                    .mealsDao()
                    .delete(meal.get(0).getIdMeal());
            return value;
        }

        @Override
        protected void onPostExecute(Integer value) {
            super.onPostExecute(value);
            if (value == 1) {
                meals.get(0).setFav(false);
            }
            mealsAdapter.notifyDataSetChanged();
            customSnack(getView(), getString(R.string.remove_successfully));
        }
    }
}