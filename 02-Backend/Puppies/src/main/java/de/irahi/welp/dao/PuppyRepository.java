package de.irahi.welp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;



import de.irahi.welp.dto.PuppyDTO;

import de.irahi.welp.entity.Puppy;





@CrossOrigin("http://localhost:4200")
public interface PuppyRepository extends JpaRepository<Puppy, Long> {
	
	//J U S T    P U P P I E S
	@Query("SELECT new de.irahi.welp.dto.PuppyDTO( p.id, p.name, p.image, p.height, p.weight, p.color, p.characteristic, p.race, p.price) FROM Puppy p ")
	Page<PuppyDTO> findPuppyObject(Pageable pageable);
	
	//NO USE
	//List<Puppy> findByCategoryId(Long id);
	
	//G E T      P U P P Y - L I S T
	@Query("SELECT  p.id, p.name, p.race FROM Puppy p ")
	List<PuppyDTO> findPuppyObjectList();
	
	//S E A R C H     P U P P I E S - P A G E  
	@Query("SELECT new de.irahi.welp.dto.PuppyDTO(p.id, p.name, p.image, p.height, p.weight, p.color, p.characteristic, p.race, p.price)" +
			"FROM Puppy p WHERE LOWER(p.race.race) like LOWER(CONCAT('%',:race,'%'))")
	Page<PuppyDTO> findPuppyByBreadRace(@Param("race") String race, Pageable pageable);
	
	//S I N G L E    P U P P Y
	@Query("SELECT new de.irahi.welp.dto.PuppyDTO(p.id, p.name, p.image, p.height, p.weight, p.color, p.characteristic, p.race, p.price) FROM Puppy p WHERE p.id = :id")
	PuppyDTO findPuppyDTOById(@Param("id") Long id);
	
	@Query("Select COUNT(p) FROM Puppy p WHERE p.name = :name")
	int puppyCount(@Param("name") String name);

	

	
}
