package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cooking.DecentralizedDatabase;
import cooking.Meal;
import cooking.NameFilePair;
import cooking.Recipe;

/**
 * Meal manager handles the functionality for MealEditor.
 * 
 * @author phanvm
 * @version Sprint 3
 *
 *          This work complies with JMU's honor code.
 */
public class MealManager
{
  private File currentFile;
  private DecentralizedDatabase db;
  private Meal currentMeal;
  private String empty = " ";

  /**
   * Handles the functionality for MealEditorPanel.
   */
  public MealManager()
  {
    currentMeal = new Meal(empty, empty);
    db = new DecentralizedDatabase();
  }

  /**
   * Opens the given file.
   * 
   * @param file
   *          The file to open.
   * 
   * @throws IOException
   * @throws ClassNotFoundException
   * 
   * @return Meal The meal being opened.
   */
  public Meal openFile(final File file) throws IOException, ClassNotFoundException
  {
    currentFile = file;

    FileInputStream fileInput = new FileInputStream(currentFile);
    ObjectInputStream objectInput = new ObjectInputStream(fileInput);

    currentMeal = (Meal) objectInput.readObject();

    objectInput.close();
    return currentMeal;
  }

  /**
   * Saves the current file.
   * 
   * @throws IOException
   *           if file cannot be found.
   */
  public void saveFile() throws IOException
  {
    if (currentFile == null)
    {
      throw new FileNotFoundException();
    }
    try
    {
      db.loadMe();
    }
    catch (ClassNotFoundException | NullPointerException | IOException e)
    {
      e.printStackTrace();
    }
    FileOutputStream fileOutput = new FileOutputStream(currentFile);
    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

    objectOutput.writeObject(currentMeal);

    db.addMeal(new NameFilePair(currentMeal.getName(), currentFile.getPath()));
    db.saveMe();
    objectOutput.close();
  }

  /**
   * Saves the current file with specified name.
   * 
   * @param file
   * @throws IOException
   */
  public void saveFileAs(final File file) throws IOException
  {
    currentFile = file;
    saveFile();
  }

  /**
   * Prompts the user to add a .rcp file from their file directory.
   * 
   * @param file
   *          The current file to open.
   * 
   * @return The name of the recipe.
   */
  public String addRecipe(final File file)
  {
    ObjectInputStream in = null;
    try
    {
      in = new ObjectInputStream(new FileInputStream(file));
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }

    Recipe toAdd = null;
    try
    {
      toAdd = (Recipe) in.readObject();
    }
    catch (ClassNotFoundException | IOException e2)
    {
      e2.printStackTrace();
    }

    currentMeal.addRecipe(toAdd);
    return toAdd.getName();

  }

  /**
   * Setter method, sets the name of a meal.
   * 
   * @param name
   *          The name to set the meal too.
   */
  public void setName(final String name)
  {
    currentMeal.setName(name);
  }

  /**
   * Removes a recipe from a meal.
   * 
   * @param name
   *          The name of the meal.
   * 
   * @return Recipe The recipe that was removed.
   */
  public Recipe removeRecipe(final String name)
  {
    ArrayList<Recipe> tempList = currentMeal.getRecipies();
    Recipe temp = null;

    for (int i = 0; i < tempList.size(); i++)
    {
      if (tempList.get(i).getName().equals(name))
      {
        temp = tempList.get(i);
      }
    }

    return currentMeal.removeRecipe(temp);
  }

}
