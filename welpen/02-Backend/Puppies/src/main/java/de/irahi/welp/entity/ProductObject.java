package de.irahi.welp.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import de.irahi.welp.component.AnimalCharacterConverter;

import de.irahi.welp.props.AnimalCharacter;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
@Access(AccessType.FIELD)
public class ProductObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(
		    name = "product_seq_gen", 
		    sequenceName = "PRODUCT_SEQUENCE", 
		    allocationSize = 1
		)
	private Long id;
	
	@Column(name="foodId")
	private Long foodId;
	
	@Column(name="puppyId")
	private Long puppyId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="weight", length=30)
	private BigDecimal weight;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="image")
	private String image;
	

	@Column(name="object_name")
	private String objectName;
	
	@Enumerated(EnumType.STRING)
	private FoodType foodType;
	
	
	
	@Column(name="color")
	private String color;
	
	private int count;
	
	@OneToOne(mappedBy="productObject", cascade=CascadeType.ALL, fetch= FetchType.EAGER)
	private Bread race;
	
	
	
	//@Enumerated(EnumType.STRING)
	@Convert(converter = AnimalCharacterConverter.class)
 	@Column(name = "characteristic", columnDefinition = "VARCHAR(600)")
   
    private Set<AnimalCharacter> characteristic;

	@Column(name="height", length=50)
	private BigDecimal height;

	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	private Category category;
	

	@Override
	public int hashCode() {
	     return Objects.hash(id, puppyId, name, weight, price, image, objectName, foodType, characteristic, color, count, race, height, category);  // Hier nur id und andere stabile Eigenschaften verwenden
	}
	
	 
	public enum FoodType{	MEAT, LEFTOVER_FOOD, CANNED, PRODUCTION, VITAMINS, BONE, DRINKS, LIQUID_FOOD, NONE	}
	
	public void setSingleCharacter(AnimalCharacter ac) {
		this.characteristic.add(ac);
	}
	
	
	

}
