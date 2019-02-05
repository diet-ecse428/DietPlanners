/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse428.dietplanner.model;
import java.util.*;
import java.sql.Date;

// line 15 "../../../../../dietplanner_model.ump"
public class LogBook
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//LogBook Associations
	private List<Entry> entries;

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
	public Entry getEntry(int index)
	{
		Entry aEntry = entries.get(index);
		return aEntry;
	}

	public List<Entry> getEntries()
	{
		List<Entry> newEntries = Collections.unmodifiableList(entries);
		return newEntries;
	}

	public int numberOfEntries()
	{
		int number = entries.size();
		return number;
	}

	public boolean hasEntries()
	{
		boolean has = entries.size() > 0;
		return has;
	}

	public int indexOfEntry(Entry aEntry)
	{
		int index = entries.indexOf(aEntry);
		return index;
	}
	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfEntries()
	{
		return 0;
	}
	/* Code from template association_AddUnidirectionalMany */
	public boolean addEntry(Entry aEntry)
	{
		boolean wasAdded = false;
		if (entries.contains(aEntry)) { return false; }
		entries.add(aEntry);
		wasAdded = true;
		return wasAdded;
	}

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
	/* Code from template association_AddIndexControlFunctions */
	public boolean addEntryAt(Entry aEntry, int index)
	{  
		boolean wasAdded = false;
		if(addEntry(aEntry))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
			entries.remove(aEntry);
			entries.add(index, aEntry);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveEntryAt(Entry aEntry, int index)
	{
		boolean wasAdded = false;
		if(entries.contains(aEntry))
		{
			if(index < 0 ) { index = 0; }
			if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
			entries.remove(aEntry);
			entries.add(index, aEntry);
			wasAdded = true;
		} 
		else 
		{
			wasAdded = addEntryAt(aEntry, index);
		}
		return wasAdded;
	}

}