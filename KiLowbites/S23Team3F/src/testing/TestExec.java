package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;



import org.junit.jupiter.api.Test;

@Execution(ExecutionMode.CONCURRENT)
class TestExec
{

  @Test
  void test()
  {
    FoodTest foodie = new FoodTest();
    IngredientTest testy = new IngredientTest();
    RecipeTest recTest = new RecipeTest();
    StepTest stest = new StepTest();
    CalorieCalculatorWindowTest calc = new CalorieCalculatorWindowTest();
    TestUnitConversion unit = new TestUnitConversion();
  }

}
