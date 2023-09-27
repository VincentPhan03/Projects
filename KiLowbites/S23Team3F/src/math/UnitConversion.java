package math;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import cooking.Ingredient;
import cooking.IngredientDatabase;

/**
 * The "back end" of the unit conversion gui.
 * 
 * @author Andrew Davis
 *
 */
public class UnitConversion
{
  private static final String POUND = "pound";
  private static final String OUNCE = "ounce";
  private static final String MILLILITER = "milliliter";
  private static final String GRAM = "gram";
  private static final String TABLESPOON = "tablespoon";
  private static final String TEASPOON = "teaspoon";
  private static final String FLUIDOUNCE = "fluidounce";
  private static final String CUP = "cup";
  private static final String PINT = "pint";
  private static final String QUART = "quart";
  private static final String DRAM = "dram";
  private static final String PINCH = "pinch";
  private static final String GALLON = "gallon";

  private static final String EMPTYFIELD = "Empty field detected";

  // default value to indicate error
  private static double ret = -1.0;

  private static double teaSpoon;
  private static double pinch;
  private static double tableSpoon;
  private static double fluidOunce;
  private static double cup;
  private static double pint;
  private static double quart;
  private static double gallon;
  private static double milliliter;

  private static double ounce;
  private static double pound;
  private static double gram;
  private static double dram;

  private static String[] mass2 = {FromUnits.POUND.toString(),
      FromUnits.OUNCE.toString(),
      FromUnits.DRAM.toString(),
      FromUnits.GRAM.toString()};

  private static String[] volume2 = {FromUnits.TEASPOON.toString(),
      FromUnits.PINCH.toString(),
      FromUnits.TABLESPOON.toString(),
      FromUnits.FLUIDOUNCE.toString(),
      FromUnits.CUP.toString(),
      FromUnits.MILLILITER.toString(),
      FromUnits.PINT.toString(),
      FromUnits.QUART.toString(),
      FromUnits.GALLON.toString()};

  private static ArrayList<String> volume = new ArrayList<>(Arrays.asList(volume2));
  private static ArrayList<String> mass = new ArrayList<>(Arrays.asList(mass2));

  private static Ingredient tempIngredient;

