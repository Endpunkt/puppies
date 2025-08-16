package de.irahi.welp.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import lombok.Data;

@Entity
@Data
@Access(AccessType.FIELD)
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="category_name")
	private String categoryName;
	
	@OneToMany(mappedBy="category")
	private Set<Food> foodObjects;

	@OneToMany(mappedBy="category")
	private Set<Puppy> puppyObjects;
	
	@Override
	public int hashCode() {
		return Objects.hash(id, categoryName, foodObjects, puppyObjects);
	}

}
