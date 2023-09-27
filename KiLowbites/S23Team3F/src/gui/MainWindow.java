package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The main window of the application. contains menus to access the other windows.
 * 
 * @author Andrew Davis
 *
 */
public class MainWindow implements Runnable, ActionListener
{

  public static final int WIDTH = 600;
  public static final int HEIGHT = 800;
  public static final String EXIT = "EXIT";
  public static final ResourceBundle STRINGS = ResourceBundle.getBundle("data.Strings");

  // protected static final String LOAD = "Load";
  protected static final String RECIPE = "RECIPE";
  protected static final String MEAL = "MEAL";
  protected static final String SEARCH = "SEARCH";
  protected static final String RECIPES = "RECIPES";
  protected static final String MEALS = "MEALS";
  protected static final String CALCULATOR = "CALORIE_CALCULATOR";
  protected static final String CONVERTER = "UNITS_CONVERTER";
  protected static final String SHOPPING = "SHOPPING_LIST";
  protected static final String PROCESS = "PROCESS";

  static final Locale LOCALE = Locale.getDefault();

  protected JTextField fileField;
  private JFrame frame;

  /**
   * Main method place holder.
   * 
   * @param args
   */
  public MainWindow(final String[] args)
  {
    // TODO Auto-generated constructor stub
  }

