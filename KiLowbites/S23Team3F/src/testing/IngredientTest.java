package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cooking.Ingredient;

/**
 * 
 * @author Liam J Herkins
 *
 */
public class IngredientTest
{
  Ingredient ing = null;
  String tomat = "Tomatillo";
  String unit = "Gram";
  String isIt = "they green";

  @BeforeEach
  void setUp()
  {
    ing = new Ingredient(tomat, 15.0, 1.0, true);
    ing.setAmount(2.5);
  }

  @Test
  void testIngConstructor()
  {
    assertFalse(ing.isPresent(tomat));
    ing.setAmount(2.5);
    assertEquals(2.5, ing.getAmount());
    assertEquals(tomat, ing.getLabel());
    ing.incAmount(1.0);
    assertEquals(3.5, ing.getAmount());
    ing.incByOne();
    assertEquals(4.5, ing.getAmount());
    ing.setUnits(unit);
    assertEquals(unit, ing.getUnits());
    assertThrows(IllegalArgumentException.class, () -> {
      ing.setUnits("gwam");
    });
    assertThrows(IllegalArgumentException.class, () -> {
      ing.setUnits(null);
    });
    ing.setDetails(isIt);
    assertEquals(isIt, ing.getDetails());
    System.out.println(ing.ingredientToString());
    assertTrue(ing.getVolumetric());
    Ingredient testy = new Ingredient("no", 1.0, 0.0, false);
    testy.setDensity(2.0);
    assertEquals(2.0, testy.getDensity());
    testy.setCalories(40.0);
    assertEquals(40.0, testy.getCalories());
    testy.setDensity(-1.0);
    testy.setCalories(-1.0);
    assertEquals(0.01, testy.getDensity() + testy.getCalories());
    assertThrows(IllegalArgumentException.class, () -> {
      testy.addSubstitution(null);
    });
    testy.addSubstitution(ing);
    assertTrue(testy.hasSubstitutes());
    assertFalse(ing.hasSubstitutes());
    assertEquals(ing, testy.getSubstitutes().get(0));
    
    

  }

  @Test
  void testToStringYMutators()
  {
    assertFalse(ing.isValidUnit("yoyo"));
    assertTrue(ing.isValidUnit("pound"));
    assertFalse(ing.isValidUnit(null));
    assertThrows(IllegalStateException.class, () -> {
      ing.incAmount(-1);
    });
    assertTrue(ing.isPresent("Alcohol"));

  }

}
