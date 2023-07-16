package com.udemy.springbootecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.udemy.springbootecommerce.entity.Country;

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries",path = "countries")
public interface CountryRepository extends JpaRepository<Country,Integer>{
	
}
