package com.coderscampus.AkshayAssignment13.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderscampus.AkshayAssignment13.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
}
