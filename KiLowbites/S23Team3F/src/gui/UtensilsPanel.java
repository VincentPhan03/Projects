package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import utilities.RecipeManager;

/**
 * Displays the panel.
 * 
 * @author phanvm, Cole T. Strubhar
 * @version Sprint 1
 * 
 *          This work complies with JMU's Honor Code.
 */
public class UtensilsPanel extends JPanel implements ActionListener
{

  private static final long serialVersionUID = 1L;
  

  private static final String DETAILS = "DETAILS";
  private static final String ADD = "ADD";
  private static final String DELETE = "DELETE";
  private static final String NAME = "NAME";

  private final String title = MainWindow.STRINGS.getString("UTENSILS");

  private JLabel name;
  private JLabel detail;

  private JTextField nameInput;
  private JTextField detailInput;

  private JButton add;
  private JButton delete;

  private DefaultListModel<String> utensil = new DefaultListModel<String>();
  private JList<String> utensilList = new JList<String>(utensil);

  private int currentPlacementIndex;

  private RecipeManager recipeManager;
  private final String nameString = "Name:";
  private final String detailString = "Details:";
  private final String addString = "Add";
  private final String deleteString = "Delete";
  
  private String colon = ":";

  /**
   * Constructor for UtensilsPanel adds and displays the components for UtensilsPanel.
   */
  public UtensilsPanel()
  {
    currentPlacementIndex = 0;

    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createTitledBorder(title));
    setVisible(true);

    // Constraints
    GridBagConstraints gbc = new GridBagConstraints();

    this.name = new JLabel(MainWindow.STRINGS.getString(NAME) + colon);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 0, 10); // Add some space on the top
    add(name, gbc);

    this.nameInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(nameInput, gbc);

    this.detail = new JLabel(MainWindow.STRINGS.getString(DETAILS) + colon);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(detail, gbc);

    this.detailInput = new JTextField();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(detailInput, gbc);

    this.add = new JButton(MainWindow.STRINGS.getString(ADD));
    gbc.gridx = 8;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 1.0;
    add(add, gbc);

    // this.utensilList = new JList<String>(utensil);
    JScrollPane scrollPane = new JScrollPane(utensilList);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 8;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    add(scrollPane, gbc);

    this.delete = new JButton(MainWindow.STRINGS.getString(DELETE));
    gbc.gridx = 8;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    add(delete, gbc);
    delete.setActionCommand(deleteString);
    delete.addActionListener(this);

    add.addActionListener(this);

    recipeManager = new RecipeManager();

    // RecipeManager.addUtensilsPanel(this);

  }

  /**
   * Constructor for UtensilsPanel adds and displays the components for UtensilsPanel.
   * 
   * @param recipeManager
   *          Handles functionality for UtensilsPanel
   */
  public UtensilsPanel(final RecipeManager recipeManager)
  {

    currentPlacementIndex = 0;

    setLayout(new GridBagLayout());
    setBorder(BorderFactory.createTitledBorder(title));
    setVisible(true);

    // Constraints
    GridBagConstraints gbc = new GridBagConstraints();

    this.name = new JLabel(MainWindow.STRINGS.getString(NAME) + colon);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 0, 10); // Add some space on the top
    add(name, gbc);

    this.nameInput = new JTextField();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL; // Make the component fill horizontally
    gbc.weightx = 1.0; // Give the component horizontal space
    add(nameInput, gbc);

    this.detail = new JLabel(MainWindow.STRINGS.getString(DETAILS) + colon);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    add(detail, gbc);

    this.detailInput = new JTextField();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;
    add(detailInput, gbc);

    this.add = new JButton(MainWindow.STRINGS.getString(ADD));
    gbc.gridx = 8;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 1.0;
    add(add, gbc);

    // this.utensilList = new JList<String>(utensil);
    JScrollPane scrollPane = new JScrollPane(utensilList);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 8;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;
    add(scrollPane, gbc);

    this.delete = new JButton(MainWindow.STRINGS.getString(DELETE));
    gbc.gridx = 8;
    gbc.gridy = 2;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    add(delete, gbc);
    delete.setActionCommand(deleteString);
    delete.addActionListener(this);

    add.addActionListener(this);

    this.recipeManager = recipeManager;

    // RecipeManager.addUtensilsPanel(this);

  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {

    if (e.getActionCommand().equals(addString))
    {
      boolean sucsess = recipeManager.addUtensil(nameInput.getText(), detailInput.getText());
      if (sucsess)
      {
        utensil.add(currentPlacementIndex, nameInput.getText());
      }
    }

    if (e.getActionCommand().equals(deleteString))
    {
      int toRemoveIndex = utensilList.getSelectedIndex();

      recipeManager.removeUtensil(utensil.get(toRemoveIndex));

      utensil.remove(toRemoveIndex);
    }
  }

  /**
   * Getter method for utensil's DefaultListModel.
   * 
   * @return Utensil's DefaultListModel.
   */
  public DefaultListModel<String> getUtensil()
  {
    return utensil;
  }
}
