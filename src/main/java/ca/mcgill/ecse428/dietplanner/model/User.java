/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../dietplanner_model.ump"
public class User
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//User Attributes
	private String name;
	private String lastName;
	private String email;
	private String password;
	private String height;
	private String targetWeight;
	private String targetDate;
	private String startWeight;

	//User Associations
	private LogBook logBook;
	private List<Progress> progresses;


	//------------------------
	// INTERFACE
	//------------------------

	public boolean setName(String aName)
	{
		boolean wasSet = false;
		name = aName;
		wasSet = true;
		return wasSet;
	}

	public boolean setLastName(String aLastName)
	{
		boolean wasSet = false;
		lastName = aLastName;
		wasSet = true;
		return wasSet;
	}

	public boolean setEmail(String aEmail)
	{
		boolean wasSet = false;
		email = aEmail;
		wasSet = true;
		return wasSet;
	}

	public boolean setPassword(String aPassword)
	{
		boolean wasSet = false;
		password = aPassword;
		wasSet = true;
		return wasSet;
	}

	public boolean setHeight(String aHeight)
	{
		boolean wasSet = false;
		height = aHeight;
		wasSet = true;
		return wasSet;
	}

	public boolean setTargetWeight(String aTargetWeight)
	{
		boolean wasSet = false;
		targetWeight = aTargetWeight;
		wasSet = true;
		return wasSet;
	}

	public boolean setTargetDate(String aTargetDate)
	{
		boolean wasSet = false;
		targetDate = aTargetDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setStartWeight(String aStartWeight)
	{
		boolean wasSet = false;
		startWeight = aStartWeight;
		wasSet = true;
		return wasSet;
	}

	public String getName()
	{
		return name;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public String getHeight()
	{
		return height;
	}

	public String getTargetWeight()
	{
		return targetWeight;
	}

	public String getTargetDate()
	{
		return targetDate;
	}

	public String getStartWeight()
	{
		return startWeight;
	}
	/* Code from template association_GetOne */
	public LogBook getLogBook()
	{
		return logBook;
	}

	public boolean hasLogBook()
	{
		boolean has = logBook != null;
		return has;
	}
	/* Code from template association_GetMany */
	public Progress getProgress(int index)
	{
		Progress aProgress = progresses.get(index);
		return aProgress;
	}

	public List<Progress> getProgresses()
	{
		List<Progress> newProgresses = Collections.unmodifiableList(progresses);
		return newProgresses;
	}

	public int numberOfProgresses()
	{
		int number = progresses.size();
		return number;
	}

	public boolean hasProgresses()
	{
		boolean has = progresses.size() > 0;
		return has;
	}

	public int indexOfProgress(Progress aProgress)
	{
		int index = progresses.indexOf(aProgress);
		return index;
	}
	/* Code from template association_SetUnidirectionalOptionalOne */
	public boolean setLogBook(LogBook aNewLogBook)
	{
		boolean wasSet = false;
		logBook = aNewLogBook;
		wasSet = true;
		return wasSet;
	}
	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfProgresses()
	{
		return 0;
	}
	/* Code from template association_AddUnidirectionalMany */
	public boolean addProgress(Progress aProgress)
	{
		boolean wasAdded = false;
		if (progresses.contains(aProgress)) { return false; }
		progresses.add(aProgress);
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeProgress(Progress aProgress)
	{
		boolean wasRemoved = false;
		if (progresses.contains(aProgress))
		{
			progresses.remove(aProgress);
			wasRemoved = true;
		}
		return wasRemoved;
	}
	/* Code from template association_AddIndexControlFunctions */
	public boolean addProgressAt(Progress aProgress, int index)
	{  
		boolean wasAdded = false;
		if(addProgress(aProgress))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfProgresses()) { index = numberOfProgresses() - 1; }
			progresses.remove(aProgress);
			progresses.add(index, aProgress);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveProgressAt(Progress aProgress, int index)
	{
		boolean wasAdded = false;
		if(progresses.contains(aProgress))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfProgresses()) { index = numberOfProgresses() - 1; }
			progresses.remove(aProgress);
			progresses.add(index, aProgress);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addProgressAt(aProgress, index);
		}
		return wasAdded;
	}

}