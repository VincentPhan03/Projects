/**
 * 
 */
package utilities;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import cooking.AbstractCook;
import cooking.DecentralizedDatabase;
import cooking.Ingredient;
import cooking.IngredientDatabase;
import cooking.NameFilePair;
import cooking.Recipe;
import cooking.Step;
import cooking.Utensil;
import gui.AddIngredient;
import gui.IngredientsPanel;
import gui.RecipeEditor;
import gui.RecipeEditorPanel;
import gui.StepsPanel;
import gui.Substitution;
import gui.UtensilsPanel;

/**
 * @author Cole T. Strubhar
 * @version 4/4/2023
 *
 *          RecipeManager
 */
public class RecipeManager
{

  private File currentFile;

  private Recipe currentRecipe;

  private UtensilsPanel utensilsPanel;

  private IngredientsPanel ingredientsPanel;

  private StepsPanel stepsPanel;

  private RecipeEditorPanel recipeEditorPanel;

  public void addUtensilsPanel(UtensilsPanel up)
  {
    utensilsPanel = up;
  }

  public void addIngredientsPanel(IngredientsPanel ip)
  {
    ingredientsPanel = ip;
  }

  public void addStepsPanel(StepsPanel sp)
  {
    stepsPanel = sp;
  }

  public void openFile(File file) throws IOException, ClassNotFoundException
  {
    currentFile = file;

    FileInputStream fileInput = new FileInputStream(currentFile);
    ObjectInputStream objectInput = new ObjectInputStream(fileInput);
    currentRecipe = (Recipe) objectInput.readObject();
    

    DefaultListModel<String> up = utensilsPanel.getUtensil();
    DefaultListModel<String> ip = ingredientsPanel.getIngredient();
    ArrayList<Utensil> utensilList = currentRecipe.getUtensils();
    ArrayList<Ingredient> ingredientsList = currentRecipe.getIngredients();

    for (Utensil utensil : utensilList)
    {
      up.add(0, utensil.getName());
    }

    for (Ingredient ingredient : ingredientsList)
    {
      if (ingredient.hasSubstitutes())
      {
        String ingString = String.format("%s ", ingredient.getLabel());
        for (Ingredient substitute : ingredient.getSubstitutes())
        {
          ingString += String.format("or %s ", substitute.getLabel());
        }

        ip.add(0, ingString);
      }
      else
      {
        ip.add(0, ingredient.getLabel());
      }
    }

    populateSteps();
  }

  public void populateSteps()
  {

    ArrayList<Utensil> utensils = currentRecipe.getUtensils();
    DefaultListModel<String> steps = stepsPanel.getSteps();
    LinkedList<Step> moreSteps = currentRecipe.getSteps();
    ArrayList<Ingredient> ingredients = currentRecipe.getIngredients();

    stepsPanel.removeUtensils();
    stepsPanel.removeOns();
    stepsPanel.removeEntries();

    for (Utensil utensil : utensils)
    {
      stepsPanel.addUtensil(utensil.getName());
      stepsPanel.addAction(utensil.getDetails());
    }

    for (Ingredient ingredient : ingredients)
    {
      stepsPanel.addOn(ingredient.getLabel());
    }

    for (Utensil utensil : utensils)
    {
      stepsPanel.addOn(utensil.getName());
    }

    for (Step step : moreSteps)
    {
      steps.add(steps.size(), step.stepToString());
      /*
      if (step.getOn() instanceof Ingredient)
      {
        steps.add(0, step.getLocalAction() + " " + ((Ingredient) step.getOn()).getLabel() + " with "
            + ((Utensil) step.getTo()).getName());
      } else {
        steps.add(0, step.getLocalAction() + " " + ((Utensil) step.getOn()).getName() + " with "
            + ((Utensil) step.getTo()).getName());
      }*/
    }
  }

  public void saveFile() throws IOException
  {
    if (currentFile == null)
    {
      throw new FileNotFoundException();
    }

    FileOutputStream fileOutput = new FileOutputStream(currentFile);
    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
    objectOutput.writeObject(currentRecipe);

    
  }
  