  /**
   * Must construct the GUI components and lay them out (as illustrated in the interaction design
   * portion of this document).
   */
  public void init()
  {

    this.frame = new JFrame("KiLowBites " + STRINGS.getString("MAIN_WINDOW"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel contentPane = (JPanel) frame.getContentPane();

    contentPane.setLayout(new BorderLayout());

    // Add the menu
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    JMenu menu;
    // JMenuItem item;

    menu = new JMenu(STRINGS.getString("FILE"));
    menuBar.add(menu);

    JMenuItem exit = new JMenuItem(STRINGS.getString(EXIT));
    exit.addActionListener(this);
    menu.add(exit);

    KeyStroke quitKey = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK); // works
                                                                                        // without
                                                                                        // opening
                                                                                        // the menu
                                                                                        // first
    exit.setAccelerator(quitKey);

    exit.setMnemonic(KeyEvent.VK_Q); // menu must first be open

    JMenu edit = new JMenu(STRINGS.getString("EDIT"));
    menuBar.add(edit);

    JMenuItem recipe = new JMenuItem(STRINGS.getString(RECIPE));
    recipe.setActionCommand(STRINGS.getString(RECIPE));
    recipe.addActionListener(this);
    edit.add(recipe);

    JMenuItem meal = new JMenuItem(STRINGS.getString(MEAL));
    meal.setActionCommand(STRINGS.getString(MEAL));
    meal.addActionListener(this);
    edit.add(meal);

    JMenu search = new JMenu(STRINGS.getString(SEARCH));
    menuBar.add(search);

    JMenuItem recipes = new JMenuItem(STRINGS.getString(RECIPES));
    recipes.addActionListener(this);
    search.add(recipes);

    JMenuItem meals = new JMenuItem(STRINGS.getString(MEALS));
    meals.addActionListener(this);
    search.add(meals);

    JMenu view = new JMenu(STRINGS.getString("VIEW"));
    menuBar.add(view);

    JMenuItem process = new JMenuItem(STRINGS.getString(PROCESS));
    process.addActionListener(this);
    view.add(process);

    JMenu tools = new JMenu(STRINGS.getString("TOOLS"));
    menuBar.add(tools);

    JMenuItem calorieCalculator = new JMenuItem(STRINGS.getString(CALCULATOR));
    calorieCalculator.setActionCommand(STRINGS.getString(CALCULATOR));
    calorieCalculator.addActionListener(this);
    tools.add(calorieCalculator);

    JMenuItem unitsConverter = new JMenuItem(STRINGS.getString(CONVERTER));
    unitsConverter.setActionCommand(STRINGS.getString(CONVERTER));
    unitsConverter.addActionListener(this);
    tools.add(unitsConverter);

    JMenu configure = new JMenu("Configure");
    // menuBar.add(configure);

    JMenuItem preferences = new JMenuItem("Preferences");
    preferences.addActionListener(this);
    configure.add(preferences);

    JMenuItem shortCuts = new JMenuItem("Shortcuts");
    shortCuts.addActionListener(this);
    configure.add(shortCuts);

    JMenuItem nutrition = new JMenuItem("Nutrition");
    nutrition.addActionListener(this);
    configure.add(nutrition);

    // JMenu help = new JMenu("Help");
    // menuBar.add(help);

    // JMenu language = new JMenu("Language");
    // menuBar.add(language);
    //
    // JMenuItem lang1 = new JMenuItem("English");
    // lang1.addActionListener(this);
    // language.add(lang1);
    //
    // JMenuItem lang2 = new JMenuItem("German");
    // lang2.addActionListener(this);
    // language.add(lang2);
    //
    // JMenuItem lang3 = new JMenuItem("Spanish");
    // lang3.addActionListener(this);
    // language.add(lang3);

    // JMenuItem about = new JMenuItem("About");
    // about.addActionListener(this);
    // help.add(about);

    // JMenuItem userGuide = new JMenuItem("User Guide");
    // userGuide.addActionListener(this);
    // help.add(userGuide);

    BufferedImage logo;
    try
    {
      logo = ImageIO.read(this.getClass().getResource("/icons/KILowBites_Logo.png"));
      JLabel picLabel = new JLabel(new ImageIcon(logo));
      frame.add(picLabel, BorderLayout.CENTER);
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    frame.setSize(400, 250);
    contentPane.setBackground(Color.WHITE);
    // menuBar.setBackground(Color.GRAY);
    frame.setBackground(Color.GRAY);

    frame.setVisible(true);

    contentPane.setVisible(true);

  }

  @Override
  public void run()
  {
    // TODO Auto-generated method stub
    try
    {
      String style = UIManager.getSystemLookAndFeelClassName();
      UIManager.setLookAndFeel(style);
    }
    catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
        | IllegalAccessException e)
    {
      System.out.print("error, style");
    }

    this.init();

  }

  /**
   * Must handle the events generated by the user clicking on the About and/or Load buttons. When
   * the user clicks on the About button, this method must invoke the handleAbout() method. When the
   * user clicks on the Load button, this method must invoke the handleLoad() method.
   * 
   * @param evt
   *          the action clicked on
   */
  public void actionPerformed(final ActionEvent evt)
  {

    String actionCommand;
    actionCommand = evt.getActionCommand();

    if (actionCommand.equals(STRINGS.getString(EXIT)))
    {

      System.exit(0);

      return;
    }

    if (actionCommand.equals(STRINGS.getString(MEAL)))
    {

      new MealEditor();

      return;
    }

    if (actionCommand.equals(STRINGS.getString(RECIPE)))
    {

      new RecipeEditor(new JFrame());

      return;
    }

    if (actionCommand.equals(STRINGS.getString(RECIPES)))
    {

      new RecipeSearch();

      return;
    }

    if (actionCommand.equals(STRINGS.getString(MEALS)))
    {

      new MealSearch();

      return;
    }

    if (actionCommand.equals(STRINGS.getString(CALCULATOR)))
    {

      try
      {
        new CalorieCalculatorWindow();
      }
      catch (ClassNotFoundException | IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      return;
    }

    if (actionCommand.equals(STRINGS.getString(CONVERTER)))
    {

      try
      {
        new UnitConverterWindow(null);
      }
      catch (ClassNotFoundException | IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      return;
    }

    if (actionCommand.equals(STRINGS.getString(PROCESS)))
    {
      new ProcessViewer();
      return;
    }

  }

  
  /**
   * The entry point of the application.
   * 
   * @param args
   *          The command-line arguments (which are ignored)
   * @throws InterruptedException
   *           If the system is interrupted
   * @throws InvocationTargetException
   *           If there is a problem starting the system
   */
  public static void main(final String[] args)
      throws InterruptedException, InvocationTargetException
  {
    // Perform all of the setup activities in the event dispatch thread
    SwingUtilities.invokeAndWait(new MainWindow(args));
  }

}
