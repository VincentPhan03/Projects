package gui;

import java.awt.*;

import javax.swing.*;


/**
 * RecipeSearch GUI.
 *
 * @author phanvm
 * @version Sprint 2
 *
 *          This work complies with JMU's Honor Code.
 */
public class RecipeSearch extends JFrame
{
  private static final long serialVersionUID = 1L;


  /**
   * Constructor for RecipeSearch.
   */
  public RecipeSearch()
  {
    setupLayout();
  }

  /**
   * Sets the look and feel of the window.
   */
  public void setupLayout()
  {
    setTitle(MainWindow.STRINGS.getString("RECIPE_SEARCH_TITLE"));
    setSize(510, 350);
    setResizable(false);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setLayout(new BorderLayout());
    setVisible(true);
    
    JPanel searchPanel = new SearchPanel(MainWindow.STRINGS.getString("RECIPES"));
    add(searchPanel, BorderLayout.CENTER);
  }
}
