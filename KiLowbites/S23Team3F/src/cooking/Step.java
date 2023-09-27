package cooking;

import java.io.Serializable;

/**
 * A step is the actions taken with the ingredients and utensils.
 * 
 * @author Liam J Herkins, Cole T. Strubhar
 * @version Sprint 1
 * 
 * 
 *          This work complies with JMU's Honor Code.
 */

public class Step extends AbstractCook implements Serializable
{

  private static final long serialVersionUID = 1L;
  private Object on;
  private String action;

  private Utensil utensilTo;
  private double time;

  /**
   * The constructor for Step.
   * 
   * @param action
   *          The action to take.
   * @param details
   *          The details about the step.
   * 
   * @param on
   *          The "On" field can contain either an ingredient from the ingredient list or the
   *          contents of a (source) utensil from the utensils list.
   * 
   * 
   * @param utensilTo
   *          The utensilTo field is the utensil which is receiving the action of the step.
   * 
   */
  public Step(final String action, final String details, final Object on, final Utensil utensilTo)
  {
    super(details, details);
    // super(action, details);
    this.action = action;

    if (on instanceof Ingredient)
    {
      this.on = (Ingredient) on;
    }
    else if (on instanceof Utensil)
    {
      this.on = (Utensil) on;
    }
    else
    {
      throw new IllegalArgumentException();
    }
    
    this.utensilTo = utensilTo;

  }

  /**
   * Acessor method for the action (string) performed in this step.
   * 
   * @return action to be performed.
   */
  public String getLocalAction()
  {
    return this.action;
  }

  /**
   * Accessor method for the on (Ingredient object or Utensil object).
   * 
   * @return current instance of On.
   */
  public Object getOn()
  {
    return this.on;
  }

  /**
   * When changing the on (Ingredient on Utensil) user can pass in a generic object where it will be
   * checked for if it is a instance of our target objects, then set the local attribute of on to
   * the passed argument.
   * 
   * @param onObj
   *          object to be assigned to this.on.
   */
  public void setOn(final Object onObj)
  {
    if (onObj != null)
    {
      if (onObj instanceof Ingredient)
      {
        this.on = (Ingredient) onObj;
      }
      else if (onObj instanceof Utensil)
      {
        this.on = (Utensil) onObj;
      }
      else
      {
        throw new IllegalArgumentException();
      }
    }
    else
    {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Mutator method for the details attribute (String) inherited from the abstract cook class.
   * 
   * @param details
   *          to be assigned.
   */
  public void setDetails(final String details)
  {
    super.setDetails(details);
  }

  /**
   * Method for converting the on (Ingredient or Utensil) into a string based upon their own
   * toString methods.
   * 
   * @param onOp
   *          to be converted to a formatted string.
   * @return res result of the string formatting.
   */
  public String onName(final Object onOp)
  {
    if (onOp == null)
    {
      throw new IllegalArgumentException();
    }
    String res = "";
    Ingredient temp = null;
    Utensil tempU = null;
    if (onOp instanceof Ingredient)
    {
      temp = (Ingredient) onOp;
      res = temp.getLabel();
    }
    else if (onOp instanceof Utensil)
    {
      tempU = (Utensil) onOp;
      res = tempU.getName();
    }
    else
    {
      throw new IllegalArgumentException();
    }
    return res;
  }

  /**
   * Acessor method (Utensil) for the utensil which is receiving the action/contents.
   * 
   * @return current utensilTo
   */
  public Utensil getTo()
  {
    return this.utensilTo;
  }

  /**
   * To string method for the step class.
   * 
   * The contents of the formatted string are dependent on whether or not the contents of from and
   * to are the same.
   * 
   * @return formatted String.
   */
  public String stepToString()
  {

    if (getOn().equals(getTo()))
    {
      return String.format("%s the contents of the %s %s", getLocalAction(), onName(this.on),
          getDetails());
    }
    else
    {
      return String.format("%s the contents of the %s on the %s %s", getLocalAction(),
          onName(this.on), getTo().getName(), getDetails());
    }
  }

  /**
   * Accessor method for the name attribute (String).
   * 
   * @return name of to object.
   */
  public String getToName()
  {
    return this.utensilTo.getName();
  }

  /**
   * Mutator method for the time to complete this step (double).
   * 
   * @param time
   *          to set the time to.
   */
  public void setTime(final double time)
  {
    if (time >= 0.1)
      this.time = time;
    else
      this.time = 0.0;
  }

  /**
   * Accessor method for time attribute (double).
   * 
   * @return current instance of time.
   */
  public double getTime()
  {
    return this.time;
  }

  /**
   * Accessor method for the details attribute (String).
   * 
   * @return current details of the step.
   */
  public String getDetails()
  {
    return super.getDetails();
  }

}
