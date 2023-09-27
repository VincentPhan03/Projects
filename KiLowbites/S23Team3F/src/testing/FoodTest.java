package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cooking.Food;

class FoodTest
{

  @Test
  public void constructorsAndGetterstest()
  {
    double alcMeasure = 0.79;
    assertEquals(Food.Alcohol.getCalories(), 275);
    assertEquals(Food.Alcohol.getMeasure(), alcMeasure, 0.79);
    assertTrue(Food.Alcohol.isVolumetric());
    assertFalse(Food.Bean.isVolumetric());
  }

}
