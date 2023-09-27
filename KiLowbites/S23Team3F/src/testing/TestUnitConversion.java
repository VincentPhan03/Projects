package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

import org.junit.jupiter.api.Test;

import cooking.Food;
import cooking.Ingredient;
import cooking.IngredientDatabase;
import math.UnitConversion;

class TestUnitConversion
{

//  @Test
//  void test()
//  {
//    fail("Not yet implemented");
//  }
  
  @SuppressWarnings("deprecation")
  @Test
  void testCalculate() throws ClassNotFoundException, IOException
  {
    //example test from the domain glossary; grams of chicken to milliliters of chicken 
    
    Map<String, Ingredient> ingredientDB = IngredientDatabase.deserializeMap();
    

    
    double test1 = UnitConversion.calculate("Gram", "Milliliter", ingredientDB.get("Chicken"), 10.0);
    
    assertEquals(9.615384615384615, test1);
    
    //convert cups of chicken to ounces of chicken 
    
    double gramToOunce = UnitConversion.calculate("Cup", "Ounce", ingredientDB.get("Chicken"), 1.0);
    

    
    String ret = String.format("%f4", gramToOunce);
    

    //truncates the original calculated value to 4 decimal places
    DecimalFormat df = new DecimalFormat("0.####");
    df.setRoundingMode(RoundingMode.DOWN);

    double temp = Double.valueOf(df.format(gramToOunce));
    String outpoutString = df.format(gramToOunce);
    
    assertEquals(8.6792, temp);
    
  }

}
