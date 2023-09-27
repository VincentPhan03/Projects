package cooking;

import java.io.Serializable;

/**
 * @author Cole T. Strubhar
 * @version 4/6/2023
 *
 */
public class NameFilePair implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String name;

  private String filePath;

  /**
   * Constructor for NameFilePair.
   * 
   * @param name
   *          The name of the file.
   * @param filePath
   *          The name of the filePath.
   */
  public NameFilePair(final String name, final String filePath)
  {
    this.name = name;
    this.filePath = filePath;
  }

  /**
   * Setter method for name.
   * 
   * @param name
   *          The name to set this.name too.
   */
  public void setName(final String name)
  {
    this.name = name;
  }

  /**
   * Setter method for filePath.
   * 
   * @param filePath
   *          The name of the filePath.
   */
  public void setFilePath(final String filePath)
  {
    this.filePath = filePath;
  }

  /**
   * Getter method for name.
   * 
   * @return String The name.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Getter method for filePath.
   * 
   * @return String The filePath.
   */
  public String getFilePath()
  {
    return filePath;
  }
}
