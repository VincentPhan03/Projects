package cooking;
/**
 * Food / Ingredients enum is a list of pre-loaded ingredients that we
 * were already given.
 * 
 * @author Jared Householder
 *
 */
public enum Food
{
  Alcohol(275, 0.79, true),
  Almond(601, 0.46, false),
  AmericanCheese(440, 0.34, false),
  Apple(44, 0.56, false),
  AppleJuice(48, 1.04, true),
  Banana(65, 0.56, false),
  Bean(130, 0.77, false),
  Beef(280, 1.05, false),
  BlackBerry(25, 0.53, false),
  BlackPepper(255, 1.01, true),
  Bread(240, 0.42, false),
  Broccoli(32, 0.37, false),
  BrownSugar(380, 1.5, true),
  Butter(750, 0.91, false),
  Cabbage(28, 0.36, false),
  Carrot(41, 0.64, false),
  Cashew(553, 0.5, false),
  Cauliflower(25, 0.27, false),
  Celery(14, 0.61, false),
  CheddarCheese(440, 0.34, false),
  Cherry(50, 1.02, false),
  Chicken(200, 1.04, false),
  Chocolate(500, 1.33, false),
  Cinnamon(261, 0.45, true),
  Cod(100, 0.58, false),
  Corn(130, 0.72, false),
  CornFlake(370, 0.12, false),
  CottageCheese(98, 0.96, false),
  Crab(110, 0.61, false),
  CremeDeCacao(275, 0.79, true),
  Cucumber(10, 0.67, false),
  Egg(150, 0.6, false),
  Flour(64, 0.45, true),
  Garlic(111, 0.32, false),
  GrapeFruit(32, 0.33, false),
  Grape(62, 0.37, false),
  GrapeJuice(60, 1.04, true),
  GreenBean(31, 0.53, false),
  Haddock(110, 0.58, false),
  Ham(240, 1.4, false),
  Honey(280, 1.5, true),
  IceCream(180, 0.55, false),
  KidneyBean(333, 0.79, false),
  Lamb(200, 1.3, false),
  Lemon(29, 0.77, false),
  Lentil(116, 0.85, false),
  Lettuce(15, 0.06, false),
  Macaroni(371, 1.31, false),
  Milk(70, 1.04, true),
  Mushroom(15, 1.17, false),
  Oil(900, 0.88, true),
  Olive(80, 0.65, false),
  Onion(22, 0.74, false),
  Orange(30, 0.77, false),
  Paprika(282, 0.46, true),
  Pasta(371, 1.31, false),
  Peach(30, 0.61, false),
  Peanut(567, 0.53, false),
  Pear(16, 0.61, false),
  Peas(148, 0.73, false),
  Pepper(20, 0.51, true),
  Pineapple(40, 0.54, false),
  Plum(39, 0.58, false),
  Pork(290, 0.7, false),
  Rum(275, 0.79, true),
  Salmon(80, 0.58, false),
  Salt(0, 1.38, true),
  SaltineCrackers(421, 0.43, false),
  Spaghetti(371, 1.31, false),
  Spinach(8, 0.08, false),
  Strawberries(30, 0.58, false),
  Sugar(400, 0.95, true),
  SweetPotato(86, 0.65, false),
  Syrup(260, 1.38, true),
  Thyme(101, 0.46, false),
  Tomato(20, 0.67, false),
  Wine(83, 0.99, true);
  
  private final double gramsPerMili;
  private final int caloriesPerHundredGrams;
  private boolean volumetric;
  
  /**
   * Explicit Value Constructor.
   * 
   * @param calories the calories per hundred grams of item
   * @param measure the density of the item
   * @param volumetric if it is a volumetric or not
   */
  private Food(final int calories, final double measure, final boolean volumetric) 
  {
    this.caloriesPerHundredGrams = calories;
    this.gramsPerMili = measure;
    this.volumetric = volumetric;
  }
  
  
  /**
   * getCalories method will return the calories of the ingredient
   * per hundered grams.
   * 
   * @return the calories per hundred grams of item
   */
  public int getCalories() 
  {
    return this.caloriesPerHundredGrams;
  }
  
  /**
   * getMeasure method will return the density of the ingredient.
   * 
   * @return the density of the item
   */
  public double getMeasure() 
  {
    return this.gramsPerMili;
  }
  
  /**
   * isVolumetric method returns true or false if the ingredient
   * is measured as volumetric or not.
   * 
   * @return true if volumetric or false otherwise
   */
  public boolean isVolumetric() 
  {
    return this.volumetric;
  }
}
