package cooking;

import java.io.Serializable;
import java.util.*;
import java.util.Objects;

/**
 * 
 * This class is used to represent recipes from within KilowBites.
 * 
 * 
 * @author Liam J Herkins, Cole T. Strubhar
 *
 */
public class Recipe extends AbstractCook implements Serializable
{

  private static final long serialVersionUID = 1L;
  private List<Ingredient> ingredients = new ArrayList<>();
  private Queue<Step> steps = new LinkedList<Step>();
  private String name;
  private int servingSize;
  private ArrayList<Utensil> utensils;

  /**
   * Explicit constructor for recipe objects with fields.
   * 
   * @param name
   *          the name of the recipe
   * @param details
   *          the deatails of the recipe
   */
  public Recipe(final String name, final String details)
  {
    super(name, details);
    utensils = new ArrayList<>();
    // todo handle exceptions
  }

  /**
   * Method adds an ingredient object to the current list.
   * 
   * @param label
   *          The name of the recipe.
   * @param calories
   *          the caloric value associated with this recipe.
   * @param density
   *          the density of the ingredient to be added.
   * @param volumetric
   *          Is this ingredient measured using volume?
   */
  public void addIngredient(final String label, final double calories, final double density,
      final boolean volumetric)
  {
    if (!ingredientListContains(label))
    {
      this.ingredients.add(new Ingredient(label, calories, density, volumetric));
    }
  }

