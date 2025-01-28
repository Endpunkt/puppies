package de.irahi.welp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import de.irahi.welp.dto.FoodDTO;
import de.irahi.welp.dto.PuppyDTO;
import de.irahi.welp.dto.interfaces.PuppyDto;
import de.irahi.welp.entity.ProductObject;





@CrossOrigin("http://localhost:4200")
public interface ProductObjectRepository extends JpaRepository<ProductObject, Long> {
	
	//J U S T    P U P P I E S
	@Query("SELECT new de.irahi.welp.dto.PuppyDTO( p.id, p.puppyId, p.name, p.image, p.height, p.weight, p.color, p.characteristic, p.race, p.price) " +
	           "FROM ProductObject p " + 
	           "WHERE p.category.id = 1")
	Page<PuppyDTO> findProductObjectWithCategoryIdOne(Pageable pageable);
	
	
	
	//G E T    F O O D - P A G E
	@Query("SELECT new de.irahi.welp.dto.FoodDTO( p.id, p.foodId, p.name, p.objectName, p.foodType, p.image,  p.count, p.price) " +
	           "FROM ProductObject p " + 
	           "WHERE p.category.id = 2")
	Page<FoodDTO> findProductObjectWithCategoryIdTwo(Pageable pageable);
	
	
	
	//NO USE
	List<ProductObject> findByCategoryId(Long id);
	
	
	
	//G E T      P U P P Y - L I S T
	@Query("SELECT  p.id, p.name, p.race " +
	           "FROM ProductObject p " + 
	           "WHERE p.category.id = 1")
	List<PuppyDto> findProductWithCategoryIdOne();
	
	
	
	//S E A R C H     P U P P I E S - P A G E  
	@Query("SELECT new de.irahi.welp.dto.PuppyDTO(p.id, p.puppyId, p.name, p.image, p.height, p.weight, p.color, p.characteristic, p.race, p.price)" +
			"FROM ProductObject p " + 
			"WHERE LOWER(p.race.race) like LOWER(CONCAT('%',:race,'%'))")
	Page<PuppyDTO> findPuppyByBreadRace(@Param("race") String race, Pageable pageable);
	
	
	
	
	//S E A R C H     F O O D - P A G E
	@Query("SELECT new de.irahi.welp.dto.FoodDTO(p.id, p.foodId, p.name, p.objectName, p.foodType, p.image,  p.count, p.price)" +
			"FROM ProductObject p " + 
			"WHERE LOWER(p.objectName) like LOWER(CONCAT('%', :name, '%'))")
	Page<FoodDTO> findFoodByObjectName(@Param("name") String name, Pageable pageable);
	
	
	
	
	
	//S I N G L E    P U P P Y
	@Query("SELECT new de.irahi.welp.dto.PuppyDTO(p.id, p.puppyId, p.name, p.image, p.height, p.weight, p.color, p.characteristic, p.race, p.price)" +
			"FROM ProductObject p " +
			"WHERE p.puppyId = :id")
	PuppyDTO findPuppyDTOById(@Param("id") Long id);
	
	
	
	
	//S I N G L E    F O O D
	@Query("SELECT new de.irahi.welp.dto.FoodDTO(p.id, p.foodId, p.name, p.objectName, p.foodType, p.image,  p.count, p.price)" +
			"FROM ProductObject p " +
			"WHERE p.foodId = :id")
	FoodDTO findFoodDTOById(@Param("id") Long id);
	
	@Query("Select COUNT(p) FROM ProductObject p WHERE p.name = :name")
	int dtoProductCount(@Param("name") String name);
	

	
}
