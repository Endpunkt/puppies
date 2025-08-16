package de.irahi.welp.entity;

import java.math.BigDecimal;
import java.util.Objects;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
@Access(AccessType.FIELD)
public class Food extends Product{
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="food_type" )
	private FoodType foodType;
	
	@Column(name="count")
	private int count;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
	
	
	public enum FoodType {
	    MEAT("Fleisch"),
	    LEFTOVER_FOOD("Essensrest"),
	    CANNED("Dosen Futter"),
	    PRODUCTION("Fabrikproduktion"),
	    VITAMINS("Vitaminen"),
	    BONE("Knochen"),
	    DRINKS("Getränke"),
	    LIQUID_FOOD("flüssiges Essen");
		
		private final String characterName;
		FoodType(String name) {
			this.characterName = name;
		}
		
		public String getCharacterName() {	return this.characterName;	}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash( foodType, count);
	}
}
