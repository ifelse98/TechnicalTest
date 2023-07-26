package com.test.buanaVariaDevita.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.buanaVariaDevita.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
	
	Optional<Transaction> findByUserNameAndItemId(String userName, String itemId);
	
	Optional<Transaction> findByTransactionIdAndItemId(String id, String itemId);
	
	List<Transaction> findByuserNameEquals(String userName);

}
