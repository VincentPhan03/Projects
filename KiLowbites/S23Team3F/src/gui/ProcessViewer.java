package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ProcessViewer class displays the utensils used in a meal or recipe and displays all of the steps
 * in a meal or recipe.
 * 
 * 
 * @author phanvm
 * @version Sprint 3
 * 
 *          This work complies with JMU's Honor Code.
 */
public class ProcessViewer extends JFrame
{
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for the ProcessViewer.
   */
  public ProcessViewer()
  {
    setUpLayout("KiLowBites " + MainWindow.STRINGS.getString("PROCESSVIEW_TITLE"));
  }

  /**
   * Sets up the layout of the ProcessViewer.
   * 
   * @param title
   *          The title of the JFrame.
   */
  public void setUpLayout(final String title)
  {
    JPanel processViewerPanel = new ProcessViewerPanel();
    
    setTitle(title);
    setSize(555, 435);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setResizable(false);
    setVisible(true);
    
    add(processViewerPanel);
  }
}
