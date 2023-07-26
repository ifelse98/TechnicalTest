package com.test.buanaVariaDevita.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.buanaVariaDevita.dto.TransactionDto;
import com.test.buanaVariaDevita.exception.BadRequestException;
import com.test.buanaVariaDevita.model.Item;
import com.test.buanaVariaDevita.model.Transaction;
import com.test.buanaVariaDevita.repository.ItemRepository;
import com.test.buanaVariaDevita.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	public List<Transaction> findByUserName(String username) {
		return transactionRepository.findByuserNameEquals(username);
	}

	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Transactional
	public Transaction addCart(String transactionId, String userName, String itemId, Double kuantitas) {
		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new BadRequestException("Item ID " + itemId + " tidak ditemukan"));

		Optional <Transaction> optional = transactionRepository.findByUserNameAndItemId(userName, itemId);
		Transaction transaction;
		if (optional.isPresent()) {
			transaction = optional.get();
			transaction.setKuantitas(transaction.getKuantitas() + kuantitas);
			transaction.setJumlah(new BigDecimal(transaction.getHarga().doubleValue() * transaction.getKuantitas()));
			transactionRepository.save(transaction);

		} else {
			transaction = new Transaction();
			transaction.setTransactionId(UUID.randomUUID().toString());
			transaction.setUserName(userName);
			transaction.setItem(item);
			transaction.setKuantitas(kuantitas);
			transaction.setHarga(item.getHarga());
			transaction.setJumlah(new BigDecimal(transaction.getHarga().doubleValue() * transaction.getKuantitas()));
			transactionRepository.save(transaction);
		}

		return transaction;

	}
	
	@Transactional
	public List<Transaction> addCarts(List<TransactionDto> request) {
        List<Transaction> savedTransactions = new ArrayList<>();
        for (TransactionDto transactionDTO : request) {
        	String userName = transactionDTO.getUserName();
        	String itemId = transactionDTO.getItemId();
        	Double kuantitas = transactionDTO.getKuantitas();
        	
        	Item item = itemRepository.findById(itemId)
    				.orElseThrow(() -> new BadRequestException("Item ID " + itemId + " tidak ditemukan"));

    		Optional <Transaction> optional = transactionRepository.findByUserNameAndItemId(userName, itemId);
    		Transaction transaction;
            
    		if (optional.isPresent()) {
    			transaction = optional.get();
    			transaction.setKuantitas(transaction.getKuantitas() + kuantitas);
    			transaction.setJumlah(new BigDecimal(transaction.getHarga().doubleValue() * transaction.getKuantitas()));
    			savedTransactions.add(transactionRepository.save(transaction));

    		} else {
    			transaction = new Transaction();
    			transaction.setTransactionId(UUID.randomUUID().toString());
    			transaction.setUserName(userName);
    			transaction.setItem(item);
    			transaction.setKuantitas(kuantitas);
    			transaction.setHarga(item.getHarga());
    			transaction.setJumlah(new BigDecimal(transaction.getHarga().doubleValue() * transaction.getKuantitas()));
    			savedTransactions.add(transactionRepository.save(transaction));
    		}
        }
        return savedTransactions;
    }
	
	@Transactional
	public void delete(String transactionId, String itemId) {
		Transaction transaction = transactionRepository.findByTransactionIdAndItemId(transactionId, itemId).orElseThrow(
				() -> new BadRequestException("Item ID " + itemId + " tidak ditemukan dalam keranjang anda"));

		transactionRepository.delete(transaction);
	}

	@Transactional
	public String calculateCartTotal(String userName) {
		List<Transaction> listTransactiol = transactionRepository.findByuserNameEquals(userName);
		BigDecimal total = BigDecimal.ZERO;

		for (Transaction transaction : listTransactiol) {
			total = total.add(transaction.getJumlah());
			System.out.println("Total" + total);
		}

		return total.toString();
	}

}
