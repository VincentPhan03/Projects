package cooking;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileOutputStream;

/**
 * @author Cole T. Strubhar
 * @version 4/12/2023
 * 
 *          Database which provides reference to all the recipe and meal files stored on users
 *          computer. Name and file paths are saved for the reference to files.
 */
public class DecentralizedDatabase implements Serializable
{

  public static final int SEARCH_BY_NAME = 0;
  public static final int SEARCH_BY_FILE_PATH = 1;
  private static final String SAVE_STRING = "data.pairs.db";
  private static final long serialVersionUID = 1L;
  
  private boolean loaded;

  private ArrayList<NameFilePair> mealNameFilePairs;
  private ArrayList<NameFilePair> recipeNameFilePairs;

  /**
   * DecentralizedDatabase - Creates a new DecentralizedDatabase with empty ArrayLists.
   */
  public DecentralizedDatabase()
  {
    mealNameFilePairs = new ArrayList<>();
    recipeNameFilePairs = new ArrayList<>();
  }

  /**
   * saveMe() - Serializes this DecentralizedDatabase and saves it to file called "data.pairs.db".
   * 
   * @throws IOException
   */
  public void saveMe() throws IOException
  {
//    if (!loaded)
//    {
//      System.out.println("HAVE NOT LOADED!!!!");
//    }

    File file = new File(SAVE_STRING);
    FileOutputStream fos = new FileOutputStream(file);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    loaded = false;

    oos.writeObject(this);
    oos.close();
  }

  /**
   * loadMe() Updates this DecentralizedDatabase by deserializing the newest previous version of
   * DecentralizedDatabase and replacing this instance's ArrayLists with the previous'.
   * 
   * @throws ClassNotFoundException
   * @throws IOException
   * @throws NullPointerException
   */
  public void loadMe() throws ClassNotFoundException, IOException, NullPointerException
  {
    File file = new File(SAVE_STRING);
    try
    {
      FileInputStream fis = new FileInputStream(file);
      ObjectInputStream ois = new ObjectInputStream(fis);

      DecentralizedDatabase tempDB = (DecentralizedDatabase) ois.readObject();
      ArrayList<NameFilePair> tempMealNameFilePairs = tempDB.getMealNameFilePairs();
      ArrayList<NameFilePair> tempRecipeNameFilePairs = tempDB.getRecipeNameFilePairs();

      this.setMealNameFilePairs(tempMealNameFilePairs);
      this.setRecipeNameFilePairs(tempRecipeNameFilePairs);
      ois.close();
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
      loadMe();
      // throw new NullPointerException();
    }
    catch (ClassNotFoundException e)
    {
      
      saveMe();
      loadMe();
    }
    catch (IOException e)
    {
      
      e.printStackTrace();
      saveMe();
      loadMe();
    }
    loaded = true;
  }

  /**
   * addMeal(NameFilePair) - Adds a NameFilePair to the mealNameFilePairs array.
   * 
   * @param mealPair
   *          - NameFilePair to be added to mealNameFilePairs.
   */
  public void addMeal(final NameFilePair mealPair)
  {
    mealNameFilePairs.add(0, mealPair);
  }

  /**
   * addRecipe(NameFilePair) Adds a NameFilePair to the recipeNameFilePairs array.
   * 
   * @param recipePair
   *          NameFilePair to be added to recipeNameFilePairs.
   */
  public void addRecipe(final NameFilePair recipePair)
  {
    recipeNameFilePairs.add(0, recipePair);
  }

  /**
   * removeMeal(int, String) - Removes a meal with either the given file path or name.
   * 
   * @param searchType
   *          - The type of information used to identify the meal to be removed.
   * @param literal
   *          - The information used to identify the meal to be removed.
   */
  public void removeMeal(final int searchType, final String literal)
  {
    int i = 0;

    if (searchType == SEARCH_BY_FILE_PATH)
    {
      while (i < mealNameFilePairs.size())
      {
        if (mealNameFilePairs.get(i).getFilePath().equals(literal))
        {
          mealNameFilePairs.remove(i);
          i = mealNameFilePairs.size();
        }
        i++;
      }
    }

    if (searchType == SEARCH_BY_NAME)
    {
      while (i < mealNameFilePairs.size())
      {
        if (mealNameFilePairs.get(i).getName().equals(literal))
        {
          mealNameFilePairs.remove(i);
          i = mealNameFilePairs.size();
        }
        i++;
      }
    }
  }

