package testing;

import cooking.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest
{
  Recipe rec = null;
  String dont = "rub";
  String where = "all over";
  String tomat = "Tomato";
  String smelly = "Garlic";
  String tillo = "Tomatillo";
  String ut = "Fork";
  String m = "meal";
  String leat = "Leatmoaf";

  @BeforeEach
  void setUp()
  {
    rec = new Recipe("Meatloaf", "spicy");
    Ingredient temp = new Ingredient(tillo, 15.0, 1.0, true);
    rec.addIngredient(temp);
    rec.addIngredient(smelly, 5, 1.0, false);
    rec.addUtensil(dont, "wooden");
    rec.enqStep(new Step("dont", where, (Object) new Ingredient(tomat, 20.0, 1.5, false),
        new Utensil(ut, m)));
    rec.setName(leat);
    rec.setServingSize(4);
    rec.ingredientServ(4);
    rec.removeIngredient(tillo);
    rec.stepDequeue();
    ArrayList<Ingredient> arrLIng = rec.getIngredients();
    
    

  }

  @SuppressWarnings("unlikely-arg-type")
  @Test
  void test()
  {
    assertFalse(rec.ingredientListContains("liam"));
    assertTrue(rec.ingredientListContains(smelly));
    rec.getSteps();
    rec.getUtensils();
    equals(new Step(dont, where, (Object) new Ingredient(tomat, 20.0, 1.5, false),
        new Utensil("Metal", ut)));
    rec.removeUtensil(ut);
    assertEquals(leat, rec.getName());
    assertEquals(0, rec.ingredientIndexOf(smelly));
    assertEquals(-1, rec.ingredientIndexOf("me"));
    rec.setName(null);
    rec.setServingSize(-1);
    assertEquals(0.0, rec.getServingSize());
   

  }

}
