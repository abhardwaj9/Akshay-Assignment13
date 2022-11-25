package com.coderscampus.AkshayAssignment13.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.AkshayAssignment13.domain.Account;
import com.coderscampus.AkshayAssignment13.repository.AccountRepository;

@Service
public class AccountService

{
	@Autowired
	private AccountRepository accountRepository;

	public void saveAccount(Account account) {
		this.accountRepository.save(account);
	}

	public Account findById(Long account_id) {
		Optional<Account> optionalAccount = this.accountRepository.findById(account_id);
		return optionalAccount.orElse(new Account());
	}
}
