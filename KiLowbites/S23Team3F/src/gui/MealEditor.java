package gui;


import javax.swing.*;


/**
 * MealEditor is a JFrame that displays the components to edit a meal.
 * 
 * @author Liam J Herkins and Vincent Phan
 * @version Sprint 3
 * 
 * This work complies with JMU honor code.
 */
public class MealEditor extends JFrame
{

  private static final long serialVersionUID = 1L;

  
  /**
   * Constructor for MealEditor.
   */
  public MealEditor()
  {
    super("KiLowBites " + MainWindow.STRINGS.getString("MEAL_EDITOR_TITLE"));
    run();
  }

  /**
   * Sets up the layout and feel of the JFrame.
   */
  public void setupLayout()
  {
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setResizable(false);
    setSize(600, 500);
    
    MealEditorPanel panel = new MealEditorPanel();
    add(panel);
  }
  
  /**
   * Runs the Jframe.
   */
  public void run() 
  {
    setupLayout();
    setVisible(true);
  }
}
