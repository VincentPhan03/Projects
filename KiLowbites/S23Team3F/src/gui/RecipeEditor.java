package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import utilities.RecipeManager;

/**
 * GUI for RecipeEditor.
 * 
 * @author phanvm, Cole T. Strubhar
 * @version Sprint 1 
 * @param <E>
 * 
 *          This work complies with JMU's Honor code.
 */
public class RecipeEditor <E> extends JFrame
{
  private final String title = "KiLowBites " + MainWindow.STRINGS.getString("RECIPE_EDITOR_TITLE");
  
  private RecipeManager recipeManager;
  private static JFrame frame;
  private RecipeEditorPanel recipeEditorPanel;
  private UtensilsPanel utensilsPanel;
  private IngredientsPanel ingredientsPanel;
  private StepsPanel stepsPanel;
  
  public RecipeEditor(JFrame frame)
  {
    recipeManager = new RecipeManager();
    
    setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
    setTitle(title);
    setVisible(true);
    setSize(700, 700);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setResizable(true);
        
    JPanel recipeEditorPanel = new RecipeEditorPanel(recipeManager, this);
    recipeEditorPanel.setPreferredSize(new Dimension(300, 200));
    this.recipeEditorPanel = (RecipeEditorPanel) recipeEditorPanel;
    add(recipeEditorPanel);
    
    JPanel utensils = new UtensilsPanel(recipeManager);
    recipeManager.addUtensilsPanel((UtensilsPanel) utensils);
    utensils.setPreferredSize(new Dimension(600, 400));
    this.utensilsPanel = (UtensilsPanel) utensils;
    add(utensils);
    
    JPanel ingredients = new IngredientsPanel(recipeManager);
    recipeManager.addIngredientsPanel((IngredientsPanel) ingredients);
    ingredients.setPreferredSize(new Dimension(600, 400));
    this.ingredientsPanel = (IngredientsPanel) ingredients;
    add(ingredients);
    
    JPanel steps = new StepsPanel(recipeManager, (UtensilsPanel) utensils);
    recipeManager.addStepsPanel((StepsPanel) steps);
    steps.setPreferredSize(new Dimension(600, 400));
    this.stepsPanel = (StepsPanel) steps;
    add(steps);
  }
  
  public RecipeEditorPanel getRecipeEditorPanel() {
    return recipeEditorPanel;
  }
  
  public UtensilsPanel getUtensilsPanel() {
    return utensilsPanel;
  }
  
  public IngredientsPanel getIngredientsPanel() {
    return ingredientsPanel;
  }
  
  public StepsPanel getStepsPanel() {
    return stepsPanel;
  }
  
  public static <E> void main(String[] args) {
    JFrame f = null;
    f = new RecipeEditor<E>(f);
    f.setVisible(true);
  }
}
