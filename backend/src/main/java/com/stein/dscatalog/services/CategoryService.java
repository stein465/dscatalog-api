package com.stein.dscatalog.services;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stein.dscatalog.dto.CategoryDTO;
import com.stein.dscatalog.entities.Category;
import com.stein.dscatalog.repositories.CategoryRepository;
import com.stein.dscatalog.services.exceptions.DatabaseIntegrityException;
import com.stein.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category> list = categoryRepository.findAll();		
		
		
		/*
		 List<CategoryDto> listDto = new ArrayList<>(); 
		 
		for(Category cat: list) {
			listDto.add(new CategoryDTO(cat));
			
			return listDto;
		}*/		
		
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());		
		
		
	}
	
	@Transactional
	public CategoryDTO findById(Long Id) {
		Optional<Category> obj = categoryRepository.findById(Id);		
		
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		
		return new CategoryDTO(entity);
		
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		
		entity = categoryRepository.save(entity);		
		
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
		Category entity = categoryRepository.getOne(id);
		entity.setName(dto.getName());
		
		return new CategoryDTO(categoryRepository.save(entity));
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not Found");
		}
	}

	public void delete(Long id) {				
		
		try {
		categoryRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Not found Id");
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseIntegrityException("Integrity Violation");
		}
	}
		


}
