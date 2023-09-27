package cooking;

import java.io.Serializable;

/**
 * A Utensil is the hardware used to cook, i.e. pans, spoons, bowls.
 * 
 * @author phanvm, Liam J Herkins
 * @version Sprint 1
 * 
 *          This work complies with JMU's honor code.
 */
public class Utensil extends AbstractCook implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int utensilCount;

  /**
   * Constructor for Utensil.
   * 
   * @param name
   *          The name of the utensil.
   * @param details
   *          The details of what the utensil does.
   */
  public Utensil(final String name, final String details)
  {
    super(name, details);
    this.utensilCount = 0;
  }

  /**
   * Default constructor for the Utensil class, initializes all attributes to "".
   */
  public Utensil()
  {
    super("", "");
  }

  /**
   * Mutator method simply increments the number of utensils (this current utensil) are needed for
   * the recipe/meal.
   * 
   */
  public void utensilCountSet()
  {
    this.utensilCount++;
  }

  /**
   * Acessor method for the utensil count for this particular instance of utensil.
   * 
   * @return current instance of utensilCount (int).
   */
  public int getUtensilCount()
  {
    return this.utensilCount;
  }
}
