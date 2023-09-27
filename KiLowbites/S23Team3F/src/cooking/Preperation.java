package cooking;

/**
 * Preparation interface.
 * 
 * @author phanvm
 * @version Sprint 1
 *
 *          This work complies with JMU's honor code.
 */
public interface Preperation
{
  /**
   * Getter for name.
   * 
   * @return The name
   */
  public String getName();

  /**
   * Setter for name.
   * 
   * @param name
   *          The name.
   */
  public void setName(String name);

  /**
   * Getter for details.
   * 
   * @return The details.
   */
  public String getDetails();

  /**
   * Setter for details.
   * 
   * @param details
   *          The details.
   */
  public void setDetails(String details);
}
