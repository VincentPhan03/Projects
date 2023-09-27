/**
 * 
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cooking.Ingredient;
import cooking.Recipe;
import utilities.RecipeManager;

/**
 * @author Cole T. Strubhar
 * @version 4/30/2023
 *
 */

public class Substitution implements ActionListener
{
  private Recipe recipe;
  private RecipeManager recipeManager;
  private JComboBox mainCombo;
  private JFrame mainFrame;
  private String nameInput;
  private String selected;
  private IngredientsPanel ingredientsPanel;
  
  
  public static void main(String[] args) {
    new Substitution( new IngredientsPanel(), "", new RecipeManager());
  }
  
  public Substitution(IngredientsPanel ingredientsPanel, String nameInput, RecipeManager recipeManager) {
    
    this.recipeManager = recipeManager;
    this.ingredientsPanel = ingredientsPanel;
    this.nameInput = nameInput;
    selected = "";
    
    mainFrame = new JFrame(MainWindow.STRINGS.getString("SUBSTITUTIONS"));
    mainFrame.setSize(400, 400);
    
    JPanel panel = new JPanel();
    panel.setSize(400, 400);
    
    panel.setVisible(true);
    
    GridBagLayout myLayout = new GridBagLayout();
    GridBagConstraints myConstraints = new GridBagConstraints();
    mainFrame.setLayout(myLayout);
    
    myConstraints.insets = new Insets(10, 10, 10, 10);
    myConstraints.gridx = 0;
    myConstraints.gridy = 0;
    // myConstraints.gridwidth = 30000;
    panel.add(new JLabel(MainWindow.STRINGS.getString("SUBSTITUTION_FOR") + ": "), myConstraints);
    
    myConstraints.gridx = 1;
    
    ArrayList<String> populatedArrayList = getPopulatedArrayList(recipe);
    mainCombo = new JComboBox(populatedArrayList.toArray());
    
    panel.add(mainCombo, myConstraints);
    
    myConstraints.gridx = 2;
    JButton sub = new JButton(MainWindow.STRINGS.getString("SUBSTITUTE"));
    sub.setActionCommand("ADD");
    sub.addActionListener(this);
    
    panel.add(sub);
    mainFrame.add(panel);
    mainFrame.setVisible(true);
  }
  
  
  private ArrayList<String> getPopulatedArrayList(Recipe recipe) {
    DefaultListModel<String> ingredients = ingredientsPanel.getIngredients();
    ArrayList<String> labels = new ArrayList<>();
    
    for (int i = 0; i < ingredients.size(); i++) {
      labels.add(ingredients.get(i));
    }
    
    return labels;
  }
  
  public String getSelected() {
    if (selected == null) {
      // System.err.println("getSubstitution(): NOTHING HAS BEEN ASSIGNED FOR SELECTED!");
      return null;
    }
    return selected;
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("ADD")) {
      selected = (String) mainCombo.getSelectedItem();
      recipeManager.addSubstitution(selected);
      ingredientsPanel.substitutionSelected(nameInput, selected);
      // recipeManager.substitutionDesired();
      mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
      
    }
  }
}
