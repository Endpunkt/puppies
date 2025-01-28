package de.irahi.welp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.irahi.welp.entity.ProductObject;

public interface InsertRepository extends JpaRepository<ProductObject, Long> {
	

}
