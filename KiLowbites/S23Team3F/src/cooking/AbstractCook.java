package cooking;

import java.io.Serializable;
import java.util.Objects;

/**
 * AbstractCook class.
 * 
 * @author phanvm
 * @version Sprint 1
 * 
 *          This work complies with JMU's honor code.
 */
public abstract class AbstractCook implements Preperation, Serializable
{

  private static final long serialVersionUID = 1L;
  private String name;
  private String details;

  /**
   * Constructor for AbstractCook.
   * 
   * @param name
   *          The name.
   * @param details
   *          The details
   */
  public AbstractCook(final String name, final String details)
  {
    if (name != null)
    {
      this.name = name;
    }
    else
    {
      this.name = "";
    }
    if (details != null)
    {
      this.details = details;
    }
    else
    {
      this.details = "";
    }

  }

  /**
   * Accessor method for the name attribute.
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * 
   */
  @Override
  public void setName(final String name)
  {
    if (!Objects.isNull(name) && name.length() > 0)
    {
      this.name = name;
    }
    else
    {
      this.name = "";
    }

  }

  /**
   * 
   */
  @Override
  public String getDetails()
  {
    return details;
  }

  /**
   * 
   */
  @Override
  public void setDetails(final String details)
  {
    if (!Objects.isNull(details))
    {
      this.details = details;
    }
    else
    {
      throw new NullPointerException();
    }
  }

}
