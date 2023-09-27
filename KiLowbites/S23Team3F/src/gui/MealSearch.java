package gui;

import java.awt.*;
import javax.swing.*;

/**
 * GUI for MealSearch.
 * 
 * @author phanvm
 * @version Sprint 2
 * 
 *          This work complies with JMU's Honor Code.
 */
public class MealSearch extends JFrame
{
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for MealSearch.
   */
  public MealSearch()
  {
    setUpLayout();
  }

  /**
   * Sets up the layout of meal Search.
   */
  public void setUpLayout()
  {
    setTitle(MainWindow.STRINGS.getString("MEALSEARCH_TITLE"));
    setSize(510, 350);
    setResizable(false);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setLayout(new BorderLayout());
    setVisible(true);
    
    JPanel searchPanel = new SearchPanel(MainWindow.STRINGS.getString("MEALS"));
    add(searchPanel, BorderLayout.CENTER);
  }
}
