package com.coderscampus.AkshayAssignment13.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.coderscampus.AkshayAssignment13.domain.Account;
import com.coderscampus.AkshayAssignment13.domain.Address;
import com.coderscampus.AkshayAssignment13.domain.User;
import com.coderscampus.AkshayAssignment13.repository.AccountRepository;
import com.coderscampus.AkshayAssignment13.repository.AddressRepository;
import com.coderscampus.AkshayAssignment13.repository.UserRepository;

public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AddressRepository addressRepository;

	public void save(User user) {
		this.userRepository.save(user);
	}

	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	public User findById(Long user_id) {
		Optional<User> optionalUser = this.userRepository.findById(user_id);
		return optionalUser.orElse(new User());
	}

	public Address findAddress(Long user_id) {
		Address address = this.findById(user_id).getAddress();

		if (address == null) {
			return new Address();
		}

		return address;
	}

	public User saveUser(User user) {
		if (user.getuser_id() != null) {
			Address address = user.getAddress();

			if (address != null) {
				address.setuser_id(user.getuser_id());
				address.setUser(user);
				user.setAddress(address);
			}
		}

		return this.userRepository.save(user);
	}

	public void delete(Long user_id) {
		User user = findById(user_id);

		if (user.getAddress() != null) {
			this.addressRepository.delete(user.getAddress());
		}

		this.userRepository.deleteById(user_id);
	}

	public void addAccount(Long user_id, Account account) {
		User user = findById(user_id);
		account.getUsers().add(user);
		user.getAccounts().add(account);
		this.accountRepository.save(account);
	}
}
