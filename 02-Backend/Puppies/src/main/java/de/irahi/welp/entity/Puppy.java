package de.irahi.welp.entity;

import java.math.BigDecimal;
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
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@Access(AccessType.FIELD)
public class Puppy extends Product{
	
	@Column(name="weight")
	private BigDecimal weight;
	@Column(name="color")
	private String color;
	@Column(name="height")
	private BigDecimal height;
	
	
	@Column(name="characteristic", columnDefinition = "VARCHAR")
	@Convert(converter= AnimalCharacterConverter.class)
	private Set<AnimalCharacter> characteristic;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name="race_id", referencedColumnName = "id")
	private Breed race;
	
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), weight, color, height, characteristic, race);
	}
	

}
