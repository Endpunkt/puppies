package de.irahi.welp.component;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import de.irahi.welp.dto.PuppyDTO;
import de.irahi.welp.entity.*;

@Component
public class ProductMapper {
	private final ModelMapper modelMapper;
	
	ProductMapper(ModelMapper moMapper){
		this.modelMapper = moMapper;
	}
//	
//	public PuppyDTO toPuppyDTO(ProductObject product) {
//		PuppyDTO puppy = modelMapper.map(product, PuppyDTO.class);
//		return puppy;
//	}
//	
//	public ProductObject toEntity(PuppyDTO puppy) {
//		ProductObject product = modelMapper.map(puppy, ProductObject.class);
//		return product;
//	}
//
//	public List<PuppyDTO> toPuppiesDTO(List<ProductObject> products){
//		List<PuppyDTO> puppies = products.stream().map(this::toPuppyDTO).collect(Collectors.toList());
//		return puppies;
//	}
//	public List<ProductObject> ToEntities(List<PuppyDTO> puppies){
//		List<ProductObject> products = puppies.stream().map(this::toEntity).collect(Collectors.toList());
//		return products;
//	}
}
