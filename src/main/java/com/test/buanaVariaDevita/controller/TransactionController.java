package com.test.buanaVariaDevita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.buanaVariaDevita.dto.TransactionDto;
import com.test.buanaVariaDevita.model.Transaction;
import com.test.buanaVariaDevita.service.TransactionService;

@RestController
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/test")
	public String welcome() {
		return "Welcome to Spring Boot Rest API";
	}

	@GetMapping("/transaction")
	public List<Transaction> findAll() {
		return transactionService.findAll();
	}

	@GetMapping("/transactions")
	public List<Transaction> findByUserName(@RequestParam("userName") String userName) {
        return transactionService.findByUserName(userName);
    }

	@PostMapping("/transaction")
	public Transaction create(@RequestBody TransactionDto request) {
		return transactionService.addCart(request.getTransactionId(), request.getUserName(), request.getItemId(),
				request.getKuantitas());
	}

	@DeleteMapping("/transaction")
	public void delete(@RequestBody TransactionDto request) {
		transactionService.delete(request.getTransactionId(), request.getItemId());
	}

	@GetMapping("/transaction/total")
	public String calculateCartTotal(@RequestParam("userName") String userName) {
		return transactionService.calculateCartTotal(userName);
	}
}