  /**
   * 
   * @param file
   * @throws IOException
   */
  public void saveFileAs(File file) throws IOException
  {
    currentFile = file;
    // saveFile();

    if (currentFile == null)
    {
      throw new FileNotFoundException();
    }

    FileOutputStream fileOutput = new FileOutputStream(currentFile);
    ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
    objectOutput.writeObject(currentRecipe);

    DecentralizedDatabase db = new DecentralizedDatabase();
    try
    {
      db.loadMe();
    }
    catch (ClassNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NullPointerException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   
    db.addRecipe(new NameFilePair(currentRecipe.getName(), currentFile.getPath()));
    db.saveMe();
  }

  public void addReceipe()
  {
    currentRecipe = new Recipe("", "");


  }

  public void setName(String name)
  {
    currentRecipe.setName(name);

  }

  public void setServings(int servings)
  {
    currentRecipe.setServingSize(servings);
  }

  public boolean addUtensil(String name, String details)
  {
    currentRecipe.addUtensil(name, details);
    stepsPanel.addAction(details);
    stepsPanel.addUtensil(name);
    populateSteps();
    return true;
  }
  
  /**
   * 
   * @param name
   */
  public void removeUtensil(String name)
  {
    ArrayList<Utensil> utensils = currentRecipe.getUtensils();


    int i = 0;
    String action = "Not Found";
    while (i < utensils.size())
    {
      if (utensils.get(i).getName().equals(name))
      {
        action = utensils.get(i).getDetails();
        i = utensils.size();
      }
      i++;
    }

    currentRecipe.removeUtensil(name);
    stepsPanel.removeUtensil(name);


    stepsPanel.removeAction(action);

  }

  public boolean addIngredient(String name, String detail, Double amount, String units)
      throws IOException, ClassNotFoundException
  {
    if (name == null || name.equals(""))
    {
      return false;
    }

    IngredientDatabase id = new IngredientDatabase();
    Ingredient tempIngredient = new Ingredient("", 0.0, 0.0, false);
    // IngredientDatabase.defValueAdd();
    // IngredientDatabase.serializeMap();

    Map<String, Ingredient> ingredientsMap = IngredientDatabase.deserializeMap();
    Set<String> ingredientsSet = ingredientsMap.keySet();



    boolean found = false;

    for (String key : ingredientsSet)
    {
      if (ingredientsMap.get(key).getLabel().equalsIgnoreCase(name))
      {
        found = true;
      }
    }

    if (!found)
    {
      new AddIngredient();
      return false;
    }

    tempIngredient = ingredientsMap.get(name);
    tempIngredient.setUnits(units);
    tempIngredient.setAmount(amount);

    currentRecipe.addIngredient(tempIngredient);
    stepsPanel.addOn(name);

    return true;
  }

  public void removeIngredient(String name)
  {
    currentRecipe.removeIngredient(name);
    stepsPanel.removeOn(name);
  }

  public void addStep(String action, String details, String utensil, String ingredient)
  {
    ArrayList<Utensil> utensils = currentRecipe.getUtensils();

    Utensil foundUtensil = null;
    Utensil foundOtherUtensil = null;

    for (Utensil u : utensils)
    {
      if (u.getName().equals(utensil))
      {
        foundUtensil = u;
       
      }

      if (u.getName().equals(ingredient))
      {
        foundOtherUtensil = u;
      }
    }

    ArrayList<Ingredient> ingredients = currentRecipe.getIngredients();

    Ingredient foundIngredient = null;

    if (foundOtherUtensil == null)
    {
      for (Ingredient i : ingredients)
      {
        if (i.getLabel().equals(ingredient))
        {
          foundIngredient = i;
        }
      }

      currentRecipe.addStep(action, details, foundUtensil, new Utensil("none", "none"),
          foundIngredient);
    }
    else
    {
      currentRecipe.addStep(action, details, foundUtensil, new Utensil("none", "none"),
          foundOtherUtensil);
    }

  }

  public void deleteStep(String action, String details, String utensil, String ingredient)
  {
    ArrayList<Utensil> utensils = currentRecipe.getUtensils();

    Utensil foundUtensil = null;

    for (Utensil u : utensils)
    {
      if (u.getName().equals(utensil))
      {
        foundUtensil = u;
      }
    }

    ArrayList<Ingredient> ingredients = currentRecipe.getIngredients();

    Ingredient foundIngredient = null;

    for (Ingredient i : ingredients)
    {
      if (i.getLabel().equals(ingredient))
      {
        foundIngredient = i;
      }
    }

    if (details == null)
    {
      details = "";
    }

    currentRecipe.removeStep(action, details, foundUtensil, foundIngredient);
  }
  
  /**
   * 
   */
  public void substitutionDesired()
  {
    // new Substitution(currentRecipe, this, ingredientsPanel.getNameInput().getText());

  }
  
  /**
   * 
   * @param subString
   */
  public void addSubstitution(final String subString)
  {
    ArrayList<Ingredient> ingredients = currentRecipe.getIngredients();
    Ingredient neededOne = null;
    Ingredient neededTwo = null;


    int i = 0;
    while (i < ingredients.size())
    {
   
      if (ingredients.get(i).getLabel().equals(subString))
      {
        neededOne = ingredients.get(i);
        i = ingredients.size();
      }
      i++;
    }
    try
    {
      /*
       * addIngredient(ingredientsPanel.nameInput.getText(), ingredientsPanel.detailInput.getText(),
       * Double.valueOf(ingredientsPanel.amountInput.getText()), (String)
       * ingredientsPanel.unitInput.getSelectedItem());
       */

      // neededOne.addSubstitution(neededTwo);

      ingredients = currentRecipe.getIngredients();

      i = 0;
      while (i < ingredients.size())
      {
        if (ingredients.get(i).getLabel().equals(ingredientsPanel.nameInput.getText()))
        {
          neededTwo = ingredients.get(i);
          i = ingredients.size();
        }
        i++;
      }

      neededOne.addSubstitution(neededTwo);

    }
    catch (NumberFormatException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    /*
     * catch (ClassNotFoundException e) { // TODO Auto-generated catch block e.printStackTrace(); }
     * catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
     */
  }

  public void substitutionSelected(String subFor)
  {
    // ingredientsPanel.getIngredientsList();
    DefaultListModel<String> ingredientList = ingredientsPanel.getIngredient();
    ingredientsPanel.removeAll();
    /*
     * for (int i = 0; i < ingredientList.getSize(); i++) { if (ingredientsList) }
     */

  }

  public Recipe getCurrentRecipe()
  {
    return currentRecipe;
  }

  public void printSteps()
  {

    LinkedList<Step> mySteps = currentRecipe.getSteps();
    for (Step step : mySteps)
    {
   //   System.out.println(step.getLocalAction());
    }
 //   System.out.println("<\\Steps>");
  }

}

/*
 * public static void main(String[] args) { Recipe r = new Recipe("test", 0, null, null, null);
 * currentRecipe = r; RecipeEditor.main(args); }
 */
