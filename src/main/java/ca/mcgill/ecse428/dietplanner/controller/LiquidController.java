package ca.mcgill.ecse428.dietplanner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.dietplanner.dto.EntryDTO;
import ca.mcgill.ecse428.dietplanner.dto.LiquidDTO;
import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Liquid;
import ca.mcgill.ecse428.dietplanner.repository.LiquidRepository;
import ca.mcgill.ecse428.dietplanner.repository.InvalidInputException;

@CrossOrigin
@RestController
@RequestMapping("api/liquid")
public class LiquidController {

	@Autowired
	private LiquidRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public LiquidDTO createLiquid(@RequestParam("entryid") int entry_id, @RequestParam("calories") int calories, @RequestParam("volume") double volume) throws InvalidInputException {
		Liquid result = repository.createLiquid(entry_id, calories, volume);
		if(result!=null) {
			LiquidDTO liquid = new LiquidDTO(result.getCalories(), result.getVolume(), result.getId(), result.getEntryId());
			return liquid;
		}else {
			return null;
		}
	}//works
	
	@GetMapping("/get/{liquidId}")
	public LiquidDTO getLiquid(@PathVariable("liquidId") int liquidId) {
		Liquid result = repository.getLiquid(liquidId);
		if(result!=null) {
			LiquidDTO liquid = new LiquidDTO(result.getCalories(), result.getVolume(), result.getId(), result.getEntryId());
			return liquid;
		}else {
			return null;
		}
	}//works
	
	@RequestMapping(value = "/getAllLiquids/{entryId}", method = RequestMethod.GET)
	@ResponseBody
	public List<LiquidDTO> getAllLiquids(@PathVariable("entryId") int entryId){
		List<Liquid> allLiquids = repository.getAllLiquids();
		List<LiquidDTO> liquidDTOs = new ArrayList<LiquidDTO>();
		for(Liquid liquid : allLiquids) {
			if(liquid.getEntryId()==entryId){
			liquidDTOs.add(new LiquidDTO(liquid.getCalories(), liquid.getVolume(), liquid.getId(), liquid.getEntryId()));
			}
	}
		return liquidDTOs;
	}
	
	@RequestMapping(value = "/remove/{liquidId}/")
	@ResponseBody
	public boolean removeLiquid(@PathVariable("liquidId") int liquidId) {
		boolean result = repository.removeLiquid(liquidId);
		if(result) {
			return true;
		}else {
			return false;
		}
	}
	
}
