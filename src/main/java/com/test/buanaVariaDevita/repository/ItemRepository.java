package com.test.buanaVariaDevita.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.buanaVariaDevita.model.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

}
