package com.app.foodycookbook.feature.mealdetails;

import com.app.foodycookbook.feature.meal.Meals;

import java.util.ArrayList;

public class BuildIngredientData {

    static ArrayList<Ingredients> ingredientsList = new ArrayList<>();
    private static Ingredients ingredients;

    public static ArrayList<Ingredients> buildData(Meals.Meal meal) {
        ingredientsList.clear();
        if (meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient1());
            ingredients.setQuantity(meal.getStrMeasure1());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient2());
            ingredients.setQuantity(meal.getStrMeasure2());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient3());
            ingredients.setQuantity(meal.getStrMeasure3());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient4());
            ingredients.setQuantity(meal.getStrMeasure4());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient5());
            ingredients.setQuantity(meal.getStrMeasure5());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient6());
            ingredients.setQuantity(meal.getStrMeasure6());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient7());
            ingredients.setQuantity(meal.getStrMeasure7());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient8());
            ingredients.setQuantity(meal.getStrMeasure8());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient9());
            ingredients.setQuantity(meal.getStrMeasure9());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient10() != null && !meal.getStrIngredient10().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient10());
            ingredients.setQuantity(meal.getStrMeasure10());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient11() != null && !meal.getStrIngredient11().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient11());
            ingredients.setQuantity(meal.getStrMeasure11());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient12() != null && !meal.getStrIngredient12().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient12());
            ingredients.setQuantity(meal.getStrMeasure12());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient13() != null && !meal.getStrIngredient13().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient13());
            ingredients.setQuantity(meal.getStrMeasure13());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient14() != null && !meal.getStrIngredient14().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient14());
            ingredients.setQuantity(meal.getStrMeasure14());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient15() != null && !meal.getStrIngredient15().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient15());
            ingredients.setQuantity(meal.getStrMeasure15());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient16());
            ingredients.setQuantity(meal.getStrMeasure16());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient17() != null && !meal.getStrIngredient17().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient17());
            ingredients.setQuantity(meal.getStrMeasure17());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient18() != null && !meal.getStrIngredient18().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient18());
            ingredients.setQuantity(meal.getStrMeasure18());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient19() != null && !meal.getStrIngredient19().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient19());
            ingredients.setQuantity(meal.getStrMeasure19());
            ingredientsList.add(ingredients);
        }
        if (meal.getStrIngredient20() != null && !meal.getStrIngredient20().isEmpty()) {
            ingredients = new Ingredients();
            ingredients.setIngredients(meal.getStrIngredient20());
            ingredients.setQuantity(meal.getStrMeasure20());
            ingredientsList.add(ingredients);
        }
        return ingredientsList;
    }
}

