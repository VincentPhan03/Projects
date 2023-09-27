package math;
/**
 * the units used to convert to and from.
 * @author Andrew Davis
 *
 */
public enum FromUnits
{
  
  //example; from 1 ounce, double vals are to: drams->grams then pound.
  //example2; 1 pound = 16 ounce
  POUND("Pound", new double[] {16.0}),
  DRAM("Dram", new double[] {(1.0/16.0)}),
  GRAM("Gram", new double[] {(1.0/28.34952)}),
  OUNCE("Ounce", new double[] {16.0, 28.34952, (1.0/16.0)}),
  
  TEASPOON("Teaspoon", new double[] {(1.0/3.0), 16.0}),
  PINCH("Pinch", new double[] {(1.0/16.0)}),
  TABLESPOON("Tablespoon", new double[] {3.0, (1.0/2.0), 14.7867648}),
  FLUIDOUNCE("Fluid Ounce", new double[] {2.0, 1.0/8.0, 29.57353}),
  CUP("Cup", new double[] {8.0, 1.0/2.0, 236.58824}),
  MILLILITER("Milliliter", new double[] {(1.0/236.58824), (1.0/29.57353), (1.0/14.7867648)}),
  PINT("Pint", new double[] {2.0, 1.0/2.0}),
  QUART("Quart", new double[] {2.0, 1.0/4.0}),
  GALLON("Gallon", new double[] {4.0});
    
  

  private String unit;
  private double[] scale;
  
  FromUnits(final String unit, final double[] scale)
  {
    // TODO Auto-generated constructor stub
    this.unit = unit;
    this.scale = scale;
  }

  /**
   * getter for the unit.
   * @return the unit being requested
   */
  public String getUnit()
  {
    return unit;
  }

  /**
   * getter for the scale.
   * @return the scale multiplier
   */
  public double[] getScale()
  {
    return scale;
  }
  
  
  

}
