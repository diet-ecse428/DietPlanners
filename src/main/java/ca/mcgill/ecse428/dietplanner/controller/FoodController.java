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
import ca.mcgill.ecse428.dietplanner.dto.FoodDTO;
import ca.mcgill.ecse428.dietplanner.model.Entry;
import ca.mcgill.ecse428.dietplanner.model.Food;
import ca.mcgill.ecse428.dietplanner.repository.FoodRepository;

@CrossOrigin
@RestController
@RequestMapping("api/food")
public class FoodController {

	@Autowired
	private FoodRepository repository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public FoodDTO createFood(@RequestParam("entryid") int entry_id, @RequestParam("calories") int calories, @RequestParam("serving") double serving,
								@RequestParam("mealtype") String meal_type) {
		Food result = repository.createFood(entry_id, calories, serving, meal_type);
		if(result!=null) {
			String correctMealType = result.getMealType().toString();
			FoodDTO food = new FoodDTO((EntryDTO.MealType.valueOf(correctMealType)), result.getCalories(), result.getServing(), result.getId(), result.getEntryId());
			return food;
		}else {
			return null;
		}
	}//works
	
	@GetMapping("/get/{foodId}")
	public FoodDTO getFood(@PathVariable("foodId") int foodId) {
		Food result = repository.getFood(foodId);
		if(result!=null) {
			String correctMealType = result.getMealType().toString();
			FoodDTO food = new FoodDTO((EntryDTO.MealType.valueOf(correctMealType)), result.getCalories(), result.getServing(), result.getId(), result.getEntryId());
			return food;
		}else {
			return null;
		}
	}//works
	
	@RequestMapping(value = "/remove/{foodId}/")
	@ResponseBody
	public boolean removeFood(@PathVariable("foodId") int foodId) {
		boolean result = repository.removeFood(foodId);
		if(result) {
			return true;
		}else {
			return false;
		}
	}//works
	
	@RequestMapping(value = "/getAllFoods", method = RequestMethod.GET)
	@ResponseBody
	public List<FoodDTO> getAllFoods(){
		List<Food> allFoods = repository.getAllFoods();
		List<FoodDTO> foodDTOs = new ArrayList<FoodDTO>();
		for(Food food : allFoods) {
			String correctMealType = food.getMealType().toString();
			foodDTOs.add(new FoodDTO(EntryDTO.MealType.valueOf(correctMealType), food.getCalories(), food.getServing(), food.getId(), food.getEntryId()));
		}
		return foodDTOs;
	}
}
