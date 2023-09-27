package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cooking.Ingredient;
import cooking.IngredientDatabase;
import math.FromUnits;
import math.UnitConversion;

/**
 * The unit Conversion GUI.
 * 
 * @author Andrew Davis
 *
 */
@SuppressWarnings("rawtypes")
public class UnitConverterWindow implements ActionListener
{

  private static final String CALCULATE = "calculate";
  private static final String RESTORE = "restore";
  private static final String FROM = "fromUnits";
  private static final String TO = "toUnits";
  private static final String FORMAT = "%.2f";
  private static final String COLON = ":";

  private JFrame frame;
  private JButton button1;
  private JButton button2;
  private JComboBox fromUnits;
  private JComboBox toUnits;
  private JComboBox ingredients;
  private JTextField fAmt;
  private JTextField tAmt;

  private String[] mass2 = {FromUnits.POUND.toString(),
     FromUnits.OUNCE.toString(),
      FromUnits.DRAM.toString(),
      FromUnits.GRAM.toString()};

  private String[] volume2 = {FromUnits.TEASPOON.toString(),
      FromUnits.PINCH.toString(),
      FromUnits.TABLESPOON.toString(),
      FromUnits.FLUIDOUNCE.toString(),
      FromUnits.CUP.toString(),
      FromUnits.MILLILITER.toString(),
      FromUnits.PINT.toString(),
      FromUnits.QUART.toString(),
      FromUnits.GALLON.toString()};

  private ArrayList<String> volume = new ArrayList<>(Arrays.asList(volume2));
  private ArrayList<String> mass = new ArrayList<>(Arrays.asList(mass2));

  /**
   * Constructor that initializes the window.
   * 
   * @param args
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public UnitConverterWindow(final String[] args) throws ClassNotFoundException, IOException
  {
    init();
  }

  /**
   * Must construct the GUI components and lay them out (as illustrated in the interaction design
   * portion of this document).
   * 
   * @throws IOException
   * @throws ClassNotFoundException
   */
  @SuppressWarnings("unchecked")
  public void init() throws ClassNotFoundException, IOException
  {

    this.frame = new JFrame(" KiLowBites " + MainWindow.STRINGS.getString("UNIT_CONVERTER"));
    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    JPanel contentPane = (JPanel) frame.getContentPane();

    contentPane.setLayout(null);

    Icon icon2 = new ImageIcon(
        this.getClass().getResource("/icons/calculate_FILL0_wght400_GRAD0_opsz48.png"));
    button2 = new JButton(icon2);
    button2.setBounds(10, 5, 40, 40);
    button2.setActionCommand(CALCULATE);
    button2.addActionListener(this);
    contentPane.add(button2);

    Icon icon = new ImageIcon(
        this.getClass().getResource("/icons/restore_page_FILL0_wght400_GRAD0_opsz48.png"));
    button1 = new JButton(icon);
    button1.setBounds(70, 5, 35, 40);
    button1.setActionCommand(RESTORE);
    button1.addActionListener(this);
    contentPane.add(button1);

    JLabel label = new JLabel(MainWindow.STRINGS.getString("FROM_UNITS") + COLON);
    label.setBounds(50, 100, 65, 25);
    contentPane.add(label);

    FromUnits[] fU = math.FromUnits.values();
    fromUnits = new JComboBox<>();

    for (FromUnits f : fU)
    {
      fromUnits.addItem(f.toString());
    }

    fromUnits.setSelectedIndex(-1);
    fromUnits.setActionCommand(FROM);
    fromUnits.addActionListener(this);
    fromUnits.setBounds(130, 100, 100, 25);
    contentPane.add(fromUnits);

    JLabel label2 = new JLabel(MainWindow.STRINGS.getString("TO_UNITS") + COLON);
    label2.setBounds(260, 100, 60, 25);
    contentPane.add(label2);

    toUnits = new JComboBox<>();
    for (FromUnits f : fU)
    {
      toUnits.addItem(f.toString());
    }

    toUnits.setSelectedIndex(-1);
    toUnits.setActionCommand(TO);
    toUnits.addActionListener(this);
    toUnits.setBounds(325, 100, 100, 25);
    contentPane.add(toUnits);

    JLabel label5 = new JLabel(MainWindow.STRINGS.getString("INGREDIENT") + COLON);
    label5.setBounds(450, 100, 67, 25);
    contentPane.add(label5);
    // IngredientDatabase db = new IngredientDatabase();
    // db.defValueAdd();
    // db.serializeMap();

    Map<String, Ingredient> ingredientDB = IngredientDatabase.deserializeMap();

    TreeSet temp = new TreeSet<>(ingredientDB.keySet());
    Object[] temp2 = temp.toArray();

    ingredients = new JComboBox<>();
    ingredients.removeAllItems();


    for (Object s : temp2)
    {
      String o = (String) s;

      try
      {
        if (!o.equals("default"))
        {
          ingredients.addItem(o);
        }
      }
      catch (IllegalArgumentException e)
      {
        // dont add it
      }

    }
    ingredients.setVisible(false);

    // temp.forEach(ingredients.addItem());

    ingredients.setSelectedIndex(-1);
    ingredients.addActionListener(this);
    ingredients.setBounds(515, 100, 100, 25);
    contentPane.add(ingredients);

    JLabel label3 = new JLabel(MainWindow.STRINGS.getString("FROM_AMOUNT") + COLON);
    label3.setBounds(50, 150, 80, 25);
    contentPane.add(label3);

    fAmt = new JTextField();
    fAmt.setBounds(130, 150, 100, 25);
    contentPane.add(fAmt);
    fAmt.addActionListener(this);

    JLabel label4 = new JLabel(MainWindow.STRINGS.getString("TO_AMOUNT") + COLON);
    label4.setBounds(260, 150, 80, 25);
    contentPane.add(label4);

    tAmt = new JTextField();
    tAmt.setBounds(330, 150, 100, 25);
    contentPane.add(tAmt);
    tAmt.addActionListener(this);

    frame.setSize(780, 325);
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new LineBorder(Color.BLACK));
    // menuBar.setBackground(Color.GRAY);
    frame.setBackground(Color.GRAY);

