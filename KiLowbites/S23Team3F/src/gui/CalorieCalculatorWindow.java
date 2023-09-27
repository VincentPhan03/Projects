package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cooking.DecentralizedDatabase;
import cooking.Ingredient;
import cooking.IngredientDatabase;
import cooking.Meal;
import cooking.Recipe;
import math.FromUnits;
import math.UnitConversion;

/**
 * CalorieCaluclatorWindow is a window that users can use to calculate the calories of an ingredient
 * using whatever unit of measure they please.
 * 
 * @author Jared Householder
 *
 */
public class CalorieCalculatorWindow extends JFrame implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private JComboBox<String> ingredients;
  private JTextField amount;
  private JComboBox<FromUnits> units;
  @SuppressWarnings("rawtypes")
  private JComboBox recipes;
  private JTextField caloriesRecipe;
  @SuppressWarnings("rawtypes")
  private JComboBox meals;
  private JTextField caloriesMeal;
  private JLabel ingredientsLabel, amountLabel, unitsLabel, caloriesLabel, recipesLabel, mealsLabel,
      caloriesRecipeLabel, caloriesMealLabel;
  private JButton calculate, refresh;
  private Container contentPane;
  private JTextField calories;
  private String blank = " ";
  private String ca = "calculate";
  private String r = "restore";
  private Map<String, Ingredient> x;
  private String doubleFormatter = "%.2f";
  private String colon = ":";

  /**
   * Default Value Constructor, that calls setup layout.
   * 
   * @throws IOException
   * @throws ClassNotFoundException
   *
   */
  public CalorieCalculatorWindow() throws ClassNotFoundException, IOException
  {
    setupLayout();
  }

  /**
   * setupLayout sets the GUI for the Calorie Calculator.
   * 
   * @throws ClassNotFoundException no class found
   * @throws IOException Input / output exception
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void setupLayout() throws ClassNotFoundException, IOException
  {
    setTitle("KiLowBites " + MainWindow.STRINGS.getString("CALORIE_CALCULATOR_TITLE"));
    setSize(800, 300);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    // create calories text field
    calories = new JTextField(10);
    amount = new JTextField(10);
    caloriesRecipe = new JTextField(10);
    caloriesMeal = new JTextField(10);

    // Database to get all meals and recipes

    DecentralizedDatabase db = new DecentralizedDatabase();
    db.loadMe();

    // Combo box for recipes (files);
    recipes = new JComboBox();
    ArrayList<Recipe> re = db.getAllRecipes();
    for (Recipe rec : re)
    {
      recipes.addItem(rec.getName());
    }
    recipes.setSelectedIndex(-1);

    // combo box for meals (files);
    meals = new JComboBox();
    ArrayList<Meal> me = db.getAllMeals();
    for (Meal m : me)
    {
      meals.addItem(m.getName());
    }
    meals.setSelectedIndex(-1);

    // Create Icons / buttons
    Icon calc = new ImageIcon(this.getClass().getResource("/icons/calculate2.png"));
    Icon rest = new ImageIcon(this.getClass().getResource("/icons/restore2.png"));
    calculate = new JButton(calc);
    refresh = new JButton(rest);
    // Make the buttons not have any background stuff
    calculate.setOpaque(false);
    calculate.setContentAreaFilled(false);
    calculate.setBorderPainted(false);
    refresh.setOpaque(false);
    refresh.setContentAreaFilled(false);
    refresh.setBorderPainted(false);
    // Create the actionlisteners
    calculate.setActionCommand(ca);
    calculate.addActionListener(this);
    refresh.setActionCommand(r);
    refresh.addActionListener(this);

    // Put the buttons in a box to add to panel
    Box box = Box.createHorizontalBox();
    box.add(calculate);
    box.add(refresh);
    x = IngredientDatabase.deserializeMap();
    TreeSet<String> t = new TreeSet<String>(x.keySet());
    Object[] o = t.toArray();
    ingredients = new JComboBox<>();
    ingredients.removeAllItems();
    for (Object k : o)
    {
      if (!k.equals("default"))
      {
        String temp = (String) k;
        ingredients.addItem(temp);
      }
    }
    // Create combo boxes
    // ingredients = new JComboBox<Ingredient>(x.);
    ingredients.setSelectedIndex(-1);
    units = new JComboBox<FromUnits>(FromUnits.values());
    units.setSelectedIndex(-1);

    // Create the labels
    ingredientsLabel = new JLabel(MainWindow.STRINGS.getString("INGREDIENT") + colon);
    amountLabel = new JLabel(MainWindow.STRINGS.getString("AMOUNT") + colon);
    unitsLabel = new JLabel(MainWindow.STRINGS.getString("UNITS") + colon);
    caloriesLabel = new JLabel(MainWindow.STRINGS.getString("CALORIES") + colon);
    recipesLabel = new JLabel(MainWindow.STRINGS.getString("RECIPE") + ": ");
    mealsLabel = new JLabel(MainWindow.STRINGS.getString("MEAL") + colon + blank);
    caloriesRecipeLabel = new JLabel(
        MainWindow.STRINGS.getString("RECIPE_CALORIES") + colon + blank);
    caloriesMealLabel = new JLabel(MainWindow.STRINGS.getString("MEAL_CALORIES") + colon + blank);

    // Create JPanel to add to frame using gridbaglayout
    JPanel p = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5, 5, 5, 5);
    c.gridx = 0;
    c.gridy = 1;
    p.add(box);
    c.gridx = 0;
    c.gridy = 2;
    p.add(ingredientsLabel, c);
    c.gridx = 1;
    c.gridy = 2;
    p.add(ingredients, c);
    c.gridx = 2;
    c.gridy = 2;
    p.add(amountLabel, c);
    c.gridx = 3;
    c.gridy = 2;
    p.add(amount, c);
    c.gridx = 4;
    c.gridy = 2;
    p.add(unitsLabel, c);
    c.gridx = 5;
    c.gridy = 2;
    p.add(units, c);
    c.gridx = 0;
    c.gridy = 3;
    p.add(caloriesLabel, c);
    c.gridx = 1;
    c.gridy = 3;
    p.add(calories, c);

    c.gridx = 0;
    c.gridy = 4;
    p.add(recipesLabel, c);
    c.gridx = 1;
    c.gridy = 4;
    p.add(recipes, c);
    c.gridx = 2;
    c.gridy = 4;
    p.add(caloriesRecipeLabel, c);
    c.gridx = 3;
    c.gridy = 4;
    p.add(caloriesRecipe, c);

    c.gridx = 0;
    c.gridy = 5;
    p.add(mealsLabel, c);
    c.gridx = 1;
    c.gridy = 5;
    p.add(meals, c);
    c.gridx = 2;
    c.gridy = 5;
    p.add(caloriesMealLabel, c);
    c.gridx = 3;
    c.gridy = 5;
    p.add(caloriesMeal, c);

    // add Panel to Frame
    add(p, BorderLayout.WEST);
    contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout(FlowLayout.LEADING));
    setVisible(true);
    setResizable(false);
  }

  /**
   * Calculate method for calculating calories.
   * 
   * @param food
   *          the food we are using to calculate its calories
   * @param amount2
   *          the amount we have
   * @param useUnits
   *          the units we are using
   *          
   * @throws ClassNotFoundException class not found
   * @throws IOException Input / Output exception
   * 
   * @return the calories of the specified ingredient
   */
  public static double calculateCalories(final Ingredient food, final double amount2,
      final String useUnits) throws ClassNotFoundException, IOException
  {
    double caloriesAmount = 0;
    double ret = 0.0;
    if (useUnits.equals("") || food == null)
    {

      return -1.0;
    }
    double amountMult = UnitConversion.calculate(useUnits, FromUnits.GRAM.getUnit(), food, amount2);
    caloriesAmount = food.getCalories();
    ret = (amountMult * (caloriesAmount / 100.0));
    return ret;
  }

  /**
   * Calculate method for calculating calories of a recipe.
   * 
   * @param recipe
   *          the given recipe to calculate calories
   * 
   * @throws ClassNotFoundException class not found
   * @throws IOException Input / Output exception
   * 
   * @return the calories of the specified recipe
   */
  public static double calculateCaloriesRecipe(final Recipe recipe)
      throws ClassNotFoundException, IOException
  {
    double calories = 0.0;
    double ingAmount = 0.0;
    String units = "";
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    if (recipe == null)
    {
      return -1.0;
    }
    ingredients = recipe.getIngredients();
    for (Ingredient i : ingredients)
    {
      ingAmount = i.getAmount();
      units = i.getUnits();
      calories += CalorieCalculatorWindow.calculateCalories(i, ingAmount, units);
    }
    return calories;
  }

  /**
   * Calculate method for calculating calories of a meal.
   * 
   * @param meal
   *          the given meal to calculate calories
   * 
   * @throws IOException Input / Output exception
   * @throws ClassNotFoundException class not found
   * 
   * @return the calories of the specified meal
   */
  public static double calculateCaloriesMeal(final Meal meal)
      throws ClassNotFoundException, IOException
  {
    double mealCalories = 0.0;
    ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    if (meal == null)
    {
      return -1.0;
    }
    recipes = meal.getRecipies();
    for (Recipe r : recipes)
    {
      mealCalories += CalorieCalculatorWindow.calculateCaloriesRecipe(r);
    }
    return mealCalories;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String actionCommand;
    actionCommand = e.getActionCommand();

    if (actionCommand.equals(r))
    {

      ingredients.setSelectedIndex(-1);
      units.setSelectedIndex(-1);
      calories.setText(null);
      amount.setText(null);
      recipes.setSelectedIndex(-1);
      caloriesRecipe.setText(null);
      meals.setSelectedIndex(-1);
      caloriesMeal.setText(null);
      return;
    }

    else if (actionCommand.equals(ca))
    {

      double caloriesTotal = 0;
      double am = 0.0;
      Ingredient in = null;
      String ing = (String) ingredients.getSelectedItem();
      String u = "";
      // Try to get the amount from the JTextField
      try
      {
        am = Double.parseDouble(amount.getText());
      }
      catch (NumberFormatException ne)
      {
        // Do something
      }
      // Try to get the ingredient from the string
      try
      {
        in = x.get(ing);
      }
      catch (IllegalArgumentException ia)
      {
        ia.printStackTrace();
      }
      try
      {
        u = units.getSelectedItem().toString();
      }
      catch (NullPointerException np)
      {
        // Do something
      }

      try
      {
        caloriesTotal = calculateCalories(in, am, u);
      }
      catch (ClassNotFoundException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      if (caloriesTotal == -1.0)
      {
        calories.setText(blank);
      }
      else
      {
        calories.setText(String.format(doubleFormatter, caloriesTotal));
      }

      // LOADING THE DATABASE TO GET MEALS AND RECIPES
      DecentralizedDatabase db2 = new DecentralizedDatabase();
      // Try loading the database throw exception and catch when not found
      try
      {
        db2.loadMe();
      }
      catch (ClassNotFoundException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      catch (NullPointerException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

      // CALCULATING CALORIES OF A RECIPE
      if (recipes.getSelectedItem() == null)
      {
        if (meals.getSelectedItem() != null)
        {
          mealCalc();
        }
        return;
      }
      String rGetString = "";
      try
      {
        rGetString = recipes.getSelectedItem().toString();
      }
      catch (NullPointerException ne)
      {
        ne.printStackTrace();
      }
      Recipe re = null;
      try
      {
        re = db2.getRecipe(rGetString);
      }
      catch (ClassNotFoundException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      double caloriesTotalRecipe = 0.0;
      // Try to calculate the calories of a recipe, throw exceptions when needed.
      try
      {
        caloriesTotalRecipe = CalorieCalculatorWindow.calculateCaloriesRecipe(re);
      }
      catch (ClassNotFoundException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      if (caloriesTotalRecipe == -1.0)
      {
        caloriesRecipe.setText(blank);
      }
      else
      {
        caloriesRecipe.setText(String.format(doubleFormatter, caloriesTotalRecipe));
      }

      // CALCULATE CALORIES OF MEAL
      if (meals.getSelectedItem() == null)
      {
        return;
      }
      String mGetString = (String) meals.getSelectedItem();
      Meal m = null;
      try
      {
        m = db2.getMeal(mGetString);
      }
      catch (ClassNotFoundException cfe)
      {
        cfe.printStackTrace();
      }
      catch (IOException ie)
      {
        ie.printStackTrace();
      }
      double caloriesTotalMeal = 0.0;
      try
      {
        caloriesTotalMeal = CalorieCalculatorWindow.calculateCaloriesMeal(m);
      }
      catch (ClassNotFoundException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      // If calories outputs -1 there was an error in calculation.
      if (caloriesTotalMeal == -1.0)
      {
        caloriesMeal.setText(blank);
      }
      else
      {
        caloriesMeal.setText(String.format(doubleFormatter, caloriesTotalMeal));
      }
      return;
    }

  }

  /**
   * mealCalc method is a helper method for the action performed method when a user just wants to
   * calculate the calories of a meal without populating anything else.
   * 
   */
  public void mealCalc()
  {
    // Load Database
    DecentralizedDatabase db3 = new DecentralizedDatabase();
    try
    {
      db3.loadMe();
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

    // CALCULATE CALORIES OF MEAL
    String mGetString = (String) meals.getSelectedItem();
    Meal m = null;
    try
    {
      m = db3.getMeal(mGetString);
    }
    catch (ClassNotFoundException cfe)
    {
      cfe.printStackTrace();
    }
    catch (IOException ie)
    {
      ie.printStackTrace();
    }
    double caloriesTotalMeal = 0.0;
    try
    {
      caloriesTotalMeal = CalorieCalculatorWindow.calculateCaloriesMeal(m);
    }
    catch (ClassNotFoundException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    catch (IOException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    // If calories outputs -1 there was an error in calculation.
    if (caloriesTotalMeal == -1.0)
    {
      caloriesMeal.setText(blank);
    }
    else
    {
      caloriesMeal.setText(String.format(doubleFormatter, caloriesTotalMeal));
    }
    return;
  }

}
