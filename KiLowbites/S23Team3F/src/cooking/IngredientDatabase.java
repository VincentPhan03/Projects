package cooking;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

/**
 * Centralized database for all ingredients able to be used by the user.
 * 
 * Class uses a local serialization method where it stores the serialized map of ingrediends
 * directly into the current working directory, and retreives from the same spot.
 * 
 * 
 * 
 * @author Liam J Herkins
 * @version sprint 2
 */
public class IngredientDatabase implements Serializable
{

  private static final long serialVersionUID = 1L;
  private static Map<String, Ingredient> ingredientMap;
  private static final String LOCATION = "data.Ingredient.txt";

  /**
   * Default constructor for the Ingredient Database class.
   * 
   * It immediately serializes the default ingredients (from the interaction design document of
   * ingredients).
   * 
   * @throws IOException
   *           throws.
   * 
   */
  public IngredientDatabase() throws IOException
  {
    if (ingredientMap == null)
    {
      ingredientMap = new HashMap<String, Ingredient>();
    }

    try
    {
      deserializeMap();
    }
    catch (ClassNotFoundException e)
    {
      // TODO Auto-generated catch block
      defValueAdd();
      serializeMap();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      defValueAdd();
      serializeMap();
    }

    // defValueAdd();
    // serializeMap();

  }

  /**
   * Add's the passed ingredient to the local map of ingredients.
   * 
   * Returns false if the ingredient could not be added.
   * 
   * @param ing
   * @return if it can be added to the map.
   */
  public boolean addIngredient(final Ingredient ing)
  {
    if (ing != null)
    {
      ingredientMap.put(ing.getLabel(), ing);
      return true;
    }
    return false;
  }

  /**
   * This serialize method APPENDS TO THE CONFIG FILE.
   * 
   * @throws IOException
   */
  public static void serializeMap() throws IOException
  {
    FileOutputStream fOut = new FileOutputStream(LOCATION);
    ObjectOutputStream out = new ObjectOutputStream(fOut);
    // for (String e: ingredientMap.keySet()) {
    // out.writeObject(e);
    // }
    // for (Ingredient f : ingredientMap.values()) {
    // out.writeObject(f);
    // }
    out.writeObject(ingredientMap);
    out.flush();
    out.close();

  }

  /**
   * Returns the most current iteration of the .txt file representation of the map.
   * 
   * @throws IOException
   * @throws ClassNotFoundException
   * 
   * @return the hashmap retreived from the file
   */
  @SuppressWarnings("unchecked")
  public static Map<String, Ingredient> deserializeMap() throws IOException, ClassNotFoundException
  {
    ObjectInputStream in = new ObjectInputStream(new FileInputStream(LOCATION));
    ingredientMap = (Map<String, Ingredient>) in.readObject();
    in.close();
    return ingredientMap;
  }

  /**
   * Method just initializes the map to that of the default values. If attempting to just serialize
   * the default options, call this, then serialize.
   */
  public static void defValueAdd()
  {
    Food[] val = Food.values();
    ingredientMap = new HashMap<String, Ingredient>();
    for (int i = 0; i < val.length; i++)
    {
      ingredientMap.put(val[i].toString(), new Ingredient(val[i].name(), val[i].getCalories(),
          val[i].getMeasure(), val[i].isVolumetric()));
    }
    ingredientMap.put("default", new Ingredient("", 0.0, 0.0, false));

  }

  /**
   * Resets the contents of the database (ingredients.txt), utility function.
   * 
   * 
   */
  public static void textReset()
  {
    try
    {
      // Create a FileWriter object with the file name and set "false" to overwrite
      FileWriter writer = new FileWriter(LOCATION, false);

      // Write an empty string to the file to reset it
      writer.write("");

      // Close the writer
      writer.close();
    }
    catch (IOException e)
    {
      System.err.println("Error resetting file: " + e.getMessage());
    }
  }

  /**
   * Returns the current instance of the Ingredient Map (HashMap).
   * @return current hashmap.
   */
  public Map<String, Ingredient> getMap()
  {
    return ingredientMap;
  }

  /**
   * Mutator method for the ingredient map which is used to serialize the ingredients list.
   * 
   * @param ingMap
   *          to assign to ingredient map.
   */
  public void setMap(final HashMap<String, Ingredient> ingMap)
  {
    ingredientMap = ingMap;
  }

}
