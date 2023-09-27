
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cooking.Ingredient;
import cooking.Recipe;
import cooking.Step;
import cooking.Utensil;
import utilities.RecipeManager;

/**
 * 
 * @author phanvm, Cole T. Strubhar
 * @version Sprint 1
 *
 *          This work complies with JMU's Honor Code.
 */
public class StepsPanel extends JPanel implements ActionListener
{

  private static final long serialVersionUID = 1L;
  
  private static final String ACTION = "ACTION";
  private static final String ON = "ON";
  private static final String UTENSIL = "UTENSIL";
  private static final String DETAILS = "DETAILS";
  private static final String ADD = "ADD";
  //private static final String DELETE = "DELETE";

  private final String title = MainWindow.STRINGS.getString("STEPS");
  
  private UtensilsPanel utensilsPanel;

  private JLabel action;
  private JLabel on;
  private JLabel utensil;
  private JLabel details;

  private JComboBox<String> onInput;
  private JComboBox<String> utensilInput;

  private JTextField actionInput;
  private JTextField detailInput;

  private JButton add;
  //private JButton delete;

  private DefaultListModel<String> steps = new DefaultListModel<String>();
  private JList<String> stepsList = new JList<String>(steps);

  private RecipeManager recipeManager;

  private final String actionString = "Action:";
  private final String onString = "On:";
  private final String utensilString = "Utensil:";
  private final String detailString = "Details:";
  private final String addString = "Add";
  private final String deleteString = "Delete";
  private String colon = ": ";

  /**
   * Constructor for steps panel creates and add the components to a steps panel.
   */
  public StepsPanel(UtensilsPanel utensilsPanel)
  {
    this.utensilsPanel = utensilsPanel;
    
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createTitledBorder(title));
    setVisible(true);

    // Constraints
    GridBagConstraints gbc = new GridBagConstraints();

    this.action = new JLabel(MainWindow.STRINGS.getString(ACTION) + colon);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 0, 10); // Add some space on the top
    add(action, gbc);

    this.actionInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(actionInput, gbc);

    this.on = new JLabel(MainWindow.STRINGS.getString(ON) + colon);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(on, gbc);

    this.onInput = new JComboBox<String>();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(onInput, gbc);

    this.utensil = new JLabel(MainWindow.STRINGS.getString(UTENSIL) + colon);
    gbc.gridx = 4;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(utensil, gbc);

    this.utensilInput = new JComboBox<String>();
    gbc.gridx = 5;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(utensilInput, gbc);

    this.details = new JLabel(MainWindow.STRINGS.getString(DETAILS) + colon);
    gbc.gridx = 6;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(details, gbc);

    this.detailInput = new JTextField();
    gbc.gridx = 7;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(detailInput, gbc);

    this.add = new JButton(MainWindow.STRINGS.getString(ADD));
    gbc.gridx = 8;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(add, gbc);

    this.stepsList = new JList<String>(steps);
    JScrollPane scrollPane = new JScrollPane(stepsList);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 8;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    add(scrollPane, gbc);

//    this.delete = new JButton(MainWindow.STRINGS.getString(DELETE));
//    gbc.gridx = 8;
//    gbc.gridy = 2;
//    gbc.gridwidth = 1;
//    gbc.gridheight = 1;
//    gbc.fill = GridBagConstraints.HORIZONTAL;
//    gbc.weightx = 0.0;
//    gbc.weighty = 0.0;
//    add(delete, gbc);

    add.setActionCommand(addString);
    add.addActionListener(this);

//    delete.setActionCommand(deleteString);
//    delete.addActionListener(this);

    recipeManager = new RecipeManager();
  }

  /**
   * Constructor for steps panel creates and add the components to a steps panel.
   * 
   * @param recipeManager
   *          Manages functionality for steps GUI.
   */
  public StepsPanel(final RecipeManager recipeManager, UtensilsPanel utensilsPanel)
  {
    this.utensilsPanel = utensilsPanel;
    
    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createTitledBorder(title));
    setVisible(true);

    // Constraints
    GridBagConstraints gbc = new GridBagConstraints();

    this.action = new JLabel(MainWindow.STRINGS.getString(ACTION) + colon);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 0, 10); // Add some space on the top
    add(action, gbc);

    this.actionInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(actionInput, gbc);

    this.on = new JLabel(MainWindow.STRINGS.getString(ON) + colon);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(on, gbc);

    this.onInput = new JComboBox<String>();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(onInput, gbc);

    this.utensil = new JLabel(MainWindow.STRINGS.getString(UTENSIL) + colon);
    gbc.gridx = 4;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(utensil, gbc);

    this.utensilInput = new JComboBox<String>();
    gbc.gridx = 5;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(utensilInput, gbc);

    this.details = new JLabel(MainWindow.STRINGS.getString(DETAILS) + colon);
    gbc.gridx = 6;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(details, gbc);

    this.detailInput = new JTextField();
    gbc.gridx = 7;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(detailInput, gbc);

    this.add = new JButton(MainWindow.STRINGS.getString(ADD));
    gbc.gridx = 8;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(add, gbc);

    this.stepsList = new JList<String>(steps);
    JScrollPane scrollPane = new JScrollPane(stepsList);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 8;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    add(scrollPane, gbc);

