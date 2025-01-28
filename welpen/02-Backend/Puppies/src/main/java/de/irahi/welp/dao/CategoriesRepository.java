package de.irahi.welp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


import de.irahi.welp.entity.Category;
@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoriesRepository extends JpaRepository<Category,Long>{
		
}
