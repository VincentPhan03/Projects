/**
 * 
 */
package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import cooking.Ingredient;
import utilities.RecipeManager;

/**
 * The IngredientsPanel.
 * 
 * @author phanvm, Cole T. Strubhar
 * @version Sprint 1
 * 
 *          This work complies with JMU's Honor Code.
 */
public class IngredientsPanel extends JPanel implements ActionListener
{
  private static String ADD = "ADD";
  private static String DELETE = "DELETE";
  private static String NAME = "NAME";
  private static String DETAILS = "DETAILS";
  private static String AMOUNT = "AMOUNT";
  private static final String UNITS = "UNITS";
  
  private final String title = MainWindow.STRINGS.getString("INGREDIENTS");
  private final String[] units = {"teaspoon", "tablespoon", "fluid ounce", "cup", "pint", "quart",
      "gallon", "milliliter", "liter", "gram", "ounce", "pound"};

  private String colon = ": ";

  
  private JLabel name;
  private JLabel detail;
  private JLabel amount;
  private JLabel unit;

  public JTextField nameInput;
  public JTextField detailInput;
  public JTextField amountInput;

  public JComboBox<String> unitInput;

  private JButton add;
  private JButton delete;

  private JCheckBox subBox;

  private DefaultListModel<String> ingredients = new DefaultListModel<String>();
  private JList<String> ingredientsList = new JList<String>(ingredients);

  private RecipeManager recipeManager;

  /**
   * 
   */
  public IngredientsPanel()
  {
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createTitledBorder(title));
    setVisible(true);

    // Constraints
    GridBagConstraints gbc = new GridBagConstraints();