  /**
   * removeRecipe(int, String) - Removes a recipe with either the given file path or name.
   * 
   * @param searchType
   *          - The type of information used to identify the recipe to be removed.
   * @param literal
   *          - The information used to identify the recipe to be removed.
   */
  public void removeRecipe(final int searchType, final String literal)
  {
    int i = 0;

    if (searchType == SEARCH_BY_FILE_PATH)
    {
      while (i < recipeNameFilePairs.size())
      {
        if (recipeNameFilePairs.get(i).getFilePath().equals(literal))
        {
          recipeNameFilePairs.remove(i);
          i = recipeNameFilePairs.size();
        }
        i++;
      }
    }

    if (searchType == SEARCH_BY_NAME)
    {
      while (i < recipeNameFilePairs.size())
      {
        if (recipeNameFilePairs.get(i).getName().equals(literal))
        {
          recipeNameFilePairs.remove(i);
          i = recipeNameFilePairs.size();
        }
        i++;
      }
    }
  }

  /**
   * getMeal(String) - Returns the Meal object identified by the name specified with @param name.
   * 
   * @param name
   *          - Name of meal desired to be returned.
   * @return - Desired meal object.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public Meal getMeal(final String name) throws IOException, ClassNotFoundException
  {
    int i = 0;
    File readFile = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    Meal retMeal = null;
    while (i < mealNameFilePairs.size())
    {
      if (mealNameFilePairs.get(i).getName().equals(name))
      {
        readFile = new File(mealNameFilePairs.get(i).getFilePath());
        fis = new FileInputStream(readFile);
        ois = new ObjectInputStream(fis);

        retMeal = (Meal) ois.readObject();
        i = mealNameFilePairs.size();
      }
    }
    return retMeal;
  }

  /**
   * getRecipe(String) - Returns the recipe object identified by the name specified with @param
   * name.
   * 
   * @param name
   *          - Name of recipe desired to be returned.
   * @return - Desired recipe object.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public Recipe getRecipe(final String name) throws IOException, ClassNotFoundException
  {
    File file = null;
    FileInputStream fis = null;
    ObjectInputStream oos = null;
    Recipe retRecipe = null;

    int i = 0;

    while (i < recipeNameFilePairs.size())
    {
      if (recipeNameFilePairs.get(i).getName().equals(name))
      {
        file = new File(recipeNameFilePairs.get(i).getFilePath());
        fis = new FileInputStream(file);
        oos = new ObjectInputStream(fis);
        retRecipe = (Recipe) oos.readObject();
        i = recipeNameFilePairs.size();
      }
    }

    return retRecipe;
  }

  /**
   * getAllRecipies() - Provides all Recipe objects referenced in recipeNameFilePairs.
   * 
   * @return - All recipes referenced in recipeNameFilePairs.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public ArrayList<Recipe> getAllRecipes() throws IOException, ClassNotFoundException
  {
    ArrayList<Recipe> retAL = new ArrayList<>();
    File currFile = null;
    FileInputStream currFIS = null;
    ObjectInputStream currOIS = null;
    Recipe currRecipe = null;

    for (NameFilePair recipePair : recipeNameFilePairs)
    {
      currFile = new File(recipePair.getFilePath());
      currFIS = new FileInputStream(currFile);
      currOIS = new ObjectInputStream(currFIS);

      currRecipe = (Recipe) currOIS.readObject();
      retAL.add(currRecipe);
    }

    return retAL;
  }

  /**
   * getAllMeals() - Provides all Recipe objects referenced in mealsNameFilePairs.
   * 
   * @return - All meals referenced in mealNameFilePairs.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public ArrayList<Meal> getAllMeals() throws IOException, ClassNotFoundException
  {
    ArrayList<Meal> retAL = new ArrayList<>();
    File currFile = null;
    FileInputStream currFIS = null;
    ObjectInputStream currOIS = null;
    Meal currMeal = null;

    for (NameFilePair mealPair : mealNameFilePairs)
    {
      currFile = new File(mealPair.getFilePath());
      currFIS = new FileInputStream(currFile);
      currOIS = new ObjectInputStream(currFIS);

      currMeal = (Meal) currOIS.readObject();
      retAL.add(currMeal);
    }

    return retAL;
  }

  /**
   * getRecipiesWithoutIngredients(ArrayList<String>) - Provides all referenced recipe objects which
   * do not contain the unwanted ingredients identified by their names/labels in.
   * 
   * @param unwanted
   *          - Names of ingredients which are not desired.
   * @return - ArrayList<Recipe> of all referenced recipes without given ingredients.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public ArrayList<Recipe> getRecipesWithoutIngredients(final ArrayList<String> unwanted)
      throws IOException, ClassNotFoundException
  {
    ArrayList<Recipe> retArr = new ArrayList<>();
    File currFile = null;
    FileInputStream currFIS = null;
    ObjectInputStream currOIS = null;
    Recipe currRecipe = null;
    ArrayList<Ingredient> currIngredients = null;
    boolean add = true;
    int i = 0;
    int j = 0;

    for (NameFilePair recipePath : recipeNameFilePairs)
    {
      i = 0;
      add = true;
      currFile = new File(recipePath.getFilePath());
      currFIS = new FileInputStream(currFile);
      currOIS = new ObjectInputStream(currFIS);
      currRecipe = (Recipe) currOIS.readObject();
      currIngredients = currRecipe.getIngredients();

      while (i < currIngredients.size())
      {
        j = 0;
        while (j < unwanted.size())
        {
          if (currIngredients.get(i).getLabel().equals(unwanted.get(j)))
          {
            j = unwanted.size();
            i = currIngredients.size();
            add = false;
          }
          j++;
        }
        i++;
      }
      if (add)
      {
        retArr.add(currRecipe);
      }
    }

    return retArr;
  }

  /**
   * getRecipesWithIngredients(ArrayList<String>) - Provides all referenced recipe objects which
   * contain the ingredients referenced by their names in @param wanted.
   * 
   * @param wanted
   *          - ArrayList<String> including Ingredients which are desired to be contained within
   *          each meal referenced by their names.
   * 
   * @return - ArrayList<Recipe> Ingredient objects which contain desired ingredients.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public ArrayList<Recipe> getRecipesWithIngredients(final ArrayList<String> wanted)
      throws IOException, ClassNotFoundException
  {


    boolean[] templateArr = new boolean[wanted.size()];

    for (int i = 0; i < templateArr.length; i++)
    {
      templateArr[i] = false;
    }

    boolean[] wantedFulArr = templateArr;
    ArrayList<Recipe> retArr = new ArrayList<>();
    File currFile = null;
    FileInputStream currFIS = null;
    ObjectInputStream currOIS = null;
    Recipe currRecipe = null;
    ArrayList<Ingredient> currIngredients = null;
    int i = 0;
    int j = 0;

    for (NameFilePair recipePath : recipeNameFilePairs)
    {
      i = 0;
      wantedFulArr = templateArr;
      currFile = new File(recipePath.getFilePath());
      currFIS = new FileInputStream(currFile);
      currOIS = new ObjectInputStream(currFIS);
      currRecipe = (Recipe) currOIS.readObject();
      currIngredients = currRecipe.getIngredients();

      while (i < currIngredients.size())
      {
        j = 0;
        while (j < wanted.size())
        {
          if (wanted.get(j).equals(currIngredients.get(i).getLabel()))
          {
            wantedFulArr[j] = true;
          }
          j++;
        }
        i++;
      }
      if (allThere(wantedFulArr))
      {
        
        retArr.add(currRecipe);
      }
      wantedFulArr = templateArr;
    }

    // Temporary Return
    return retArr;
  }

  /**
   * getMealsWithoutIngredients(ArrayList<String>) - Provides all referenced Meal objects which do
   * not contain the unwanted ingredients, identified by their names/labels in.
   * 
   * @param unwanted
   *          - ArrayList<String> including Names of ingredients which are not desired.
   * @return - ArrayList<Meal> of all referenced recipes without given ingredients.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public ArrayList<Meal> getMealsWithoutIngredients(final ArrayList<String> unwanted)
      throws IOException, ClassNotFoundException
  {
    ArrayList<Recipe> recipesArr = getRecipesWithoutIngredients(unwanted);
    ArrayList<Meal> retArr = new ArrayList<>();
    File currFile = null;
    FileInputStream currFIS = null;
    ObjectInputStream currOIS = null;
    Meal currMeal = null;

    for (NameFilePair mealPath : mealNameFilePairs)
    {
      currFile = new File(mealPath.getFilePath());
      currFIS = new FileInputStream(currFile);
      currOIS = new ObjectInputStream(currFIS);
      currMeal = (Meal) currOIS.readObject();

      if (containsSameRecipes(currMeal.getRecipies(), recipesArr))
      {
        retArr.add(currMeal);
      }
    }

    return retArr;
  }

  /**
   * getMealsWithoutIngredients(ArrayList<String>) - Provides all referenced Meal objects which
   * contain the unwanted ingredients, identified by their names/labels in.
   * 
   * @param wanted
   *          - ArrayList<String> including Names of ingredients which are desired.
   * @return - ArrayList<Meal> of all referenced recipes with given ingredients.
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public ArrayList<Meal> getMealsWithIngredients(final ArrayList<String> wanted)
      throws IOException, ClassNotFoundException
  {
    ArrayList<Recipe> recipesArr = getRecipesWithIngredients(wanted);
    ArrayList<Meal> retArr = new ArrayList<>();
    File currFile = null;
    FileInputStream currFIS = null;
    ObjectInputStream currOIS = null;
    Meal currMeal = null;

    for (NameFilePair mealPath : mealNameFilePairs)
    {
      currFile = new File(mealPath.getFilePath());
      currFIS = new FileInputStream(currFile);
      currOIS = new ObjectInputStream(currFIS);
      currMeal = (Meal) currOIS.readObject();

      if (containsSameRecipes(currMeal.getRecipies(), recipesArr))
      {
        retArr.add(currMeal);
      }
    }

    return retArr;
  }

  /**
   * allThere(boolean[]) - Private method to determine if a boolean[] has all true values.
   * 
   * @param toTest
   *          - boolean[] to be checked.
   * @return - boolean representing if the boolean[] has all true values.
   */
  private boolean allThere(final boolean[] toTest)
  {
    for (boolean bool : toTest)
    {
      if (!bool)
      {
        return false;
      }
    }
    return true;
  }

