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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Window;

import utilities.RecipeManager;

/**
 * @author phanvm, Cole T. Strubhar
 * @version Sprint 1
 * 
 *          This work complies with JMU's Honor Code.
 */
public class RecipeEditorPanel extends JPanel implements ActionListener
{
  
  private static final String ADD = "ADD";
  private final String colon = ":";
  private final JLabel name = new JLabel(MainWindow.STRINGS.getString("NAME") + colon);
  private final JLabel serve = new JLabel(MainWindow.STRINGS.getString("SERVES") + colon);


  private JButton save, saveAs, open, add, close, addButton;
  private Icon saveIcon, saveAsIcon, openIcon, addIcon, closeIcon;

  private JTextField nameInput = new JTextField();
  private JTextField serveInput = new JTextField();

  private RecipeManager recipeManager;
  
  private boolean newPressed;
  private RecipeEditor recipeEditor;

  public RecipeEditorPanel()
  {
    recipeManager = new RecipeManager();
    setLayout(new GridBagLayout());
    setVisible(true);
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.weightx = 0.1;

    addIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_add_box_black_24dp.png"));
    add = new JButton(addIcon);
    add.setActionCommand("NEW");
    add.addActionListener(this);
    add.setSize(1, 1);

    gbc.gridx = 0;
    gbc.gridy = 0;
    add(add, gbc);

    openIcon = new ImageIcon(
        this.getClass().getResource("/icons/baseline_file_open_black_24dp.png"));
    open = new JButton(openIcon);
    open.setActionCommand("OPEN");
    open.setSize(1, 1);
    open.addActionListener(this);
    // open.setEnabled(false);
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    add(open, gbc);

    saveIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_save_black_24dp.png"));
    save = new JButton(saveIcon);
    save.setSize(1, 1);
    save.setActionCommand("SAVE");
    save.addActionListener(this);

    gbc.gridx = 2;
    gbc.gridy = 0;
    add(save, gbc);

    saveAsIcon = new ImageIcon(
        this.getClass().getResource("/icons/baseline_save_as_black_24dp.png"));
    saveAs = new JButton(saveAsIcon);
    saveAs.setSize(1, 1);
    saveAs.setActionCommand("SAVE_AS");
    saveAs.addActionListener(this);

    gbc.gridx = 3;
    gbc.gridy = 0;
    add(saveAs, gbc);

    closeIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_close_black_24dp.png"));
    close = new JButton(closeIcon);
    close.setSize(1, 1);
    close.addActionListener(this);

    gbc.gridx = 4;
    gbc.gridy = 0;
    add(close, gbc);

    gbc.insets = new Insets(1, 5, 1, 5);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(name, gbc);

    this.nameInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.5; // Give the component horizontal space
    add(nameInput, gbc);

    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(serve, gbc);

    this.serveInput = new JTextField();
    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.2;
    add(serveInput, gbc);

    this.addButton = new JButton(MainWindow.STRINGS.getString(ADD));
    addButton.addActionListener(this);
    gbc.gridx = 4;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(addButton, gbc);
  }

  public RecipeEditorPanel(RecipeManager recipeManager, RecipeEditor recipeEditor)
  {
    this.recipeManager = recipeManager;
    this.recipeEditor = recipeEditor;
    setLayout(new GridBagLayout());
    setVisible(true);
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.weightx = 0.1;

    addIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_add_box_black_24dp.png"));
    add = new JButton(addIcon);
    add.setActionCommand("NEW");
    add.addActionListener(this);
    add.setSize(1, 1);

    gbc.gridx = 0;
    gbc.gridy = 0;
    add(add, gbc);

    openIcon = new ImageIcon(
        this.getClass().getResource("/icons/baseline_file_open_black_24dp.png"));
    open = new JButton(openIcon);
    open.setActionCommand("OPEN");
    open.setSize(1, 1);
    open.addActionListener(this);

    gbc.gridx = 1;
    gbc.gridy = 0;
    add(open, gbc);

    saveIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_save_black_24dp.png"));
    save = new JButton(saveIcon);
    save.setSize(1, 1);
    save.setActionCommand("SAVE");
    save.addActionListener(this);
    save.setEnabled(false);

    gbc.gridx = 2;
    gbc.gridy = 0;
    add(save, gbc);

    saveAsIcon = new ImageIcon(
        this.getClass().getResource("/icons/baseline_save_as_black_24dp.png"));
    saveAs = new JButton(saveAsIcon);
    saveAs.setSize(1, 1);
    saveAs.setActionCommand("SAVE_AS");
    saveAs.addActionListener(this);
    saveAs.setEnabled(false);

    gbc.gridx = 3;
    gbc.gridy = 0;
    add(saveAs, gbc);

    closeIcon = new ImageIcon(this.getClass().getResource("/icons/baseline_close_black_24dp.png"));
    close = new JButton(closeIcon);
    close.setSize(1, 1);
    close.setActionCommand("CLOSE");
    close.addActionListener(this);

    gbc.gridx = 4;
    gbc.gridy = 0;
    add(close, gbc);

    gbc.insets = new Insets(1, 5, 1, 5);
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(name, gbc);

    this.nameInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.5; // Give the component horizontal space
    add(nameInput, gbc);

    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(serve, gbc);

    this.serveInput = new JTextField();
    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 0.2;
    add(serveInput, gbc);

    this.addButton = new JButton(MainWindow.STRINGS.getString(ADD));
    addButton.addActionListener(this);
    gbc.gridx = 4;
    gbc.gridy = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(addButton, gbc);
  }

  public void actionPerformed(ActionEvent e)
  {

    JFileChooser fileChooser = new JFileChooser();
    int result = -1;

    switch (e.getActionCommand())
    {
      case ("OPEN"):
        result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
          File currFile = fileChooser.getSelectedFile();
          try
          {
            recipeManager.openFile(currFile);
            save.setEnabled(true);
            saveAs.setEnabled(true);
          }
          catch (ClassNotFoundException | IOException e1)
          {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        }
        break;
      case "SAVE":
        try
        {
          recipeManager.saveFile();
        }
        catch (IOException e1)
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        break;
      case "SAVE_AS":
        try
        {
          result = fileChooser.showSaveDialog(this);

          if (result == JFileChooser.APPROVE_OPTION)
          {
            File currFile = fileChooser.getSelectedFile();
            recipeManager.saveFileAs(currFile);
            save.setEnabled(true);
          }
        }
        catch (IOException e1)
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        break;
      case "NEW":
        recipeManager.addReceipe();
        saveAs.setEnabled(true);
        break;

      case "Add":
        recipeManager.setName(nameInput.getText());
        recipeManager.setServings(Integer.valueOf(serveInput.getText()));
        break;
        
      case "CLOSE":
        exit();
        break;
    }
  }

  private void exit() {
    recipeEditor.setVisible(false);
  }
  
  /**
   * @param args
   */
  public static void main(String[] args)
  {
    JPanel p = new RecipeEditorPanel();
    JFrame f = new JFrame();

    f.setSize(700, 400);
    f.add(p);
    f.setVisible(true);
  }

}
