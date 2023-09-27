package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cooking.DecentralizedDatabase;
import cooking.Ingredient;
import cooking.IngredientDatabase;
import cooking.Meal;
import cooking.Recipe;

/**
 * Search Panel for recipe and meal search.
 * 
 * @author phanvm
 * @version Sprint 3
 *
 *          This work complies with JMU's Honor code.
 */
public class SearchPanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;

  private static final String CONTAIN = "CONTAIN";
  private static final String SEARCH = "SEARCH";

  private JButton search;
  private Icon searchIcon;
  private JList<String> ingredientsList;
  private JList<String> recipeList;
  private DefaultListModel<String> recipeModel;

  private Map<String, Ingredient> ingredientMap;
  private Iterator<String> ingredientIterator;
  private String[] ingredientArray;
  private GridBagConstraints gbc;
  private JComboBox<String> inOrOut;
  private String name;
  // private String s = "Search";
  // private String c = "Contain(s)";
  // private String dc = "Does not Contain";

  /**
   * Constructor for the search panel.
   * 
   * @param name
   *          Either meal or recipe as the title.
   */
  public SearchPanel(final String name)
  {
    this.name = name;
    setupLayout();
  }

  /**
   * Sets up the layout of the JPanel.
   */
  public void setupLayout()
  {
    gbc = new GridBagConstraints();
    setLayout(new GridBagLayout());
    setVisible(true);

    searchIcon = new ImageIcon(
        this.getClass().getResource("/icons/baseline_search_black_24dp.png"));
    search = new JButton(searchIcon);
    search.setOpaque(false);
    search.setContentAreaFilled(false);
    search.setBorderPainted(false);
    search.addActionListener(this);
    search.setActionCommand(MainWindow.STRINGS.getString(SEARCH));
    search.setEnabled(false);

    gbc.gridx = 0;
    gbc.gridy = 0;
    add(search, gbc);

    String[] x = {"", MainWindow.STRINGS.getString(CONTAIN),
        MainWindow.STRINGS.getString("NOT_CONTAIN")};
    inOrOut = new JComboBox<String>(x);
    inOrOut.addActionListener(this);

    gbc.gridx = 1;
    add(inOrOut);

    try
    {
      ingredientMap = IngredientDatabase.deserializeMap();
      TreeSet<String> ingredientSet = new TreeSet<String>(ingredientMap.keySet());
      ingredientIterator = ingredientSet.iterator();
      ingredientArray = new String[ingredientSet.size()];

      int i = 0;
      while (ingredientIterator.hasNext())
      {
        ingredientArray[i] = ingredientIterator.next();
        i++;
      }

    }
    catch (ClassNotFoundException | IOException e)
    {
      e.printStackTrace();
    }

    ingredientsList = new JList<String>(ingredientArray);
    MouseListener mouseListener = new MouseAdapter()
    {
      public void mouseClicked(final MouseEvent e)
      {
        if (MouseEvent.MOUSE_CLICKED > 0 && ingredientsList.getSelectedValuesList().size() > 0)
        {
          search.setEnabled(true);
        }
        else if (MouseEvent.MOUSE_CLICKED > 0
            && ingredientsList.getSelectedValuesList().size() == 0)
        {
          search.setEnabled(false);
          recipeModel.removeAllElements();
        }
      }
    };

    ingredientsList.addMouseListener(mouseListener);
    JScrollPane ingredients = new JScrollPane(ingredientsList);
    ingredients
        .setBorder(BorderFactory.createTitledBorder(MainWindow.STRINGS.getString("INGREDIENTS")));
    ingredients.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    gbc.ipady = 80;
    gbc.ipadx = 450;
    gbc.weightx = 0.0;
    gbc.gridwidth = 3;
    gbc.gridx = 0;
    gbc.gridy = 1;

    add(ingredients, gbc);

    recipeModel = new DefaultListModel<String>();
    recipeList = new JList<String>();
    JScrollPane recipe = new JScrollPane(recipeList);
    recipe.setBorder(BorderFactory.createTitledBorder(name));
    recipe.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    gbc.ipady = 80;
    gbc.ipadx = 450;
    gbc.weightx = 0.0;
    gbc.gridwidth = 3;
    gbc.gridx = 0;
    gbc.gridy = 2;

    add(recipe, gbc);
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    DecentralizedDatabase db = new DecentralizedDatabase();

    if (e.getActionCommand().equals(MainWindow.STRINGS.getString(SEARCH)))
    {
      recipeModel.removeAllElements();

      try
      {
        ArrayList<String> list = (ArrayList<String>) ingredientsList.getSelectedValuesList();
        db.loadMe();

        if (name.equals(MainWindow.STRINGS.getString("RECIPES")))
        {
          ArrayList<Recipe> haveIngredients;
          Iterator<Recipe> iterator;
          if (inOrOut.getSelectedItem().equals(MainWindow.STRINGS.getString(CONTAIN)))
          {
            haveIngredients = db.getRecipesWithIngredients(list);
          }
          else
          {
            haveIngredients = db.getRecipesWithoutIngredients(list);
          }

          if (haveIngredients.size() > 0)
          {
            iterator = haveIngredients.iterator();

            while (iterator.hasNext())
            {
              recipeModel.addElement(iterator.next().getName());
            }

            recipeList.setModel(recipeModel);
          }
          else
          {
            recipeModel.addElement(MainWindow.STRINGS.getString("NO_RECIPES_FOUND"));
            recipeList.setModel(recipeModel);
          }
        }
        else if (name.equals(MainWindow.STRINGS.getString("MEALS")))
        {
          ArrayList<Meal> mealIngredients;
          Iterator<Meal> iterator1;
          if (inOrOut.getSelectedItem().equals(MainWindow.STRINGS.getString(CONTAIN)))
          {
            mealIngredients = db.getMealsWithIngredients(list);
          }
          else
          {
            mealIngredients = db.getMealsWithoutIngredients(list);
          }

          if (mealIngredients.size() > 0)
          {
            iterator1 = mealIngredients.iterator();

            while (iterator1.hasNext())
            {
              recipeModel.addElement(iterator1.next().getName());
            }

            recipeList.setModel(recipeModel);
          }
          else
          {
            recipeModel.addElement(MainWindow.STRINGS.getString("NO_MEALS_FOUND"));
            recipeList.setModel(recipeModel);
          }
        }
      }
      catch (ClassNotFoundException | IOException e1)
      {
        e1.printStackTrace();
      }
    }
  }
}