  /**
   * The main functionality for the unit conversion gui.
   * 
   * @param fromUnit
   *          the unit converted from
   * @param toUnit
   *          the unit converting to
   * @param ingredient
   *          the desired ingredient
   * @param fAmt
   *          the initial amount
   * @return amount converted from the initial amount
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public static double calculate(final String fromUnit, final String toUnit,
      final Ingredient ingredient, final double fAmt) throws ClassNotFoundException, IOException
  {
    Map<String, Ingredient> ingredientDB = IngredientDatabase.deserializeMap();
    tempIngredient = ingredient;

    // TODO
    // check for null/ unselected units
    if (fromUnit == null || toUnit == null)
    {

      // throw exception because from unit or to unit was needed but was null
      throw new IllegalArgumentException(EMPTYFIELD);

    }

    if (ingredient == null)
    {
      // if from units is mass & to units is volume, enable ingredient box
      if ((mass.contains(fromUnit)
          && volume.contains(toUnit) | (volume.contains(fromUnit) && mass.contains(toUnit))))
      {
        // throw exception because ingredient was needed but was null
        throw new IllegalArgumentException(EMPTYFIELD);

      }
      else
      {
        tempIngredient = ingredientDB.get("default");
      }

    }

    if (fromUnit.equalsIgnoreCase("Pound"))
    {

      pound = fAmt;
      ounce = pound * FromUnits.POUND.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      gram = ounce * FromUnits.OUNCE.getScale()[1];

      milliliter = gram / tempIngredient.getDensity();
      fluidOunce = milliliter * FromUnits.MILLILITER.getScale()[1];
      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];

      switch (toUnit.toLowerCase())
      {

        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        default: // do nothing
          break;

      }
    }

    if (fromUnit.equalsIgnoreCase("Dram"))
    {

      dram = fAmt;
      ounce = dram * FromUnits.DRAM.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];
      gram = ounce * FromUnits.OUNCE.getScale()[1];

      milliliter = gram / tempIngredient.getDensity();
      fluidOunce = milliliter * FromUnits.MILLILITER.getScale()[1];
      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];

      switch (toUnit.toLowerCase())
      {

        case OUNCE:
          ret = ounce;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        case DRAM:
          ret = dram;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Gram"))
    {

      gram = fAmt;
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      milliliter = gram / tempIngredient.getDensity();
      fluidOunce = milliliter * FromUnits.MILLILITER.getScale()[1];
      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];

      switch (toUnit.toLowerCase())
      {

        case OUNCE:
          ret = fAmt * FromUnits.GRAM.getScale()[0];
          break;
        case DRAM:
          ret = (fAmt * FromUnits.GRAM.getScale()[0]) * FromUnits.OUNCE.getScale()[0];
          break;
        case POUND:
          ret = (fAmt * FromUnits.GRAM.getScale()[0]) * FromUnits.OUNCE.getScale()[2];
          break;
        case GRAM:
          ret = fAmt;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Ounce"))
    {

      ounce = fAmt;
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      gram = ounce * FromUnits.OUNCE.getScale()[1];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      milliliter = gram / tempIngredient.getDensity();
      fluidOunce = milliliter * FromUnits.MILLILITER.getScale()[1];
      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];

      switch (toUnit.toLowerCase())
      {

        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Teaspoon"))
    {
      tableSpoon = fAmt * FromUnits.TEASPOON.getScale()[0];
      fluidOunce = (fAmt * FromUnits.TEASPOON.getScale()[0]) * FromUnits.TABLESPOON.getScale()[1];
      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];
      milliliter = tableSpoon * FromUnits.TABLESPOON.getScale()[2];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TABLESPOON:
          ret = tableSpoon;
          break;
        case PINCH:
          ret = fAmt * FromUnits.TEASPOON.getScale()[1];
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case TEASPOON:
          ret = fAmt;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Pinch"))
    {
      teaSpoon = fAmt * FromUnits.PINCH.getScale()[0];

      tableSpoon = teaSpoon * FromUnits.TEASPOON.getScale()[0];

      fluidOunce = tableSpoon * FromUnits.TABLESPOON.getScale()[1];
      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];
      milliliter = tableSpoon * FromUnits.TABLESPOON.getScale()[2];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TEASPOON:
          ret = teaSpoon;
          break;
        case TABLESPOON:
          ret = tableSpoon;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case PINCH:
          ret = fAmt;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Tablespoon"))
    {
      tableSpoon = fAmt;
      teaSpoon = fAmt * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];

      fluidOunce = tableSpoon * FromUnits.TABLESPOON.getScale()[1];
      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];
      milliliter = tableSpoon * FromUnits.TABLESPOON.getScale()[2];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case TABLESPOON:
          ret = tableSpoon;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Fluid Ounce"))
    {

      fluidOunce = fAmt;
      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];

      cup = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];
      milliliter = tableSpoon * FromUnits.TABLESPOON.getScale()[2];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Cup"))
    {

      cup = fAmt;
      fluidOunce = cup * FromUnits.CUP.getScale()[0];

      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];
      milliliter = tableSpoon * FromUnits.TABLESPOON.getScale()[2];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Milliliter"))
    {

      milliliter = fAmt;
      cup = milliliter * FromUnits.MILLILITER.getScale()[0];
      fluidOunce = cup * FromUnits.CUP.getScale()[0];

      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      pint = cup * FromUnits.CUP.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case CUP:
          ret = cup;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case MILLILITER:
          ret = fAmt;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Pint"))
    {

      pint = fAmt;
      cup = pint * FromUnits.PINT.getScale()[0];
      fluidOunce = cup * FromUnits.CUP.getScale()[0];
      milliliter = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[2];

      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      quart = pint * FromUnits.PINT.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case CUP:
          ret = cup;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case PINT:
          ret = pint;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Quart"))
    {

      quart = fAmt;
      pint = quart * FromUnits.QUART.getScale()[0];

      cup = pint * FromUnits.PINT.getScale()[0];
      fluidOunce = cup * FromUnits.CUP.getScale()[0];
      milliliter = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[2];
      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];
      gallon = quart * FromUnits.QUART.getScale()[1];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case CUP:
          ret = cup;
          break;
        case GALLON:
          ret = gallon;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    if (fromUnit.equalsIgnoreCase("Gallon"))
    {

      gallon = fAmt;
      quart = gallon * FromUnits.GALLON.getScale()[0];
      pint = quart * FromUnits.QUART.getScale()[0];
      cup = pint * FromUnits.PINT.getScale()[0];
      fluidOunce = cup * FromUnits.CUP.getScale()[0];
      milliliter = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[2];
      tableSpoon = fluidOunce * FromUnits.FLUIDOUNCE.getScale()[0];
      teaSpoon = tableSpoon * FromUnits.TABLESPOON.getScale()[0];
      pinch = teaSpoon * FromUnits.TEASPOON.getScale()[1];

      gram = milliliter * tempIngredient.getDensity();
      ounce = gram * FromUnits.GRAM.getScale()[0];
      dram = ounce * FromUnits.OUNCE.getScale()[0];
      pound = ounce * FromUnits.OUNCE.getScale()[2];

      switch (toUnit.toLowerCase())
      {

        case TABLESPOON:
          ret = tableSpoon;
          break;
        case TEASPOON:
          ret = teaSpoon;
          break;
        case PINCH:
          ret = pinch;
          break;
        case CUP:
          ret = cup;
          break;
        case FLUIDOUNCE:
          ret = fluidOunce;
          break;
        case MILLILITER:
          ret = milliliter;
          break;
        case PINT:
          ret = pint;
          break;
        case QUART:
          ret = quart;
          break;
        case GALLON:
          ret = gallon;
          break;
        case OUNCE:
          ret = ounce;
          break;
        case DRAM:
          ret = dram;
          break;
        case GRAM:
          ret = gram;
          break;
        case POUND:
          ret = pound;
          break;
        default: // do nothing
          break;
      }
    }

    return ret;

  }

}
