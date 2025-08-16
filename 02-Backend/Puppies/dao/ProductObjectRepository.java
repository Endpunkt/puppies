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

import de.irahi.welp.dto.PuppyDTO;
import de.irahi.welp.entity.ProductObject;

@CrossOrigin("http://localhost:4200")
public interface ProductObjectRepository extends JpaRepository<ProductObject, Long> {
	
	@Query("SELECT new de.irahi.welp.dto.PuppyDTO(p.id, p.name, d.color, p.weight, d.height, p.image,d.characteristic, d.rase, p.price) " +
	           "FROM ProductObject p " +
	           "JOIN p.details d " +
	           "WHERE p.category.id = 1")
	 Optional<PuppyDTO> findProductObjectWithDetailsIdOne();

	
}
