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
		System.out.println("---------------------------------Puppy Creaiting Profile------------------------------------" );
		puppyDTO.getCharacteristic().forEach(an -> System.out.println("characteristic: " + an.getCharacterName()));
		System.out.println(puppyDTO.getCharacteristic().stream().map(an -> " " + an.getCharacterName()));
	    System.out.println(puppyDTO.getId() + ", " + puppyDTO.getColor() + ", " + puppyDTO.getRace().getRace());
	    System.out.println("Characteristic Type: " + puppyDTO.getCharacteristic().getClass());
	    System.out.println(puppyDTO.getName());
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
	        System.out.println("Rasse bereits vorhanden: " + puppyDTO.getRace().getRace());
	    } else {
	        // Die Rasse existiert noch nicht, erstelle ein neues Objekt
	        breed = new Breed();
	        breed.setRace(puppyDTO.getRace().getRace());
	        System.out.println("Neue Rasse erstellt: " + breed.getRace());
	    }
	     // Setzt die Beziehung von Breed zu ProductObject
	    breed.setRace(puppyDTO.getRace().getRace());

	    pup.setRace(breed);  // Setzt die Beziehung von ProductObject zu Breed
//	    System.out.println("###################");
//	    System.out.println(pup.toString());
	    // Speichere erst das ProductObject mit der verknüpften Breed-Instanz
	    puppyRepository.save(pup);

	    System.out.println("Breed -----" + puppyDTO.getRace() + ", ProductObject: " + breed.getPuppies());

	    // Optional, da CascadeType.ALL das Breed automatisch mit ProductObject speichern sollte
	    breedRepository.save(breed);  

	    System.out.println(puppyDTO.getCharacteristic() + ", \n" + puppyDTO.getColor());
	    puppyDTO.getCharacteristic().forEach(enum_ -> System.out.println("enum: " + enum_.getClass()));
	    
	   
	    
	    System.out.println("\t\t\tpup.getId() : " + pup.getId()
	            + ", \n\t\t\tpup.getCharacteristic(): " + pup.getCharacteristic()
	            + ", \n\t\t\tpup.getColor(): " + pup.getColor()
	            + "");
	  

	    // Speichern abschließen
	    System.out.println("Das Objekt: " );
	}
}

