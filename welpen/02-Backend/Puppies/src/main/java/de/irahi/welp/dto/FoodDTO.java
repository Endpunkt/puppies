package de.irahi.welp.dto;

import java.math.BigDecimal;

import de.irahi.welp.entity.ProductObject.FoodType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
public class FoodDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long foodId;
	private long id;
	private String name;
	private String objectName;
	private FoodType foodType;
	private String image;
	private int count;
	private BigDecimal price;
	
	public FoodDTO(long id, long foodId, String name, String objectName, FoodType foodType, String image, int count, BigDecimal price) {
		this.id = id;
		this.foodId = foodId;
		this.name = name;
		this.objectName = objectName;
		this.foodType = foodType;
		this.image = image;
		this.count = count;
		this.price = price;
	}
	

}