//    this.delete = new JButton(MainWindow.STRINGS.getString(DELETE));
//    gbc.gridx = 8;
//    gbc.gridy = 2;
//    gbc.gridwidth = 1;
//    gbc.gridheight = 1;
//    gbc.fill = GridBagConstraints.HORIZONTAL;
//    gbc.weightx = 0.0;
//    gbc.weighty = 0.0;
//    add(delete, gbc);

    add.setActionCommand(addString);
    add.addActionListener(this);

//    delete.setActionCommand(deleteString);
//    delete.addActionListener(this);

    this.recipeManager = recipeManager;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getActionCommand().equals(addString))
    {
      Recipe currRecipe = recipeManager.getCurrentRecipe();
      Utensil currUtensil = currRecipe.getUtensil((String) utensilInput.getSelectedItem());

      
      Step tempStep = null;
      Utensil currUtensilOption = currRecipe.getUtensil((String) onInput.getSelectedItem());
      Ingredient currIngredientOption = currRecipe.getIngredient((String) onInput.getSelectedItem());
      
      if (currUtensilOption != null) {
        tempStep = new Step((String) actionInput.getText(), (String) detailInput.getText(), currUtensilOption, currUtensil);
      } else {
        tempStep = new Step((String) actionInput.getText(), (String) detailInput.getText(), currIngredientOption, currUtensil);
      }      
      
      recipeManager.addStep((String) actionInput.getText(), detailInput.getText(),
          (String) utensilInput.getSelectedItem(), (String) onInput.getSelectedItem());

      steps.add(steps.size(), tempStep.stepToString());
      
      /*
      steps.add(steps.size(), String.format("%s %s with %s", (String) actionInput.getText(),
          (String) onInput.getSelectedItem(), (String) utensilInput.getSelectedItem()));
          */
          
      // steps.add(steps.size(), );

      recipeManager.printSteps();
    }

    if (e.getActionCommand().equals(deleteString))
    {
      recipeManager.deleteStep((String) actionInput.getText(),
          detailInput.getSelectedText(), (String) utensilInput.getSelectedItem(),
          (String) onInput.getSelectedItem());

      steps.remove(stepsList.getSelectedIndex());
      recipeManager.printSteps();
    }
  }

  /**
   * Adds a string literal "action" to a JComboBox so that it can be displayed in GUI.
   * 
   * @param action1
   *          The name of the action.
   */
  public void addAction(final String action1)
  {
    //Do Nothing
    // actionInput.addItem(action1);
  }

  /**
   * Adds a string "on" to a JComboBox so that it can be displayed in GUI.
   * 
   * @param on1
   *          The name of the "on" step
   */
  public void addOn(final String on1)
  {
    onInput.addItem(on1);
  }

  /**
   * Removes a string "on" from the JComboBox so that it will on be displayed in GUI.
   * 
   * @param on1
   *          The string to be removed.
   */
  public void removeOn(final String on1)
  {
    onInput.removeItem(on1);
  }

  /**
   * Adds a string "utensil" to the JComboBox so that it displayed in GUI.
   * 
   * @param utensil1
   *          The string to be removed.
   */
  public void addUtensil(final String utensil1)
  {
    utensilInput.addItem(utensil1);
  }

  /**
   * Removes a string "utensil" from the JComboBox so that it will on be displayed in GUI.
   * 
   * @param utensil1
   *          The string to be removed.
   */
  public void removeUtensil(final String utensil1)
  {
    utensilInput.removeItem(utensil1);
  }
  
  public void removeUtensils() {
    utensilInput.removeAllItems();
    
    /*
    InputMap currMap = utensilInput.getInputMap();
    
    for (int i = 0; i < currMap.size(); i++) {
      utensilInput.remove(i);
    }
    */
  }

  /**
   * Removes a string "action" from the JComboBox so that it will on be displayed in GUI.
   * 
   * @param action1
   *          The string to be removed.
   */
  public void removeAction(final String action1)
  {
    // Do nothing
    //actionInput.removeItem(action);
  }
  
  public void removeOns() {
    
    onInput.removeAllItems();
    
    /*
    InputMap currMap = actionInput.getInputMap();
    
    for (int i = 0; i < currMap.size(); i++) {
      actionInput.remove(i);
    }*/
  }
  
  public void removeEntries() {
    steps.removeAllElements();
  }

  /**
   * Getter method for step's DefaultListModel.
   * 
   * @return Step's DefaultListModel
   */
  public DefaultListModel<String> getSteps()
  {
    return steps;
  }
}
