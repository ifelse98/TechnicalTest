package com.test.buanaVariaDevita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.buanaVariaDevita.model.Item;
import com.test.buanaVariaDevita.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/items/{id}")
    public Item findById(@PathVariable("id") String id) {
        return itemService.findById(id);
    }

    @PostMapping("/items")
    public Item create(@RequestBody Item produk) {
        return itemService.create(produk);
    }

    @PutMapping("/items")
    public Item edit(@RequestBody Item produk) {
        return itemService.edit(produk);
    }

    @DeleteMapping("/items/{id}")
    public void deleteById(@PathVariable("id") String id) {
    	itemService.deleteById(id);
    }
}