  /**
   * Adds an ingredient to the local list of ingredients.
   * 
   * @param toAdd
   *          added to the local list of ingredients
   */
  public void addIngredient(final Ingredient toAdd)
  {
    if (toAdd != null)
    {
      ingredients.add(toAdd);
    }
    else
    {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Add's a utensil (via constructor arguments) to the local list of utensils that are needed for
   * this recipe.
   * 
   * @param n
   *          of the utensil
   * @param details
   *          any additional details for the recipe.
   */
  public void addUtensil(final String n, final String details)
  {
    if (n != null && details != null)
      this.utensils.add(new Utensil(n, details));
  }

  /**
   * Add's a new step object to the list.
   * 
   * Note : method titled "enqStep" is a much better implementation for adding steps than this
   * method.
   * 
   * @param action
   *          of the step.
   * @param details
   *          of the step.
   * @param utensil
   *          in the step.
   * @param utensilTo
   *          of the step.
   * @param ingredient
   *          associated with the step.
   */
  @SuppressWarnings("rawtypes")
  public void addStep(final String action, final String details, final Utensil utensil,
      final Utensil utensilTo, final Object ingredient)
  {
    System.out.println(action);
    System.out.println(details);
    steps.add(new Step(action, details, ingredient, utensil));
  }

  /**
   * Method sets the name attribute for this class.
   * 
   * @param name
   *          of the recipe
   */
  @Override
  public void setName(final String name)
  {
    if (!Objects.isNull(name))
      this.name = name;
    else
      System.out.println("String has been deemed null.");
    return;
  }

  /**
   * Mutator method for the serving size attribute in this class.
   * 
   * @param servingSize
   *          to be set to.
   */
  public void setServingSize(final int servingSize)
  {
    if (servingSize >= 0)
    {
      this.servingSize = servingSize;
    }
    else
    {
      this.servingSize = 0;
    }
  }

  /**
   * Method iterates through the entirety of the ingredient list of this class and sets their
   * serving size so it is the same for the entire recipe list.
   * 
   * @param serSize
   *          to be set.
   */
  public void ingredientServ(final int serSize)
  {
    for (Ingredient e : this.ingredients)
    {
      e.setAmount(serSize);
    }
  }

  /**
   * Is a functional contains() method for arrayList but of ingredient type but allows a string to
   * be passed instead for easier implementation for GUI.
   * 
   * @param item
   *          String value to check if it is here.
   * @return boolean result if it is there
   */
  public boolean ingredientListContains(final String item)
  {
    for (Ingredient e : this.ingredients)
    {
      if (e.getLabel().equals(item))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks and finds the index of a particular ingredient via the string argument passed.
   * 
   * @param item
   *          String representation of an ingredient to search for.
   * @return the index of the ingredient
   */
  public int ingredientIndexOf(final String item)
  {
    // Set to -1 to show that it is not in the list
    // and if it is not found, the value will still be
    // -1 upon returning
    int index = -1;
    for (Ingredient e : this.ingredients)
    {
      if (e.getLabel().equals(item))
      {
        return this.ingredients.indexOf(e);
      }
    }
    return index;
  }

  /**
   * Removes the utensil passed (string name of it) from the local list of utensils.
   * 
   * 
   * @param utName
   *          Name of the utensil to be removed.
   */
  public void removeUtensil(final String utName)
  {
    for (int i = 0; i < utensils.size(); i++)
    {
      if (utensils.get(i).getName().equalsIgnoreCase(utName))
      {
        utensils.remove(i);
        i = utensils.size();
      }
    }
  }

  /**
   * Removes the ingredient passed (string name of it) from the local list of ingredients.
   * 
   * @param ingName
   *          name of the ingredient
   */
  public void removeIngredient(final String ingName)
  {
    for (int i = 0; i < ingredients.size(); i++)
    {
      if (ingredients.get(i).getLabel().equals(ingName))
      {
        ingredients.remove(i);
        i = ingredients.size();
      }
    }
  }

  /**
   * Acessor method for the steps attribute.
   * 
   * @return the current instance of the steps
   */
  public LinkedList<Step> getSteps()
  {
    return (LinkedList<Step>) steps;
  }

  /**
   * Accessor method for the list of ingredients attribute.
   * 
   * @return the current instance of the ingredients list.
   */
  public ArrayList<Ingredient> getIngredients()
  {
    return (ArrayList<Ingredient>) ingredients;
  }

  /**
   * Accessor method for the list of utensils attribute.
   * 
   * @return the current instance of the utensils list.
   */
  public ArrayList<Utensil> getUtensils()
  {
    return utensils;
  }

  /**
   * Method that removes a step (by ingredient constructor?) from the queue of steps store locally.
   * 
   * @param action
   *          Action of the step.
   * @param details
   *          Details regarding the step.
   * @param foundUtensil
   *          Utensil
   * @param foundIngredient
   *          found to be returned.
   */
  public void removeStep(final String action, final String details, final Utensil foundUtensil,
      final Ingredient foundIngredient)
  {

    for (Step step : steps)
    {

//      System.out.println("<List>");
//      System.out.println(step.getLocalAction());
//      System.out.println(action);
//      System.out.println(step.getDetails());
//      System.out.println(details);
//      System.out.println(((AbstractCook) step.getOn()).getName());
//      System.out.println(foundUtensil.getName());
//      System.out.println(step.getTo().getDetails());
//      System.out.println(foundIngredient.getLabel());
//      System.out.println("<\\List>");

      if (step.getLocalAction().equals(action) && step.getDetails().equals(details)
          && ((AbstractCook) step.getOn()).getName().equals(foundUtensil.getName())
          && ((Ingredient) step.getOn()).getLabel().equals(foundIngredient.getLabel()))
      {
        
        steps.remove(step);
      }
    }
  }

  /**
   * Utesnsil finder function that iterates through the local list of utensils to find.
   * 
   * @param toFind
   *          Key
   * @return the returned value.
   */
  public Utensil getUtensil(final String toFind)
  {
    for (Utensil utensil : utensils)
    {
      if (utensil.getName().equals(toFind))
      {
        return utensil;
      }
    }
    return null;
  }

  /**
   * Ingredient finder function that iterates through the local list of ingredients to find.
   * 
   * @param toFind
   *          Key
   * @return returned value.
   */
  public Ingredient getIngredient(final String toFind)
  {
    for (Ingredient ingredient : ingredients)
    {
      if (ingredient.getLabel().equals(toFind))
      {
        return ingredient;
      }
    }

    return null;
  }

  /**
   * Dequeue's an element from the queue.
   * 
   * @return the step that is being removed.
   */
  public Step stepDequeue()
  {
    return this.steps.remove();
  }

  /**
   * Accessor method for the name attribute.
   * 
   * @return name the current instance of the name attribute.
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * Accessor method for the serving size (int) attribute.
   * @return current instance of the integer attribute.
   */
  public int getServingSize()
  {
    return this.servingSize;
  }
  /**
   * Enqueue's an element into the queue.
   * 
   * @param step
   *          to be enqueued
   */
  public void enqStep(final Step step)
  {
    this.steps.add(step);
  }

}
