package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import cooking.DecentralizedDatabase;
import cooking.Ingredient;
import cooking.IngredientDatabase;
import cooking.Meal;
import cooking.Recipe;
import gui.CalorieCalculatorWindow;
import math.FromUnits;

class CalorieCalculatorWindowTest
{

  @Test
  public void calculateTest() throws ClassNotFoundException, IOException
  {
    String a = "Alcohol";
    Map<String, Ingredient> x = IngredientDatabase.deserializeMap();
    double calories2;
    calories2 = CalorieCalculatorWindow.calculateCalories(x.get(a),
        1, FromUnits.POUND.getUnit());
    assertEquals(calories2, 1247.37888);
    double calories;
    calories = CalorieCalculatorWindow.calculateCalories(x.get(a),
        1, FromUnits.OUNCE.getUnit());
    assertEquals(calories, 77.96118);
    double calories3;
    calories3 = CalorieCalculatorWindow.calculateCalories(null,
        1, "");
    assertEquals(calories3, -1.0);
    double calories4;
    calories4 = CalorieCalculatorWindow.calculateCalories(null,
        1, FromUnits.OUNCE.getUnit());
    assertEquals(calories4, -1.0);
  }
  
  @Test
  public void calculateRecipeTest() throws IOException, ClassNotFoundException
  {
    double caloriesRecipe = 0.0;
    DecentralizedDatabase db = new DecentralizedDatabase();
    db.loadMe();
    ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    recipes = db.getAllRecipes();
    for (Recipe r : recipes)
    {
      caloriesRecipe = CalorieCalculatorWindow.calculateCaloriesRecipe(r);
    }
    assertEquals(caloriesRecipe, 1247.37888);
    double caloriesRecipe2 = 0.0;
    caloriesRecipe2 = CalorieCalculatorWindow.calculateCaloriesRecipe(null);
    assertEquals(caloriesRecipe2, -1.0);
    
  }
  
  @Test
  public void calculateMealTest() throws IOException, ClassNotFoundException
  {
    double caloriesMeal = 0.0;
    DecentralizedDatabase db2 = new DecentralizedDatabase();
    db2.loadMe();
    ArrayList<Meal> meals = new ArrayList<Meal>();
    meals = db2.getAllMeals();
    for (Meal meal : meals)
    {
      caloriesMeal += CalorieCalculatorWindow.calculateCaloriesMeal(meal);
    }
    assertEquals(caloriesMeal, 1247.37888);
    double caloriesMeal2 = 0.0;
    caloriesMeal2 = CalorieCalculatorWindow.calculateCaloriesMeal(null);
    assertEquals(caloriesMeal2, -1.0);
  }

}
