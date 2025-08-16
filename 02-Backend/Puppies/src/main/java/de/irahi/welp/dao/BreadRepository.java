package de.irahi.welp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import de.irahi.welp.entity.Breed;

public interface BreadRepository extends JpaRepository<Breed, Long> {

}
