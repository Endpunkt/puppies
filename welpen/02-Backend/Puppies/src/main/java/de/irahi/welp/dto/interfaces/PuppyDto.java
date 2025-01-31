package de.irahi.welp.dto.interfaces;

import java.math.BigDecimal;
import java.util.Set;

import de.irahi.welp.entity.Bread;
import de.irahi.welp.props.AnimalCharacter;

public interface PuppyDto {
	
	Long getId();
	String getName();
	String getColor();
	BigDecimal getWeight();
	BigDecimal getHeight();
	String getImage();
	Set<AnimalCharacter> getAnimalCharacter();
	Bread getBread();
	BigDecimal getPrice();
}