    this.name = new JLabel(MainWindow.STRINGS.getString(NAME) + colon);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 0, 10); // Add some space on the top
    add(name, gbc);

    this.nameInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(nameInput, gbc);

    this.detail = new JLabel(MainWindow.STRINGS.getString(DETAILS) + colon);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(detail, gbc);

    this.detailInput = new JTextField();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(detailInput, gbc);

    this.amount = new JLabel(MainWindow.STRINGS.getString(AMOUNT));
    gbc.gridx = 4;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(amount, gbc);

    this.amountInput = new JTextField();
    gbc.gridx = 5;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(amountInput, gbc);

    this.unit = new JLabel(MainWindow.STRINGS.getString(UNITS) + colon);
    gbc.gridx = 6;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(unit, gbc);

    this.unitInput = new JComboBox<String>(units);
    gbc.gridx = 7;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(unitInput, gbc);

    GridBagConstraints gbcTwo = new GridBagConstraints();
    gbcTwo.insets = new Insets(1, 1, 1, 1);

    this.subBox = new JCheckBox();
    gbcTwo.fill = GridBagConstraints.BOTH;
    gbcTwo.gridwidth = GridBagConstraints.RELATIVE;
    gbcTwo.gridx = 8;
    gbcTwo.gridy = 0;
    add(subBox, gbcTwo);

    this.add = new JButton(MainWindow.STRINGS.getString(ADD));
    gbc.gridx = 9;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 1.0;
    add(add, gbc);

    this.ingredientsList = new JList<String>(ingredients);
    JScrollPane scrollPane = new JScrollPane(ingredientsList);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 8;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    add(scrollPane, gbc);

    this.delete = new JButton(MainWindow.STRINGS.getString(DELETE));
    gbc.gridx = 8;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    add(delete, gbc);

    add.setActionCommand("Add");
    add.addActionListener(this);

    delete.setActionCommand("Delete");
    delete.addActionListener(this);

    recipeManager = new RecipeManager();

    // RecipeManager.addIngredientsPanel(this);
  }

  public IngredientsPanel(RecipeManager recipeManager)
  {
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createTitledBorder(title));
    setVisible(true);

    // Constraints
    GridBagConstraints gbc = new GridBagConstraints();

    this.name = new JLabel(MainWindow.STRINGS.getString("NAME") + colon);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 0, 0, 0); // Add some space on the top
    add(name, gbc);

    this.nameInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(nameInput, gbc);

    this.detail = new JLabel(MainWindow.STRINGS.getString("DETAILS") + colon);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(detail, gbc);

    this.detailInput = new JTextField();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(detailInput, gbc);

    this.amount = new JLabel(MainWindow.STRINGS.getString("AMOUNT"));
    gbc.gridx = 4;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(amount, gbc);

    this.amountInput = new JTextField();
    gbc.gridx = 5;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(amountInput, gbc);

    this.unit = new JLabel(MainWindow.STRINGS.getString("UNITS") + colon);
    gbc.gridx = 6;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(unit, gbc);

    this.unitInput = new JComboBox<String>(units);
    gbc.gridx = 7;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(unitInput, gbc);

    // GridBagConstraints gbcTwo = new GridBagConstraints();
    // gbcTwo.insets = new Insets(1, 1, 1, 1);

    JLabel subAsk = new JLabel(MainWindow.STRINGS.getString("SUBSTITUTION") + colon);
    gbc.gridx = 8;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(subAsk, gbc);

    this.subBox = new JCheckBox();
    gbc.gridx = 9;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(subBox, gbc);

    this.add = new JButton(MainWindow.STRINGS.getString(ADD));
    gbc.gridx = 10;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(add, gbc);

    this.ingredientsList = new JList<String>(ingredients);
    JScrollPane scrollPane = new JScrollPane(ingredientsList);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 8;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    add(scrollPane, gbc);

    this.delete = new JButton(MainWindow.STRINGS.getString(DELETE));
    gbc.gridx = 10;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    add(delete, gbc);

    add.setActionCommand("Add");
    add.addActionListener(this);

    delete.setActionCommand("Delete");
    delete.addActionListener(this);

    this.recipeManager = recipeManager;

    // RecipeManager.addIngredientsPanel(this);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      if (e.getActionCommand().equals("Add"))
      {
        if (subBox.isSelected())
        {
          boolean add = false;
          add = recipeManager.addIngredient(nameInput.getText(), detailInput.getText(),
              Double.valueOf(amountInput.getText()), (String) unitInput.getSelectedItem());

          if (add)
          {
            ingredients.add(0, nameInput.getText());
            Substitution sub = new Substitution(this, nameInput.getText(), recipeManager);

          }

         
        }
        else
        {
          boolean add = false;
          add = recipeManager.addIngredient(nameInput.getText(), detailInput.getText(),
              Double.valueOf(amountInput.getText()), (String) unitInput.getSelectedItem());

          if (add)
          {
            ingredients.add(0, nameInput.getText());
          }
        }

      }
    }
    catch (NumberFormatException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
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
    if (e.getActionCommand().equals("Delete"))

    {
      int toRemoveIndex = ingredientsList.getSelectedIndex();

      recipeManager.removeIngredient(ingredients.get(toRemoveIndex));
      ingredients.remove(toRemoveIndex);
    }

  }

  public DefaultListModel<String> getIngredient()
  {
    return ingredients;
  }

  public JList<String> getIngredientsList()
  {
    return ingredientsList;
  }

  public JTextField getNameInput()
  {
    return nameInput;
  }

  public void substitutionSelected(String selected, String originalInput)
  {
    String ingredient = "";
    double done = 0;
    for (int i = 0; i < ingredients.size(); i++)
    {
      ingredient = ingredients.get(i);
      if (ingredient.equals(selected) || ingredient.equals(originalInput))
      {
        ingredients.remove(i);
        i -= 1;
        done += 0.5;
      }
      if (done == 1)
      {
        i = ingredients.size();
      }
    }
    ingredients.add(ingredients.size(), String.format("%s or %s", originalInput, selected));
  }

  public DefaultListModel<String> getIngredients()
  {
    return ingredients;
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    JPanel p = new IngredientsPanel();
    JFrame f = new JFrame();

    f.setSize(700, 400);
    f.add(p);
    f.setVisible(true);

  }

}
