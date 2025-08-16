package de.irahi.welp.dto;

import java.math.BigDecimal;

import de.irahi.welp.entity.Food.FoodType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
public class FoodDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private FoodType foodType;
	private String image;
	private int count;
	private BigDecimal price;
	
	public FoodDTO(long id, String name, FoodType foodType, String image, int count, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.foodType = foodType;
		this.image = image;
		this.count = count;
		this.price = price;
	}
	

}
