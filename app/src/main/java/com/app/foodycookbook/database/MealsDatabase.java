package com.app.foodycookbook.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.app.foodycookbook.feature.meal.Meals;

@Database(entities = {Meals.Meal.class}, version = 1)
public abstract class MealsDatabase extends RoomDatabase {
    public abstract MealsDao mealsDao();
}
