package de.irahi.welp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.irahi.welp.dao.FoodRepository;
import de.irahi.welp.dto.FoodDTO;
import de.irahi.welp.entity.Food;
import jakarta.transaction.Transactional;

@Service
public class FoodService {
	
	@Autowired
	private FoodRepository foodRepository;
	
	// G E T    FOOD-PAGE
	public Page<FoodDTO> getAllFood(Pageable pageable){
		return foodRepository.findFoodObject(pageable);
	}
	
	// S E A R C H   FOOD
	public Page<FoodDTO> searchFoodDTO(String theKeyword, Pageable pageable){
		return foodRepository.findFoodByObjectName(theKeyword, pageable);
	}
	
	//S I N G L E   FOOD
	public FoodDTO getSingleFoodDTO(Long foodId) {
		return foodRepository.findFoodDTOById(foodId);
	}
	
	public int foodDtoLength(String name) {
		return foodRepository.foodCount(name);
	}
	
	@Transactional
	public void save(FoodDTO foodDTO) {
		Food food = new Food();
		
		food.setName(foodDTO.getName());
		food.setImage(foodDTO.getImage());
		food.setPrice(foodDTO.getPrice());
		food.setFoodType(foodDTO.getFoodType());
		
		foodRepository.save(food);
		
	}
	
}
