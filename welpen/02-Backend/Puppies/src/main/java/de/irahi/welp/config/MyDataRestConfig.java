package de.irahi.welp.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import de.irahi.welp.component.AnimalCharacterConverter;
import de.irahi.welp.props.AnimalCharacter;
import jakarta.persistence.metamodel.EntityType;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class MyDataRestConfig implements RepositoryRestConfigurer, WebMvcConfigurer{
	
    private static final String DB_URL = "jdbc:h2:file:./src/main/resources/dogs;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;CACHE_SIZE=1"; // Passe den Pfad an deine DB an
    private static final String DB_USERNAME = "user";          // Dein H2-Benutzername
    private static final String DB_PASSWORD = "pass";            // Dein H2-Passwort
	
	private final EntityManager entityManager;
	
	public MyDataRestConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
//	@Bean
//	public AnimalCharacterConverter enumArrayConverter() {
//		System.out.println("AnimalCharacterConverter BEAN is created!!!!!!!!!!!!!!!!!");
//		return new AnimalCharacterConverter();
//		
//	}
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		exposeIds(config);
	}
	
	  @PreDestroy
	    public void shutdownDatabase() {
	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	            conn.createStatement().execute("SHUTDOWN");
	            System.out.println("H2-Datenbank erfolgreich heruntergefahren.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	//ID for Category
	private void exposeIds(RepositoryRestConfiguration config) {
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
		
	}

}
