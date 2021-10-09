package com.app.foodycookbook.feature.mealdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.foodycookbook.R;
import com.app.foodycookbook.baseui.BaseActivity;
import com.app.foodycookbook.feature.meal.Meals;
import com.app.foodycookbook.utills.AddViewDynamically;
import com.bumptech.glide.Glide;
import java.util.ArrayList;


public class MealDetailsActivity extends BaseActivity {

    private Meals.Meal meal;
    private ImageView mIvMealImage, mIvBack;
    private Toolbar mToolbar;
    private TextView mTvMealCat, mTvArea, mTvInst;

    ArrayList<Ingredients> ingredientList;
    LinearLayout mLlIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);
        initializeView();
    }

    private void initializeView() {

        if (getIntent().hasExtra("data")) {
            meal = (Meals.Meal) getIntent().getSerializableExtra("data");
        }
        mIvBack = findViewById(R.id.iv_back);
        mIvMealImage = findViewById(R.id.iv_meal_image);
        mLlIngredient = findViewById(R.id.ll_ingredient);
        mToolbar = findViewById(R.id.toolbar);
        mTvMealCat = findViewById(R.id.tv_meal_cat);
        mTvArea = findViewById(R.id.tv_area);
        mTvInst = findViewById(R.id.tv_inst);
        mIvBack.setOnClickListener(view -> onBackPressed());
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle(meal.getStrMeal());
        mTvMealCat.setText("Category:- " + meal.getStrCategory());
        mTvArea.setText("Area:- " + meal.getStrArea());
        Glide.with(this).load(meal.getStrMealThumb()).into(mIvMealImage);
        mTvInst.setText(meal.getStrInstructions());
        ingredientList = BuildIngredientData.buildData(meal);
        AddViewDynamically.setKeywords(this, mLlIngredient, ingredientList);
    }


}