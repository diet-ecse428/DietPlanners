package ca.mcgill.ecse428.dietplanner.controller;

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
import ca.mcgill.ecse428.dietplanner.model.Liquid;
import ca.mcgill.ecse428.dietplanner.repository.LiquidRepository;

@CrossOrigin
@RestController
@RequestMapping("api/liquid")
public class LiquidController {

	@Autowired
	private LiquidRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public LiquidDTO createLiquid(@RequestParam("entryid") int entry_id, @RequestParam("calories") int calories, @RequestParam("volume") double volume) {
		Liquid result = repository.createLiquid(entry_id, calories, volume);
		if(result!=null) {
			LiquidDTO liquid = new LiquidDTO(result.getCalories(), result.getVolume(), result.getId(), result.getEntryId());
			return liquid;
		}else {
			return null;
		}
	}
	
	@GetMapping("/get/{liquidId}")
	public LiquidDTO getLiquid(@PathVariable("liquidId") int liquidId) {
		Liquid result = repository.getLiquid(liquidId);
		if(result!=null) {
			LiquidDTO liquid = new LiquidDTO(result.getCalories(), result.getVolume(), result.getId(), result.getEntryId());
			return liquid;
		}else {
			return null;
		}
	}
	
}
