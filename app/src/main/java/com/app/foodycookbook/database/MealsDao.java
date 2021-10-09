package com.app.foodycookbook.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.app.foodycookbook.feature.meal.Meals;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MealsDao {

    @Query("SELECT * FROM  Meal")
    List<Meals.Meal> getAll();

    @Query("SELECT * FROM Meal WHERE idMeal = :idMeal")
    int getTask(String idMeal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Meals.Meal meals);

    @Query("DELETE  FROM Meal WHERE idMeal = :idMeal")
    int delete(String idMeal);
//    @Delete
//    int delete(Meals.Meal meals);

    @Update
    void update(Meals.Meal meals);
}
