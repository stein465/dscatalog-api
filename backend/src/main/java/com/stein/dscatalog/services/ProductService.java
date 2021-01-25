package com.stein.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stein.dscatalog.dto.ProductDTO;
import com.stein.dscatalog.entities.Product;
import com.stein.dscatalog.repositories.ProductRepository;
import com.stein.dscatalog.services.exceptions.DatabaseIntegrityException;
import com.stein.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		Page<Product> list = productRepository.findAll(pageRequest);		
		
		
		/*
		 List<ProductDto> listDto = new ArrayList<>(); 
		 
		for(Product cat: list) {
			listDto.add(new ProductDTO(cat));
			
			return listDto;
		}*/		
		
		return list.map(x -> new ProductDTO(x));		
		
		
	}
	
	@Transactional
	public ProductDTO findById(Long Id) {
		Optional<Product> obj = productRepository.findById(Id);		
		
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new ProductDTO(entity, entity.getCategories());
		
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		//Vai mudar entity.setName(dto.getName());
		
		entity = productRepository.save(entity);		
		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
		Product entity = productRepository.getOne(id);
		// Vai mudar entity.setName(dto.getName());
		
		return new ProductDTO(productRepository.save(entity));
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not Found");
		}
	}

	public void delete(Long id) {				
		
		try {
			productRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Not found Id");
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseIntegrityException("Integrity Violation");
		}
	}
		


}
