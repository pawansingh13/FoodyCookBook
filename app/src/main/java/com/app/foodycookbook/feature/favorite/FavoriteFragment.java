package com.app.foodycookbook.feature.favorite;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodycookbook.FoodyCookBookApplication;
import com.app.foodycookbook.R;
import com.app.foodycookbook.baseui.BaseFragment;
import com.app.foodycookbook.database.DatabaseClient;
import com.app.foodycookbook.feature.container.HomeActivity;
import com.app.foodycookbook.feature.meal.MealFragment;
import com.app.foodycookbook.feature.meal.Meals;
import com.app.foodycookbook.feature.meal.MealsAdapter;
import com.app.foodycookbook.feature.mealdetails.MealDetailsActivity;
import com.app.foodycookbook.listeners.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends BaseFragment implements ItemClickListener {
    private View mRootView;

    RecyclerView rvMeals;
    MealsAdapter mealsAdapter;
    LinearLayoutManager linearLayoutManager;
    private ArrayList<Meals.Meal> meals;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        initializeView();

        return mRootView;
    }

    private void initializeView() {
        rvMeals = mRootView.findViewById(R.id.rv_fav_meals);
        FoodyCookBookApplication.getApp().getDaggerAppComponent().provideIn(this);
        setAdapter();
        FatchData fatchData = new FatchData();
        fatchData.execute();
    }

    private void setAdapter() {
        mealsAdapter = new MealsAdapter(requireContext(), this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvMeals.setLayoutManager(linearLayoutManager);
        rvMeals.setAdapter(mealsAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();

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
                RemoveData st = new RemoveData(meals.get(position),position);
                st.execute();
                break;

        }
    }

    class FatchData extends AsyncTask<Void, Void, ArrayList<Meals.Meal>> {

        @Override
        protected ArrayList<Meals.Meal> doInBackground(Void... v) {
            //adding to database
            List<Meals.Meal> list = DatabaseClient.getInstance(getActivity()).getAppDatabase()
                    .mealsDao()
                    .getAll();
            meals = new ArrayList(list);
            return meals;
        }

        @Override
        protected void onPostExecute(ArrayList<Meals.Meal> meals) {
            super.onPostExecute(meals);
            mealsAdapter.updateList(meals);
        }
    }

    class RemoveData extends AsyncTask<Void, Void, Integer> {
        Meals.Meal meal;
        int position;

        public RemoveData(Meals.Meal meal, int position) {
            this.meal = meal;
            this.position = position;
        }

        @Override
        protected Integer doInBackground(Void... v) {
            //adding to database
            Integer value = DatabaseClient.getInstance(getActivity()).getAppDatabase()
                    .mealsDao()
                    .delete(meal.getIdMeal());
            return value;
        }

        @Override
        protected void onPostExecute(Integer value) {
            super.onPostExecute(value);
            if (value == 1) {
                meals.remove(position);
                ((HomeActivity) getActivity()).isRemoveFromDb(true);
            }
            mealsAdapter.notifyDataSetChanged();
            customSnack(getView(), getString(R.string.remove_successfully));
        }
    }
}