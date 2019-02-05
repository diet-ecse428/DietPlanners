/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;

// line 45 "../../../../../dietplanners_model.ump"
public class Workout
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Workout Attributes
  private int duration;
  private int caloriesLost;
  private String type;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Workout(int aDuration, int aCaloriesLost, String aType)
  {
    duration = aDuration;
    caloriesLost = aCaloriesLost;
    type = aType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public boolean setCaloriesLost(int aCaloriesLost)
  {
    boolean wasSet = false;
    caloriesLost = aCaloriesLost;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public int getDuration()
  {
    return duration;
  }

  public int getCaloriesLost()
  {
    return caloriesLost;
  }

  public String getType()
  {
    return type;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "duration" + ":" + getDuration()+ "," +
            "caloriesLost" + ":" + getCaloriesLost()+ "," +
            "type" + ":" + getType()+ "]";
  }
}