package cooking;


import java.io.Serializable;
import java.util.ArrayList;


import math.FromUnits;

/**
 * Ingredient object to be used from within the software itself. Class has its own functions for
 * checking validity, does throw exceptions.
 * 
 * @author Liam J Herkins
 * 
 *         This code complies with the JMU honor code.
 *
 */
public class Ingredient implements Serializable
{

  private static final long serialVersionUID = 1L;
  private double amount;
  private String units;
  private String label;
  private String details;
  private boolean isVolumetric;
  private double density;
  private double calories;
  private ArrayList<Ingredient> substitutions;

  /**
   * Explicit constructor for the Ingredient class.
   * 
   * @param label
   *          Name of the ingredient.
   * @param calories
   *          Caloric value associated with the ingredient.
   * @param density
   *          Density of the particular food item.
   * @param volumetric
   *          if it is volumetric or not.
   */
  public Ingredient(final String label, final double calories, final double density,
      final boolean volumetric)
  {
    this.substitutions = new ArrayList<>();
    if (density == 0.0)
    {
      this.density = 0.01;
    }
    if (density >= 0 && calories >= 0)
      this.calories = calories;
    this.density = density;
    if (label != null && label.length() > 0)
      this.label = label;
    else
      this.label = "";
    this.isVolumetric = volumetric;

  }

  /**
   * Mutator method for the details attribute.
   * 
   * @param deets
   *          argument passed to assign.
   */
  public void setDetails(final String deets)
  {
    if (deets != null)
    {
      this.details = deets;
    }
    else
    {
      this.details = "";
    }
  }

  /**
   * Checks if the item (ingredient) is a valid ingredient. Specifically, if it is a member of the
   * ingredient enum.
   * 
   * @param item
   *          to be checked.
   * @return boolean state if it is there.
   */
  public boolean isPresent(final String item)
  {
    Food[] options = Food.values();
    for (int i = 0; i < options.length; i++)
    {
      if (item.equalsIgnoreCase(options[i].name()))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the most current instance of the amount attribute.
   * 
   * @return the current amount
   */
  public double getAmount()
  {
    return this.amount;
  }

  /**
   * Accessor method for the label associated with this object.
   * 
   * @return most current instance of the label
   */
  public String getLabel()
  {
    return this.label;
  }

  /**
   * Increments the amount associated with this class by whatever value is present in the arguments.
   * 
   * @param toInc
   *          how much to increment the amount by.
   */
  public void incAmount(final double toInc)
  {
    if (toInc > 0)
      this.amount += toInc;
    else
      throw new IllegalStateException();
  }

  /**
   * Method that increments the amount by one, most likely with a button.
   * 
   */
  public void incByOne()
  {
    this.amount++;
  }

  /**
   * Method for setting an amount to a particular number, overriding the previous amount.
   * 
   * @param count
   *          the number to store.
   */
  public void setAmount(final double count)
  {
    this.amount = count;
  }

  /**
   * Mutator method for the units attribute.
   * 
   * Method does check if the unit passed is a valid unit (it exists).
   * 
   * @param units
   *          to be set by this method.
   */
  public void setUnits(final String units)
  {
    if (units != null && isValidUnit(units))
    {
      this.units = units;
    }
    else
    {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Method returns a formatted string representation of the ingredient object.
   * 
   * @return formatted string representation of ingredient objects.
   */
  public String ingredientToString()
  {

    return String.format("%.2f %s of %s %s", getAmount(), getUnits(), getDetails(), getLabel());
  }

  /**
   * Accessor method for the units attribute as part of this ingredient class.
   * 
   * @return the current instance of the units (String) attribute.
   */
  public String getUnits()
  {
    return this.units;
  }

  /**
   * Accessor method for the details attribute associated with the ingredient object.
   * 
   * @return the current instance of the details (String) attribute.
   */
  public String getDetails()
  {
    return this.details;
  }

  /**
   * Method checks the list of units from a pre-written enumerated type and sees if it is a member
   * of that enum.
   * 
   * @param units2
   *          String representation of units passed in.
   * @return Whether or not the units are indeed valid.
   */
  public boolean isValidUnit(final String units2)
  {

    FromUnits[] check = FromUnits.values();
    if (units2 != null)
    {
      for (int i = 0; i < check.length; i++)
      {
        if (check[i].toString().equalsIgnoreCase(units2))
        {
          return true;
        }
      }
    }
    else
    {
      return false;
    }
    return false;

  }

  /**
   * Accessor method for the isVolumetric boolean value that is associated with this class.
   * 
   * @return The boolean state of the isVolumetric attribute.
   */
  public boolean getVolumetric()
  {
    return this.isVolumetric;
  }

  /**
   * Accessor method for the density attribute (double floating point precision) associated with
   * this class.
   * 
   * @return the current instance of the density attribute.
   */
  public double getDensity()
  {
    return this.density;
  }

  /**
   * Mutator method for the calories (double) attribute.
   * 
   * If the argument passed is negative, it's value is set to zero.
   * 
   * @param calories
   *          to be set to the attribute.
   */
  public void setCalories(final double calories)
  {
    if (calories >= 0.0)
      this.calories = calories;
    else
      this.calories = 0.0;
  }

  /**
   * Mutator method for the density (double) attribute.
   * 
   * If the density is less than .01 (impossible) than the attribute is set to 0.01.
   * 
   * @param density
   *          to be assigned to the density attribute.
   */
  public void setDensity(final double density)
  {
    if (density >= 0.01)
      this.density = density;
    else
      this.density = 0.01;
  }

  /**
   * Accessor method for the calorie (double) attribute.
   * 
   * @return current instance of the calorie attribute.
   */
  public double getCalories()
  {
    return this.calories;
  }

  /**
   * Method for adding a substitution for a particular ingredient.
   * 
   * If a particular ingredient needs to be substituted, it's substitions attribute can be retreived
   * to give a list of possible substituions.
   * 
   * @param ing
   *          Ingredient to be added.
   */
  public void addSubstitution(final Ingredient ing)
  {
    if (ing != null)
      this.substitutions.add(ing);
    else
      throw new IllegalArgumentException();
  }

  /**
   * Boolean check method to see if an ingredient has any subs.
   * 
   * @return if this ingredient has substitutes.
   */
  public boolean hasSubstitutes()
  {
    if (substitutions.size() != 0)
    {
      return true;
    }
    return false;
  }

  /**
   * Accessor method for the substitutes attribute (list).
   * 
   * @return the current list of substitutions.
   */
  public ArrayList<Ingredient> getSubstitutes()
  {
    return substitutions;
  }

}