    frame.setVisible(true);

    contentPane.setVisible(true);

  }

  /**
   * Must handle the events generated by the user clicking on the About and/or Load buttons. When
   * the user clicks on the About button, this method must invoke the handleAbout() method. When the
   * user clicks on the Load button, this method must invoke the handleLoad() method.
   * 
   * @param evt
   *          the action clicked on
   */
  public void actionPerformed(final ActionEvent evt)
  {

    String actionCommand;
    actionCommand = evt.getActionCommand();

    if (actionCommand.equals(FROM))
    {
      // if from units is mass & to units is volume, enable ingredient box
      if ((mass.contains(fromUnits.getSelectedItem())
          && volume.contains(toUnits.getSelectedItem()))
          || (volume.contains(fromUnits.getSelectedItem())
              && mass.contains(toUnits.getSelectedItem())))
      {

        ingredients.setVisible(true);

      }
      else
      {
        ingredients.setVisible(false);
      }
    }

    if (actionCommand.equals(TO))
    {
      // if from units is mass & to units is volume, enable ingredient box
      if ((mass.contains(fromUnits.getSelectedItem())
          && volume.contains(toUnits.getSelectedItem()))
          || (volume.contains(fromUnits.getSelectedItem())
              && mass.contains(toUnits.getSelectedItem())))
      {

        ingredients.setVisible(true);

      }
      else
      {
        ingredients.setVisible(false);
      }
    }

    if (actionCommand.equals(RESTORE))
    {

      fromUnits.setSelectedIndex(-1);
      toUnits.setSelectedIndex(-1);
      ingredients.setSelectedIndex(-1);
      fAmt.setText(null);
      tAmt.setText(null);
      return;
    }

    if (actionCommand.equals(CALCULATE))
    {

      try
      {
        Map<String, Ingredient> ingredientDB = IngredientDatabase.deserializeMap();

        double fromAmt = Double.valueOf(fAmt.getText());

        double output = UnitConversion.calculate(fromUnits.getSelectedItem().toString(),
            toUnits.getSelectedItem().toString(), ingredientDB.get(ingredients.getSelectedItem()),
            fromAmt);

        tAmt.setText(String.format(FORMAT, output));

      }
      catch (NullPointerException | NumberFormatException | ClassNotFoundException | IOException e)
      {
//        System.out.println("From amount/calculate error");
      }

    }

  }

  /**
   * Main method to summon window.
   * 
   * @param args
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public static void main(final String[] args) throws ClassNotFoundException, IOException
  {
    new UnitConverterWindow(null);
  }

}
