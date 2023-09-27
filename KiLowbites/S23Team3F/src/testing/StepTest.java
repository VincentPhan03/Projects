package testing;

import cooking.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StepTest
{

  String isNot = "aint";
  String chop = "cut";
  String smelly = "Garlic";
  String frk = "Fork";
  Step up = null;
  Step down = null;

  @BeforeEach
  void setUp()
  {
    up = new Step(chop, "quickly", new Ingredient(smelly, 10, 1.0, true),
        new Utensil(frk, "wooden"));
    down = new Step(chop, isNot, new Utensil(frk, smelly), new Utensil(frk, smelly));
    

  }

  @Test
  void test()
  {
    assertEquals(chop, up.getLocalAction());
    Ingredient ing = new Ingredient(smelly, 10, 1.0, true);
    Ingredient ing2 = (Ingredient) up.getOn();
    assertEquals(ing.getLabel(), ing2.getLabel());
    up.setTime(45);
    assertEquals(45, up.getTime());
    up.setDetails(isNot);
    assertEquals(isNot, up.getDetails());
    up.setOn(ing2);
    assertThrows(IllegalArgumentException.class, () -> 
    {
      up.onName(null);
    });
    assertThrows(IllegalArgumentException.class, () -> 
    {
      up.setOn(null);
    });
    assertThrows(IllegalArgumentException.class, () -> 
    {
      up.setOn(new Object());
    });
    up.setOn(up.getTo());
    assertEquals(frk, up.getToName());
    up.setTime(-1);
    assertEquals(up.getTime(), 0.0);
    assertEquals("cut the contents of the Fork aint", up.stepToString());
    
    
    
    
    

  }
  @Test
  void test2()
  {
    assertThrows(IllegalArgumentException.class, () -> 
    {
      up.onName(new Object());
    });
    assertEquals("cut the contents of the Fork on the Fork aint", down.stepToString());
    
    assertThrows(IllegalArgumentException.class, () -> 
    {
      down = new Step(frk, smelly, "Liam", new Utensil("pot", "big"));;
    });
    down.onName(new Ingredient("no", 0.0, 1.0, true));
    

  }

}
