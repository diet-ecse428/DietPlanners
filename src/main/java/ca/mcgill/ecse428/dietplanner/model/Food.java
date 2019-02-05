/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;

// line 32 "../../../../../dietplanners_model.ump"
public class Food
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum MealType { Breakfast, Lunch, Dinner, Snack }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Food Attributes
  private MealType mealType;
  private int calories;
  private double serving;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Food(MealType aMealType, int aCalories, double aServing)
  {
    mealType = aMealType;
    calories = aCalories;
    serving = aServing;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMealType(MealType aMealType)
  {
    boolean wasSet = false;
    mealType = aMealType;
    wasSet = true;
    return wasSet;
  }

  public boolean setCalories(int aCalories)
  {
    boolean wasSet = false;
    calories = aCalories;
    wasSet = true;
    return wasSet;
  }

  public boolean setServing(double aServing)
  {
    boolean wasSet = false;
    serving = aServing;
    wasSet = true;
    return wasSet;
  }

  public MealType getMealType()
  {
    return mealType;
  }

  public int getCalories()
  {
    return calories;
  }

  public double getServing()
  {
    return serving;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "calories" + ":" + getCalories()+ "," +
            "serving" + ":" + getServing()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mealType" + "=" + (getMealType() != null ? !getMealType().equals(this)  ? getMealType().toString().replaceAll("  ","    ") : "this" : "null");
  }
}