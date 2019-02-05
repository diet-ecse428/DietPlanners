/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.sql.Date;

// line 52 "../../../../../dietplanner_model.ump"
public class Progress
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Progress Attributes
  private int weight;
  private Date date;
  private String picture;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Progress()
  {
    date = null;
    picture = null;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPicture(String aPicture)
  {
    boolean wasSet = false;
    picture = aPicture;
    wasSet = true;
    return wasSet;
  }

  public int getWeight()
  {
    return weight;
  }

  public Date getDate()
  {
    return date;
  }

  public String getPicture()
  {
    return picture;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "weight" + ":" + getWeight()+ "," +
            "picture" + ":" + getPicture()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}