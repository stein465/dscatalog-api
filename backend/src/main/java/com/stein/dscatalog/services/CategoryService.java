package com.stein.dscatalog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stein.dscatalog.dto.CategoryDTO;
import com.stein.dscatalog.entities.Category;
import com.stein.dscatalog.repositories.CategoryRepository;

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


}
