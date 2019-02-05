/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;

// line 39 "../../../../../dietplanners_model.ump"
public class Liquid
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Liquid Attributes
  private int calories;
  private double volume;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Liquid(int aCalories, double aVolume)
  {
    calories = aCalories;
    volume = aVolume;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCalories(int aCalories)
  {
    boolean wasSet = false;
    calories = aCalories;
    wasSet = true;
    return wasSet;
  }

  public boolean setVolume(double aVolume)
  {
    boolean wasSet = false;
    volume = aVolume;
    wasSet = true;
    return wasSet;
  }

  public int getCalories()
  {
    return calories;
  }

  public double getVolume()
  {
    return volume;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "calories" + ":" + getCalories()+ "," +
            "volume" + ":" + getVolume()+ "]";
  }
}