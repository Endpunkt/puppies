package de.irahi.welp.service;

import java.util.Arrays;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import de.irahi.welp.dao.BreadRepository;
import de.irahi.welp.dao.CategoriesRepository;
import de.irahi.welp.dao.ProductObjectRepository;
import de.irahi.welp.dto.FoodDTO;
import de.irahi.welp.dto.PuppyDTO;
import de.irahi.welp.entity.Bread;
import de.irahi.welp.entity.Category;
import de.irahi.welp.entity.ProductObject;
import de.irahi.welp.entity.ProductObject.FoodType;
import de.irahi.welp.props.AnimalCharacter;


@Service
public class PuppyService {
	
	@Autowired
	private ModelMapper modelM;
	
	@Autowired
	private ProductObjectRepository prObjectRepository;
	@Autowired
	private BreadRepository breadRepository;
	
	@Autowired
	private CategoriesRepository categoryRepository;
	
	// G E T    PUPPY-PAGE
	public Page<PuppyDTO> getAllPuppies(Pageable pageable){
		return prObjectRepository.findProductObjectWithCategoryIdOne(pageable);
	}
	
	// G E T    PRODUCT_OBJECT-LIST
	public List<ProductObject> getAllPuppyProducts(){
		return prObjectRepository.findByCategoryId(1L);
	}
	
	// G E T    FOOD-PAGE
	public Page<FoodDTO> getAllFood(Pageable pageable){
		return prObjectRepository.findProductObjectWithCategoryIdTwo(pageable);
	}
	
	// S E A R C H   PUPPY-PAGE
	public Page<PuppyDTO> searchPuppyDTO(String theKeyword, Pageable pageable){
		return prObjectRepository.findPuppyByBreadRace(theKeyword, pageable);
	}
	
	// S E A R C H   FOOD
	public Page<FoodDTO> searchFoodDTO(String theKeyword, Pageable pageable){
		return prObjectRepository.findFoodByObjectName(theKeyword, pageable);
	}
	
	//S I N G L E   PUPPY
	public PuppyDTO getSinglePuppyDTO(Long puppyId) {
		return prObjectRepository.findPuppyDTOById(puppyId);
	}
	
	//S I N G L E   FOOD
	public FoodDTO getSingleFoodDTO(Long foodId) {
		return prObjectRepository.findFoodDTOById(foodId);
	}
	
	public int dtoLength(String name) {
		return prObjectRepository.dtoProductCount(name);
	}

	public void save(PuppyDTO puppyDTO) {
		System.out.println("---------------------------------Puppy Creaiting Profile------------------------------------" );
		puppyDTO.getCharacteristic().forEach(an -> System.out.println("characteristic: " + an.getCharacterName()));
		System.out.println(puppyDTO.getCharacteristic().stream().map(an -> " " + an.getCharacterName()));
	    System.out.println(puppyDTO.getPuppyId() + ", " + puppyDTO.getColor() + ", " + puppyDTO.getRace().getRace());
	    System.out.println("Characteristic Type: " + puppyDTO.getCharacteristic().getClass());
	    ProductObject prOb = new ProductObject();

	    
	    prOb.setCategory(categoryRepository.getReferenceById(1L));
	    prOb.setPuppyId(puppyDTO.getPuppyId());
	    prOb.setFoodId(0L);
	    prOb.setName(puppyDTO.getName());
	    prOb.setWeight(puppyDTO.getWeight());
	    prOb.setHeight(puppyDTO.getHeight());
	    prOb.setPrice(puppyDTO.getPrice());
	    prOb.setImage(puppyDTO.getImage());
	    prOb.setCharacteristic(puppyDTO.getCharacteristic());
	    System.out.print("prOb Characteristic: ");
	     prOb.getCharacteristic().forEach(ac -> System.out.println(ac.getCharacterName()));
	    //puppyDTO.getCharacteristic().forEach(ac -> prOb.setSingleCharacter(ac));
	    prOb.setObjectName("");
	    prOb.setFoodType(ProductObject.FoodType.NONE);
	    prOb.setColor(puppyDTO.getColor());
	    prOb.setCount(1);

	    Bread bread = new Bread();
	    bread.setProductObject(prOb);  // Setzt die Beziehung von Bread zu ProductObject
	    bread.setRace(puppyDTO.getRace().getRace());

	    prOb.setRace(bread);  // Setzt die Beziehung von ProductObject zu Bread

	    // Speichere erst das ProductObject mit der verknüpften Bread-Instanz
	    prObjectRepository.save(prOb);

	    System.out.println("Bread -----" + puppyDTO.getRace() + ", ProductObject: " + bread.getProductObject().getPuppyId());

	    // Optional, da CascadeType.ALL das Bread automatisch mit ProductObject speichern sollte
	    breadRepository.save(bread);  

	    System.out.println(puppyDTO.getCharacteristic() + ", \n" + puppyDTO.getColor());
	    puppyDTO.getCharacteristic().forEach(enum_ -> System.out.println("enum: " + enum_.getClass()));
	    
	    System.out.println("\t\t\tprOb.getPuppyId() : " + prOb.getPuppyId()
	            + ", \n\t\t\tprOb.getCharacteristic(): " + prOb.getCharacteristic()
	            + ", \n\t\t\tprOb.getColor(): " + prOb.getColor()
	            + "");

	    // Speichern abschließen
	    prObjectRepository.save(prOb);
	    System.out.println("Das Objekt: " );
	}
}

