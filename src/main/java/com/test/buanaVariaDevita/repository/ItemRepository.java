package com.test.buanaVariaDevita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.buanaVariaDevita.model.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
	
	List<Item> findAllById(String itemId);

}
