package de.irahi.welp.entity;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
@Access(AccessType.FIELD)
public class Breed {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="race")
	private String race;
	
	@JsonIgnore
	@OneToMany(mappedBy = "race", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Puppy> puppies;
	
	@Override
	public int hashCode() {
	     return Objects.hash(id, race);  // Hier nur id und andere stabile Eigenschaften verwenden
	}


}
