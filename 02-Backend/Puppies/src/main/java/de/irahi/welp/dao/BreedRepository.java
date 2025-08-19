package de.irahi.welp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.irahi.welp.entity.Breed;

public interface BreedRepository extends JpaRepository<Breed, Long> {
	
	Optional<Breed> findByRace(String race);

}
