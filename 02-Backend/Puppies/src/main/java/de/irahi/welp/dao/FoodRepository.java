package de.irahi.welp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import de.irahi.welp.dto.FoodDTO;
import de.irahi.welp.entity.Food;


@CrossOrigin("http://localhost:4200")
public interface FoodRepository extends JpaRepository<Food, Long> {
	
	//G E T    F O O D - P A G E
	@Query("SELECT new de.irahi.welp.dto.FoodDTO(p.id, p.name, p.foodType, p.image, p.count, p.price) FROM Food p")
	Page<FoodDTO> findFoodObject(Pageable pageable);
	
	//S E A R C H     F O O D - P A G E
	@Query("SELECT new de.irahi.welp.dto.FoodDTO(p.id, p.name, p.foodType, p.image,  p.count, p.price)" +
			"FROM Food p WHERE LOWER(p.name) like LOWER(CONCAT('%', :name, '%'))")
	Page<FoodDTO> findFoodByObjectName(@Param("name") String name, Pageable pageable);
	
	//S I N G L E    F O O D
	@Query("SELECT new de.irahi.welp.dto.FoodDTO(p.id, p.name, p.foodType, p.image,  p.count, p.price) FROM Food p WHERE p.id = :id")
	FoodDTO findFoodDTOById(@Param("id") Long id);
	
	
	@Query("Select COUNT(p) FROM Food p WHERE p.name = :name")
	int foodCount(@Param("name") String name);

}
