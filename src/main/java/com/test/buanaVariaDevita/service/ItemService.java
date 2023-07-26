package com.test.buanaVariaDevita.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.test.buanaVariaDevita.exception.BadRequestException;
import com.test.buanaVariaDevita.exception.ResourceNotFoundException;
import com.test.buanaVariaDevita.model.Item;
import com.test.buanaVariaDevita.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public Item findById(String id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item dengan id " + id + " tidak ditemukan"));
	}

	public Item create(Item item) {
		if (!StringUtils.hasText(item.getNama())) {
			throw new BadRequestException("Nama item tidak boleh kosong");
		}

		item.setId(UUID.randomUUID().toString());
		return itemRepository.save(item);
	}

	public Item edit(Item item) {
		if (!StringUtils.hasText(item.getId())) {
			throw new BadRequestException("Item ID harus diisi");
		}

		if (!StringUtils.hasText(item.getNama())) {
			throw new BadRequestException("Nama Item tidak boleh kosong");
		}

		return itemRepository.save(item);
	}

	public void deleteById(String id) {
		itemRepository.deleteById(id);
	}
}
