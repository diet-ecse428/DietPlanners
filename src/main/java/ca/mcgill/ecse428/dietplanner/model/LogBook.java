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
	private Set<Entry> entries; //TODO: make Set<> and do same annotations for user->progress

	private int logbookId;
	//private User user;
	//private String user_id;



	//------------------------
	// INTERFACE
	//------------------------
	/* Code from template association_GetMany */

	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}
	

	public void setId(int id) {
		this.logbookId = id;
	}

	@OneToMany
	@JoinColumn(name="fk_logbook_id", referencedColumnName="logbook_id")
	public Set<Entry> getEntries()
	{
		return entries;
	}
	
	@Id
	@Column(name="logbook_id")
	public int getId() {
		return logbookId;
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

}