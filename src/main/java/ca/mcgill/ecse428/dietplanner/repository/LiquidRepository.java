package ca.mcgill.ecse428.dietplanner.repository;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Liquid;

@Repository
public class LiquidRepository {
	
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public Liquid createLiquid(int entry_id, int calories, double volume) throws InvalidInputException {
		Entry entry = em.find(Entry.class, entry_id);
		if(entry==null) throw new InvalidInputException("Error: Entry not found.\n");
		if (volume < 0.0) throw new InvalidInputException("Error: volume must be positive.\n");
		if (calories < 0) throw new InvalidInputException("Error: calories must be positive.\n");
		Liquid liquid = new Liquid();
		liquid.setCalories(calories);
		liquid.setEntryId(entry_id);
		liquid.setVolume(volume);
		
		entry.setRemaingCal(entry.getRemaingCal() - calories);
		
		Set<Liquid> liquids = entry.getLiquids();
		liquids.add(liquid);
		entry.setLiquids(liquids);
		
		em.persist(entry);
		
		em.persist(liquid);
		return liquid;
	}

	@Transactional
	public Liquid getLiquid(int liquidId) {
		Liquid liquid = em.find(Liquid.class, liquidId);
		return liquid;
	}
	
	@Transactional
	public List<Liquid> getAllLiquids() {
		TypedQuery<Liquid> query = em.createQuery("select e from Liquid e", Liquid.class);
		List<Liquid> liquids = query.getResultList();
		return liquids;
	}
	
	@Transactional
	public boolean removeLiquid(int id) {
		int rowsDeleted = em.createQuery("delete from Liquid where id = '" + id + "'").executeUpdate();
		if(rowsDeleted == 1) {
			return true;
		}
		return false;
	}
}