  /**
   * containsSameRecipies(ArrayList<Recipe>, ArrayList<Recipe>)Method determining whether two
   * ArrayList<Recipies>s contain the same recipe.
   * 
   * @param arrOne
   *          - ArrayList<Recipe> to be compared with the other.
   * @param arrTwo
   *          - ArrayList<Recipe> to be compared with the other.
   * @return - Boolean representing whether the two Arrays contain the same recipies.
   */
  public boolean containsSameRecipes(final ArrayList<Recipe> arrOne, final ArrayList<Recipe> arrTwo)
  {
    

    for (Recipe itemOne : arrOne)
    {
      for (Recipe itemTwo : arrTwo)
      {
        if (!(itemOne.getName().equals(itemTwo.getName())))
        {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * getMealNameFilePairs() - Method returning an instance's mealNameFilePairs array.
   * 
   * @return - ArrayList<NameFilePair> - Reference to an instance's mealNameFilePairs array.
   */
  public ArrayList<NameFilePair> getMealNameFilePairs()
  {
    return mealNameFilePairs;
  }

  /**
   * getRecipeNameFilePairs() - Method returning an instance's recipeNameFilePairs array.
   * 
   * @return - ArrayList<NameFilePair> - Reference to an instance's recipeNameFilePairs array.
   */
  public ArrayList<NameFilePair> getRecipeNameFilePairs()
  {
    return recipeNameFilePairs;
  }

  /**
   * setMealFilePairs(ArrayList<NameFilePair>) - Sets an instance's mealNameFilePairs array
   * to @param toPut.
   * 
   * @param toPut
   *          - NameFilePair array to set an instance's mealNameFilePair array to.
   */
  public void setMealNameFilePairs(final ArrayList<NameFilePair> toPut)
  {
    this.mealNameFilePairs = toPut;
  }

  /**
   * setRecipeFilePairs(ArrayList<NameFilePair>) - Sets an instance's recipeNameFilePairs array
   * to @param toPut.
   * 
   * @param toPut
   *          - NameFilePair array to set an instance's recipeNameFilePair array to.
   */
  public void setRecipeNameFilePairs(final ArrayList<NameFilePair> toPut)
  {
    this.recipeNameFilePairs = toPut;
  }

}
