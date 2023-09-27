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

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cooking.Ingredient;
import cooking.IngredientDatabase;

/**
 * AddIngredient GUI is the GUI for when a user inputs an ingredient that
 * does not already exist in the list of ingredients.
 * 
 * @author Jared Householder
 *
 */
public class AddIngredient implements ActionListener
{
  private JLabel ingredientsLabel, caloriesLabel, densityLabel, volLabel;
  private JButton refresh, add;
  private JFrame window;
  private Container contentPane;
  private JTextField calories, density, ingredient, vol;
  private String addz = "add";
  private String r = "refresh";
  private String colon = ":";

  /**
   * Default Value Constructor.
   *
   */
  public AddIngredient() 
  {
    setupLayout();
  }
  
  /**
   * setupLayout, sets the GUI for the add ingredient.
   */
  public void setupLayout()
  {
    window = new JFrame("KiLowBites " + MainWindow.STRINGS.getString("ADD_ING_TITLE"));
    window.setSize(800,300);
    window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    
    // create calories text field
    calories = new JTextField(10);
    density  = new JTextField(10);
    ingredient = new JTextField(10);
    vol = new JTextField(10);
    
    
    // Create Icons / buttons
    Icon rest = new ImageIcon(this.getClass().getResource("/icons/restore2.png"));
    Icon a = new ImageIcon(this.getClass().getResource("/icons/add.png"));
    refresh = new JButton(rest);
    add = new JButton(a);
    // Make the buttons not have any background stuff
    add.setOpaque(false);
    add.setContentAreaFilled(false);
    add.setBorderPainted(false);
    add.setActionCommand(addz);
    add.addActionListener(this);
    refresh.setOpaque(false);
    refresh.setContentAreaFilled(false);
    refresh.setBorderPainted(false);
    // Create the actionlisteners
    refresh.setActionCommand(r);
    refresh.addActionListener(this);
    
    // Put the buttons in a box to add to panel
    Box box = Box.createHorizontalBox();
    box.add(refresh);
    box.add(add);
    
    
    // Create the labels
    ingredientsLabel = new JLabel(MainWindow.STRINGS.getString("INGREDIENT") + colon);
    densityLabel = new JLabel(MainWindow.STRINGS.getString("DENSITY") + colon);
    caloriesLabel = new JLabel(MainWindow.STRINGS.getString("CALORIES") + colon);
    volLabel = new JLabel(MainWindow.STRINGS.getString("VOLUMETRIC") +  "?: (T/F)");
    
    
    
    // Create JPanel to add to frame using gridbaglayout
    JPanel p = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5,5,5,5);
    c.gridx = 0;
    c.gridy = 1;
    p.add(box);
    c.gridx = 0;
    c.gridy = 2;
    p.add(ingredientsLabel, c);
    c.gridx = 1;
    c.gridy = 2;
    p.add(ingredient, c);
    c.gridx = 2;
    c.gridy = 2;
    p.add(caloriesLabel, c);
    c.gridx = 3;
    c.gridy = 2;
    p.add(calories, c);
    c.gridx = 4;
    c.gridy = 2;
    p.add(densityLabel, c);
    c.gridx = 5;
    c.gridy = 2;
    p.add(density, c);
    c.gridx = 0;
    c.gridy = 3;
    p.add(volLabel, c);
    c.gridx = 1;
    c.gridy = 3;
    p.add(vol, c);
    
    // add Panel to Frame
    window.add(p, BorderLayout.WEST);
    contentPane = window.getContentPane();
    contentPane.setLayout(new FlowLayout(FlowLayout.LEADING));
    window.setVisible(true);
    window.setResizable(false);
  }
  
  
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String actionCommand;
    actionCommand = e.getActionCommand();

    if (actionCommand.equals(r))
    {

      ingredient.setText(null);
      calories.setText(null);
      density.setText(null);
      vol.setText(null);
      return;
    }
    else if (actionCommand.equals(addz)) 
    {
      String t = "T";
      String f = "F";
      boolean volCheck = false;
      String volInput = vol.getText();
      if (volInput.equalsIgnoreCase(t) 
          || volInput.equalsIgnoreCase("True")) 
      {
        volCheck = true;
      } 
      else if (volInput.equalsIgnoreCase(f) || volInput.equalsIgnoreCase("false")) 
      {
        volCheck = false;
      } 
      else 
      {
        throw new IllegalArgumentException("Input was not T or F.");
      }
      String ingredientInput = ingredient.getText();
      double caloriesInput = Double.parseDouble(calories.getText());
      String densityInput = density.getText();
      
      Ingredient ing = new Ingredient(ingredientInput,
          caloriesInput,
              Double.parseDouble(densityInput), volCheck);
      IngredientDatabase id = null;
      try
      {
        id = new IngredientDatabase();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      try
      {
        IngredientDatabase.deserializeMap();
      }
      catch (ClassNotFoundException e2)
      {
        // TODO Auto-generated catch block
        e2.printStackTrace();
      }
      catch (IOException e2)
      {
        // TODO Auto-generated catch block
        e2.printStackTrace();
      }
      id.addIngredient(ing);
      try
      {
        IngredientDatabase.serializeMap();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      ingredient.setText(null);
      calories.setText(null);
      density.setText(null);
      vol.setText(null);
      
    }
    
  }
}
