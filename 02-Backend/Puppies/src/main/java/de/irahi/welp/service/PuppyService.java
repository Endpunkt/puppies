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
	
	private Breed findOrCreateBreed(String raceName) {
	    Optional<Breed> existingBreed = breedRepository.findByRace(raceName);
	    
	    if (existingBreed.isPresent()) {
	        return existingBreed.get();
	    } else {
	        Breed newBreed = new Breed();
	        newBreed.setRace(raceName);
	        return breedRepository.save(newBreed); // Speichert die neue Rasse und gibt sie zurück
	    }
	}
	
	@Transactional
	public void save(PuppyDTO puppyDTO) {
		Breed breed = findOrCreateBreed(puppyDTO.getRace().getRace().trim().toUpperCase());
		Category cat = categoryRepository.getReferenceById(1L);
	    Puppy pup = new Puppy();
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
	    
	    pup.setRace(breed);  // Setzt die Beziehung von ProductObject zu Breed
	    puppyRepository.save(pup);
  
	}
}

