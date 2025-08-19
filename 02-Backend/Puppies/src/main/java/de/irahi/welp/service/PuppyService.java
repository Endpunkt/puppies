package de.irahi.welp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.irahi.welp.dao.BreedRepository;
import de.irahi.welp.dao.CategoriesRepository;
import de.irahi.welp.dao.PuppyRepository;
import de.irahi.welp.dto.FoodDTO;
import de.irahi.welp.dto.PuppyDTO;
import de.irahi.welp.entity.Breed;
import de.irahi.welp.entity.Category;

import de.irahi.welp.entity.Puppy;
import de.irahi.welp.props.AnimalCharacter;
import jakarta.transaction.Transactional;


@Service
public class PuppyService {
	
	@Autowired
	private ModelMapper modelM;
	
	@Autowired
	private PuppyRepository puppyRepository;
	@Autowired
	private BreedRepository breedRepository;
	
	@Autowired
	private CategoriesRepository categoryRepository;
	
	// G E T    PUPPY-PAGE
	public Page<PuppyDTO> getAllPuppies(Pageable pageable){
		return puppyRepository.findPuppyObject(pageable);
	}
	
	// G E T    PRODUCT_OBJECT-LIST
	public List<PuppyDTO> getAllPuppyProducts(){
		return puppyRepository.findPuppyObjectList();
	}
	
	// S E A R C H   PUPPY-PAGE
	public Page<PuppyDTO> searchPuppyDTO(String theKeyword, Pageable pageable){
		return puppyRepository.findPuppyByBreadRace(theKeyword, pageable);
	}
	
	//S I N G L E   PUPPY
	public PuppyDTO getSinglePuppyDTO(Long puppyId) {
		return puppyRepository.findPuppyDTOById(puppyId);
	}
	
	public int puppyDtoLength(String name) {
		return puppyRepository.puppyCount(name);
	}
	
	@Transactional
	public void save(PuppyDTO puppyDTO) {
	    Puppy pup = new Puppy();
	    
	    Category cat = categoryRepository.getReferenceById(1L);
	    pup.setCategory(cat);
	    pup.setName(puppyDTO.getName());
	    pup.setWeight(puppyDTO.getWeight());
	    pup.setHeight(puppyDTO.getHeight());
	    pup.setPrice(puppyDTO.getPrice());
	    pup.setImage(puppyDTO.getImage());
	    pup.setCharacteristic(puppyDTO.getCharacteristic());
	    System.out.print("pup Characteristic: ");
	    pup.getCharacteristic().forEach(puppy -> System.out.println(puppy.getCharacterName()));
	    //puppyDTO.getCharacteristic().forEach(ac -> pup.setSingleCharacter(ac));
	    pup.setColor(puppyDTO.getColor());
	    
	    Optional<Breed> maybeBreed = breedRepository.findByRace(puppyDTO.getRace().getRace());
	    Breed breed;
	    if (maybeBreed.isPresent()) {
	        // Die Rasse existiert bereits, verwende das vorhandene Objekt
	        breed = maybeBreed.get();
	    } else {
	        // Die Rasse existiert noch nicht, erstelle ein neues Objekt
	        breed = new Breed();
	        breed.setRace(puppyDTO.getRace().getRace());
	    }
	     // Setzt die Beziehung von Breed zu ProductObject
	    breed.setRace(puppyDTO.getRace().getRace());
	    pup.setRace(breed);  // Setzt die Beziehung von ProductObject zu Breed
	    puppyRepository.save(pup);
	    breedRepository.save(breed);  

	    
	   
	    
	}
}

