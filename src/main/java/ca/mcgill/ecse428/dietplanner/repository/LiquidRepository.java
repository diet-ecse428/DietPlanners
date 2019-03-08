package ca.mcgill.ecse428.dietplanner.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Liquid;

@Repository
public class LiquidRepository {
	
	@PersistenceContext
	public EntityManager em;

	@Transactional
	public Liquid createLiquid(int entry_id, int calories, double volume) {
		Entry entry = em.find(Entry.class, entry_id);
		if(entry==null || calories<0 || volume<0) {
			return null;
		}
		Liquid liquid = new Liquid();
		liquid.setCalories(calories);
		liquid.setEntryId(entry_id);
		liquid.setVolume(volume);
		
		em.persist(liquid);
		return liquid;
	}

	@Transactional
	public Liquid getLiquid(int liquidId) {
		Liquid liquid = em.find(Liquid.class, liquidId);
		return liquid;
	}
}
