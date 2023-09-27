package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import cooking.Meal;
import cooking.Recipe;
import cooking.Step;
import cooking.Utensil;

/**
 * ProcessViewerPanel Contains all of the components for ProcessViewer.
 * 
 * @author phanvm
 * @version Sprint 3
 * 
 *          This work complies with JMU's Honor Code.
 */
public class ProcessViewerPanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;

  private static String UTENSILS = "UTENSILS";
  private static String STEPS = "STEPS";
  private String s = "'s ";
  private String colon = ": ";

  private JButton open, add;
  private Icon openIcon, addIcon;
  private String o = "OPEN";
  private String a = "ADD";

  private DefaultListModel<String> utensilModel;
  private JList<String> utensilsLists;
  private JScrollPane utensilScroll;

  private DefaultListModel<String> stepsModel;
  private JList<String> stepsLists;
  private JScrollPane stepsScroll;

  /**
   * Constructor for ProcessViewerPanel.
   */
  public ProcessViewerPanel()
  {
    setUpLayout();
  }

  /**
   * Sets up the components of a ProcessViewerPanel.
   */
  private void setUpLayout()
  {

    setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints();

    addIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_add_box_black_24dp.png"));
    add = new JButton(addIcon);
    add.setActionCommand(MainWindow.STRINGS.getString(a));
    add.addActionListener(this);
    add.setOpaque(false);
    add.setContentAreaFilled(false);
    add.setBorderPainted(false);

    gbc.gridx = 0;
    gbc.gridy = 0;
    add(add, gbc);

    openIcon = new ImageIcon(
        this.getClass().getResource("/icons/baseline_file_open_black_24dp.png"));

    open = new JButton(openIcon);
    open.setContentAreaFilled(false);
    open.setBorderPainted(false);
    open.setActionCommand(MainWindow.STRINGS.getString(o));
    open.addActionListener(this);

    gbc.gridx = 1;
    gbc.gridy = 0;
    add(open, gbc);

    utensilsLists = new JList<String>();
    utensilModel = new DefaultListModel<String>();
    utensilScroll = new JScrollPane(utensilsLists);
    utensilScroll
        .setBorder((BorderFactory.createTitledBorder(MainWindow.STRINGS.getString(UTENSILS))));
    utensilScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.ipady = 125;
    gbc.ipadx = 435;
    gbc.weightx = 0.0;
    gbc.gridwidth = 3;
    add(utensilScroll, gbc);

    stepsLists = new JList<String>();
    stepsModel = new DefaultListModel<String>();
    stepsScroll = new JScrollPane(stepsLists);
    stepsScroll.setBorder((BorderFactory.createTitledBorder(MainWindow.STRINGS.getString(STEPS))));
    stepsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    gbc.gridx = 0;
    gbc.gridy = 3;
    add(stepsScroll, gbc);
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.addChoosableFileFilter(
        new FileNameExtensionFilter(MainWindow.STRINGS.getString("MEAL_FILES"), "mel"));
    fileChooser.addChoosableFileFilter(
        new FileNameExtensionFilter(MainWindow.STRINGS.getString("RECIPE_FILES"), "rcp"));
    fileChooser.setAcceptAllFileFilterUsed(false);
    int result = -1;

    if (e.getActionCommand().equals(MainWindow.STRINGS.getString(o)))
    {
      result = fileChooser.showOpenDialog(this);

      if (result == JFileChooser.APPROVE_OPTION)
      {
        File currFile = fileChooser.getSelectedFile();

        try
        {
          FileInputStream fileInput = new FileInputStream(currFile);
          ObjectInputStream objectInput = new ObjectInputStream(fileInput);
          Object object = objectInput.readObject();

          if (object instanceof Recipe)
          {
            Recipe recipe = (Recipe) object;
            ArrayList<Utensil> utensils = recipe.getUtensils();
            LinkedList<Step> steps = recipe.getSteps();

            for (int i = 0; i < utensils.size(); i++)
            {
              utensilModel.addElement(utensils.get(i).getName());
            }
            utensilsLists.setModel(utensilModel);

            for (int i = 0; i < steps.size(); i++)
            {
              stepsModel.addElement(steps.get(i).stepToString());
            }
            stepsLists.setModel(stepsModel);
          }
          else if (object instanceof Meal)
          {
            Meal meal = (Meal) object;
            ArrayList<Recipe> recipes = meal.getRecipies();

            for (int i = 0; i < recipes.size(); i++)
            {
              Recipe currRecipe = recipes.get(i);

              ArrayList<Utensil> currUtensils = currRecipe.getUtensils();
              LinkedList<Step> currSteps = currRecipe.getSteps();

              utensilModel.addElement(
                  recipes.get(i).getName() + s + MainWindow.STRINGS.getString(UTENSILS) + colon);
              for (int j = 0; j < currUtensils.size(); j++)
              {
                utensilModel.addElement(currUtensils.get(j).getName());
              }
              utensilsLists.setModel(utensilModel);

              stepsModel.addElement(
                  recipes.get(i).getName() + s + MainWindow.STRINGS.getString(STEPS) + colon);
              for (int k = 0; k < currSteps.size(); k++)
              {
                stepsModel.addElement(currSteps.get(k).stepToString());
              }
              stepsLists.setModel(stepsModel);
            }
          }

          objectInput.close();
        }
        catch (ClassNotFoundException | IOException exception)
        {
          exception.printStackTrace();
        }
      }
    }
    else if (e.getActionCommand().equals(MainWindow.STRINGS.getString(a)))
    {
      new ProcessViewer();
    }
  }
}
