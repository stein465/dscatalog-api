package com.stein.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stein.dscatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
