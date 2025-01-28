package de.irahi.welp.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@Access(AccessType.FIELD)
public class Bread {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="race")
	private String race;
	
	@JsonIgnore
	@OneToOne()
	@JoinColumn(name="product_object_fk")
	private ProductObject productObject;
	
	@Override
	public int hashCode() {
	     return Objects.hash(id, race);  // Hier nur id und andere stabile Eigenschaften verwenden
	}


}
