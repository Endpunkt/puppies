package de.irahi.welp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.irahi.welp.component.ProductMapper;
import de.irahi.welp.dto.FoodDTO;
import de.irahi.welp.dto.PuppyDTO;
import de.irahi.welp.service.FoodService;
import de.irahi.welp.service.PuppyService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/puppyFood")
public class ProductController {

	
	@Autowired
	private PuppyService puppyService;
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	//-----------------------------------------------------------------------------------P A G E------------------------------------------
	// G E T    PUPPY-PAGE        ( page, size )
	@GetMapping("dto/Puppies")
	public Page<PuppyDTO> getPuppies(
			@RequestParam(defaultValue = "0") int page, 
	        @RequestParam(defaultValue = "8") int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<PuppyDTO> pup =  puppyService.getAllPuppies(pageable);
		System.out.println("PuppyDTO Liste: ---------------------" + pup.getSize() + "-----------------------");
		return pup;
	}
	
	
	// G E T    FOOD-PAGE         ( page, size )
	@GetMapping("dto/Food")
	public Page<FoodDTO> getFood(
	        @RequestParam(defaultValue = "0") int page, 
	        @RequestParam(defaultValue = "8") int size){
	    Pageable pageable = PageRequest.of(page, size);
		return foodService.getAllFood(pageable);
	}
	
	//--------------------------------------------------------------------------------------| S E A R C H |-------------------------
	
	//S E A R C H     PUPPY-PAGE      ( keyword, page, size )
	@GetMapping("dto/search/PuppyDTO/{theKeyWord}")
	public Page<PuppyDTO> getSearchPuppyDTO(
			@PathVariable("theKeyWord") String theKeyWord,
	        @RequestParam(defaultValue = "0") int page, 
	        @RequestParam(defaultValue = "8") int size){
		Pageable pageable = PageRequest.of(page, size);
		if(theKeyWord == "") return puppyService.getAllPuppies(pageable);
	    Page<PuppyDTO> result = puppyService.searchPuppyDTO(theKeyWord, pageable);

	    return result;
		//return puppyService.searchPuppyDTO(race);
	}
	
	//S E A R C H     FOOD-PAGE     ( keyword, page, size )
	@GetMapping("dto/search/FoodDTO/{theKeyWord}")
	public Page<FoodDTO> getSearchFoodDTO(
			@PathVariable("theKeyWord") String theKeyWord,
			@RequestParam(defaultValue = "0") int page, 
	        @RequestParam(defaultValue = "8") int size){
		Pageable pageable = PageRequest.of(page, size);
		if(theKeyWord == "") return foodService.getAllFood(pageable);
		return foodService.searchFoodDTO(theKeyWord, pageable);
	}
	
	//------------------------------------------------------------------------------------S I N G L E----------------------------
	
	@GetMapping("dto/singlePuppyDTO/{puppyId}")
	public PuppyDTO getSinglePuppyObject(@PathVariable("puppyId") long puppyId) {
		PuppyDTO dto = puppyService.getSinglePuppyDTO(puppyId);
		return dto;
	}
	
	@GetMapping("dto/singleFoodDTO/{foodId}")
	public FoodDTO getSingleFoodObject(@PathVariable("foodId") Long foodId) {
		FoodDTO dto = foodService.getSingleFoodDTO(foodId);
		return dto;
	}
	
	//-----------------------------------------------------------------------------------H 2  D a t a b a s e----------------------------
	
	@GetMapping("/closeH2")
	public void closeH2() {
	    
	        try (Connection conn = DriverManager.getConnection("jdbc:h2:file:./src/main/resources/dogs;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;CACHE_SIZE=1", "user", "pass")) {
	            conn.createStatement().execute("SHUTDOWN");
	            System.out.println("H2-Datenbank erfolgreich heruntergefahren.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	}

}
