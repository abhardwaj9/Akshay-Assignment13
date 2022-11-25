package com.coderscampus.AkshayAssignment13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coderscampus.AkshayAssignment13.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
}