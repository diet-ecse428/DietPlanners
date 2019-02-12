/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.util.*;

import javax.persistence.*;

// line 15 "../../../../../dietplanner_model.ump"

@Entity
@Table(name = "logbook")
@NamedQueries({
    @NamedQuery(name = "LogBook.findAll", query = "SELECT e FROM LogBook e")
})
public class LogBook
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//LogBook Associations
	private List<Entry> entries;
	
	private int logbookId;
	//private User user;
	//private String user_id;

	//------------------------
	// CONSTRUCTOR
	//------------------------

	public LogBook()
	{
		entries = new ArrayList<Entry>();
	}

	//------------------------
	// INTERFACE
	//------------------------
	/* Code from template association_GetMany */
	
	@Transient
	public Entry getEntry(int index)
	{
		Entry aEntry = entries.get(index);
		return aEntry;
	}

	@Transient
	public List<Entry> getEntries()
	{
		List<Entry> newEntries = Collections.unmodifiableList(entries);
		return newEntries;
	}

	/* Code from template association_AddUnidirectionalMany */
	@Transient
	public boolean addEntry(Entry aEntry)
	{
		boolean wasAdded = false;
		if (entries.contains(aEntry)) { return false; }
		entries.add(aEntry);
		wasAdded = true;
		return wasAdded;
	}

	@Transient
	public boolean removeEntry(Entry aEntry)
	{
		boolean wasRemoved = false;
		if (entries.contains(aEntry))
		{
			entries.remove(aEntry);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	@Id
	@Column(name="logbook_id")
	public int getId() {
		return logbookId;
	}

	public void setId(int id) {
		this.logbookId = id;
	}

//	@ManyToOne(optional=true)
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

//	@Column(name="user_id")
//	public String getUser_id() {
//		return user_id;
//	}
//
//	public void setUser_id(String user_id) {
//		this.user_id = user_id;
//	}

}