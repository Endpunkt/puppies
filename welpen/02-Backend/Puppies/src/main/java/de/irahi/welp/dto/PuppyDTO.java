package de.irahi.welp.dto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import de.irahi.welp.entity.Bread;
import de.irahi.welp.props.AnimalCharacter;
import lombok.Data;

@Data
public class PuppyDTO {
	private Long id;
	private Long puppyId;
	private String name;
	private String color;
	private BigDecimal weight;
	private BigDecimal height;

	
	private String image;
	private Set<AnimalCharacter> characteristic;
	private Bread race;
	private BigDecimal price;
	
	
	public PuppyDTO() {}
	public PuppyDTO(Long id, long puppyId, String name, String image, BigDecimal height, BigDecimal weight, String color, Set<AnimalCharacter> characteristic, Bread race, BigDecimal price) {
		this.id = id;
		this.puppyId = puppyId;
		this.name = name;
		this.image = image;
		this.height = height;
		this.weight = weight;
		this.color = color;
		this.characteristic = characteristic;
		this.race = race;
		this.price = price;
	}
	

}
