package cooking;

import java.io.Serializable;
import java.util.*;

/**
 * Meal object class for the KilowBite meal editor.
 * 
 * Class is used/instantiated throughout repository Represents a meal with two main attributes which
 * are from the parent class (name, details). Attached to the class is an arraylist of recipes and a
 * floating point value which represents how many people that this meal can serve.
 * 
 * @author Liam J Herkins, Cole T. Strubhar
 *
 */

public class Meal extends AbstractCook implements Serializable
{

  private static final long serialVersionUID = 1L;
  private ArrayList<Recipe> recipes;

  private double sizeToServe;

  /**
   * Explict constructor for the meal class.
   * 
   * @param name
   *          The user delcared name for the meal.
   * @param details
   *          any additional information about the meal name.
   */
  public Meal(String name, String details)
  {
    super(name, details);
    recipes = new ArrayList<>();
    // TODO Auto-generated constructor stub
  }

  /**
   * Method which adds recipe objects to the list of recipes associated with this instance of the
   * class.
   * 
   * Method checks if the recipe is not null and is already in the list.
   * 
   * If either condition is not met, an illegalArgumentException is thrown.
   * 
   * @param rec
   *          Recipe object to be added to the list.
   */
  public void addRecipe(Recipe rec)
  {
    if (rec != null && !this.recipes.contains(rec))
    {
      this.recipes.add(rec);
    }
    else
    {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Method which removes the recipe which is passed to it from the attribute list of recipe
   * objects.
   * 
   * Method checks if the argument isn't null and is already within the list of recipes.
   * 
   * @param toRemove
   *          target recipe to remove from the list of recipes.
   * @return the removed recipe.
   */
  public Recipe removeRecipe(Recipe toRemove)
  {
    if (toRemove != null && this.recipes.contains(toRemove))
    {
      int indexOf = this.recipes.indexOf(toRemove);
      Recipe beenRemoved = this.recipes.remove(indexOf);
      return beenRemoved;
    }
    else
    {
      throw new NoSuchElementException();
    }
  }

  /**
   * Accessor method for the recipes attribute.
   * 
   * @return the current instance of recipes.
   */
  public ArrayList<Recipe> getRecipies()
  {
    return this.recipes;
  }

  /**
   * Method that returns the current length of the recipe list attribute.
   * 
   * @return the size of the list.
   */
  public int recipesSize()
  {
    if (this.recipes != null)
    {
      return this.recipes.size();
    }
    else
    {
      return 0;
    }
  }

  /**
   * Method sets the serving size of the meal via. assigning the attribute to the argument passed.
   * 
   * @param serv
   *          the serving size to be set.
   */
  public void setServS(double serv)
  {
    if (serv >= .1)
    {
      this.sizeToServe = serv;
    }
    else
    {
      this.sizeToServe = 0.0;
    }
  }

}
