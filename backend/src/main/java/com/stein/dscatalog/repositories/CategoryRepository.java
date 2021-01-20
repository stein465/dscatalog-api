package com.stein.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stein.dscatalog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
