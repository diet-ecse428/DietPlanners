/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.util.*;

import javax.persistence.*;

import java.sql.Date;

// line 3 "../../../../../dietplanner_model.ump"
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT e FROM User e")
})
public class User
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//User Attributes
	private String name;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String height;
	private int targetWeight;
	private Date targetDate;
	private int startWeight;

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

	public boolean setUsername(String aUsername)
	{
		boolean wasSet = false;
		username = aUsername;
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

	public boolean setTargetWeight(int aTargetWeight)
	{
		boolean wasSet = false;
		targetWeight = aTargetWeight;
		wasSet = true;
		return wasSet;
	}

	public boolean setTargetDate(Date aTargetDate)
	{
		boolean wasSet = false;
		targetDate = aTargetDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setStartWeight(int aStartWeight)
	{
		boolean wasSet = false;
		startWeight = aStartWeight;
		wasSet = true;
		return wasSet;
	}

	@Column(name="firstname")
	public String getName()
	{
		return name;
	}

	@Column(name="lastname")
	public String getLastName()
	{
		return lastName;
	}

	@Column(name="email")
	public String getEmail()
	{
		return email;
	}
	
	@Id
	@Column(name="username")
	public String getUsername() {
		return username;
	}

	@Column(name="password")
	public String getPassword()
	{
		return password;
	}

	@Column(name="height", nullable=true)
	public String getHeight()
	{
		return height;
	}

	@Column(name="target_weight", nullable=true)
	public int getTargetWeight()
	{
		return targetWeight;
	}

	@Column(name="target_date", nullable=true)
	public Date getTargetDate()
	{
		return targetDate;
	}

	@Column(name="start_weight", nullable=true)
	public int getStartWeight()
	{
		return startWeight;
	}
	
	/* Code from template association_GetOne */
	@Transient
	public LogBook getLogBook()
	{
		return logBook;
	}


	@Transient
	public List<Progress> getProgresses()
	{
		List<Progress> newProgresses = Collections.unmodifiableList(progresses);
		return newProgresses;
	}
	
	/* Code from template association_SetUnidirectionalOptionalOne */
	public boolean setLogBook(LogBook aNewLogBook)
	{
		boolean wasSet = false;
		logBook = aNewLogBook;
		wasSet = true;
		return wasSet;
	}
	
	/* Code from template association_AddUnidirectionalMany */
	@Transient
	public boolean addProgress(Progress aProgress)
	{
		boolean wasAdded = false;
		if (progresses.contains(aProgress)) { return false; }
		progresses.add(aProgress);
		wasAdded = true;
		return wasAdded;
	}

	@Transient
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
	

}