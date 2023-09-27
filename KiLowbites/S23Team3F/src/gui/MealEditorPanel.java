package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cooking.Meal;
import cooking.Recipe;
import utilities.MealManager;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * JPanel for MealEditor.
 * 
 * @author phanvm
 * @version 4/26/2023
 * 
 *          This work complies with JMU's Honor Code.
 */
public class MealEditorPanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private JList<String> recipeList;
  private DefaultListModel<String> recipeModel;

  private final JLabel name = new JLabel(MainWindow.STRINGS.getString("NAME") + ":");
  private JTextField textField = new JTextField();

  private JButton save, saveAs, open, add, close, addButton, delete;
  private Icon saveIcon, saveAsIcon, openIcon, addIcon, closeIcon;

  private String a = "ADD";
  private String o = "OPEN";
  private String s = "SAVE";
  private String sa = "SAVE_AS";
  private String c = "CLOSE";
  private String d = "DELETE";
  private String ar = "addrecipe";

  private MealManager mm = new MealManager();

  /**
   * Constructor for MealEditorPanel.
   */
  public MealEditorPanel()
  {
    setupLayout();
  }

  /**
   * Sets up the look and feel of mealeditor.
   */
  public void setupLayout()
  {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    addIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_add_box_black_24dp.png"));
    add = new JButton(addIcon);
    add.setActionCommand(a);
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
    open.setActionCommand(o);
    open.addActionListener(this);
    open.setOpaque(false);
    open.setContentAreaFilled(false);
    open.setBorderPainted(false);

    gbc.gridx = 1;
    gbc.gridy = 0;
    add(open, gbc);

    saveIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_save_black_24dp.png"));
    save = new JButton(saveIcon);
    save.setActionCommand(s);
    save.addActionListener(this);
    save.setOpaque(false);
    save.setContentAreaFilled(false);
    save.setBorderPainted(false);

    gbc.gridx = 2;
    gbc.gridy = 0;
    add(save, gbc);

    saveAsIcon = new ImageIcon(
        this.getClass().getResource("/icons/baseline_save_as_black_24dp.png"));
    saveAs = new JButton(saveAsIcon);
    saveAs.setActionCommand(sa);
    saveAs.addActionListener(this);
    saveAs.setOpaque(false);
    saveAs.setContentAreaFilled(false);
    saveAs.setBorderPainted(false);

    gbc.gridx = 3;
    gbc.gridy = 0;
    add(saveAs, gbc);

    closeIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_close_black_24dp.png"));
    close = new JButton(closeIcon);
    close.setActionCommand(c);
    close.addActionListener(this);
    close.setOpaque(false);
    close.setContentAreaFilled(false);
    close.setBorderPainted(false);

    gbc.gridx = 4;
    gbc.gridy = 0;
    add(close, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    add(name, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1;
    gbc.gridwidth = 20;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    add(textField, gbc);

    addButton = new JButton(MainWindow.STRINGS.getString("ADD_RECIPE"));
    addButton.setActionCommand(ar);
    addButton.addActionListener(this);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.weightx = 0.0;
    add(addButton, gbc);

    recipeList = new JList<String>();
    recipeModel = new DefaultListModel<String>();
    JScrollPane sp = new JScrollPane(recipeList);
    sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.ipadx = 450;
    gbc.ipady = 500;
    gbc.weighty = 1.0;
    add(sp, gbc);

    this.delete = new JButton(MainWindow.STRINGS.getString(d));
    delete.setActionCommand(d);
    delete.addActionListener(this);
    gbc.ipadx = 0;
    gbc.ipady = 0;
    gbc.gridx = 25;
    gbc.gridy = 5;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    add(delete, gbc);

    save.setEnabled(false);
    saveAs.setEnabled(false);
    delete.setEnabled(false);
  }

  /**
   * Getter method for recipes.
   * 
   * @return The DefaultListModel of recipes.
   */
  public DefaultListModel<String> getRecipes()
  {
    return recipeModel;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(
        new FileNameExtensionFilter(MainWindow.STRINGS.getString("MEAL_FILES"), "mel"));
    fileChooser.setAcceptAllFileFilterUsed(false);
    int result = -1;

    if (e.getActionCommand().equals(o))
    {
      textField.setText("");
      recipeModel.clear();
      recipeList.setModel(recipeModel);
      
      result = fileChooser.showOpenDialog(this);

      if (result == JFileChooser.APPROVE_OPTION)
      {
        File currFile = fileChooser.getSelectedFile();
        try
        {
          Meal openedMeal = mm.openFile(currFile);

          textField.setText(openedMeal.getName());
          for (int i = 0; i < openedMeal.getRecipies().size(); i++)
          {
            Recipe temp = (Recipe) openedMeal.getRecipies().get(i);
            recipeModel.addElement(temp.getName());
          }

          recipeList.setModel(recipeModel);
        }
        catch (ClassNotFoundException | IOException e1)
        {
          e1.printStackTrace();
        }
      }

      save.setEnabled(true);
      saveAs.setEnabled(true);

      if (recipeModel.size() > 0)
      {
        delete.setEnabled(true);
      }

    }
    else if (e.getActionCommand().equals(s))
    {
      try
      {
        mm.saveFile();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else if (e.getActionCommand().equals(sa))
    {
      mm.setName(textField.getText());
      try
      {
        result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
          File currFile = fileChooser.getSelectedFile();
          mm.saveFileAs(currFile);
        }
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else if (e.getActionCommand().equals(a))
    {
      new MealEditor();
    }
    else if (e.getActionCommand().equals(ar))
    {
      JFileChooser fileChooser1 = new JFileChooser();
      int result1 = -1;

      fileChooser1.setFileFilter(new FileNameExtensionFilter("Recipe Files", "rcp"));
      fileChooser1.setAcceptAllFileFilterUsed(false);
      result1 = fileChooser1.showOpenDialog(this);

      if (result1 == JFileChooser.APPROVE_OPTION)
      {
        File selected = fileChooser1.getSelectedFile();
        recipeModel.addElement(mm.addRecipe(selected));
      }

      recipeList.setModel(recipeModel);
      if (textField.getText().length() > 0)
      {
        saveAs.setEnabled(true);
      }
      delete.setEnabled(true);
    }
    else if (e.getActionCommand().equals(d))
    {
      String recipeToDelete = recipeList.getSelectedValue();
      Recipe temp = mm.removeRecipe(recipeToDelete);

      recipeModel.removeElement(temp.getName());
      recipeList.setModel(recipeModel);

      if (recipeModel.size() == 0)
      {
        save.setEnabled(false);
        delete.setEnabled(false);
        saveAs.setEnabled(false);
      }
    }
    else if (e.getActionCommand().equals(c))
    {
      textField.setText("");
      recipeModel.clear();
      recipeList.setModel(recipeModel);
      save.setEnabled(false);
      delete.setEnabled(false);
      saveAs.setEnabled(false);
    }

  }
}
