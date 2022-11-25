package com.coderscampus.AkshayAssignment13.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.AkshayAssignment13.domain.Account;
import com.coderscampus.AkshayAssignment13.domain.User;
import com.coderscampus.AkshayAssignment13.service.AccountService;
import com.coderscampus.AkshayAssignment13.service.UserService;

public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/")
	public String getRoot(ModelMap model) {
		return "users";
	}

	@GetMapping("/users")
	public String getUsers(ModelMap modelMap) {
		List<User> users = this.userService.findAll();
		modelMap.put("users", users);
		return "users";
	}

	@GetMapping("/users/{user_id}")
	public String getUser(ModelMap modelMap, @PathVariable Long user_id) {
		User user = this.userService.findById(user_id);
		modelMap.put("users", Arrays.asList(user));
		modelMap.put("user", user);
		return "users";
	}

	@PostMapping("/users/{user_id}")
	public String postUser(User user, Account account) {
		if (account != null) {
			this.userService.addAccount(user.getuser_id(), account);
		}

		this.userService.saveUser(user);
		return "redirect:/users/" + user.getuser_id();
	}

	@PostMapping("/users/{user_id}/delete")
	public String postUserDeletion(@PathVariable Long user_id) {
		this.userService.delete(user_id);
		return "redirect:/users";
	}

	@GetMapping("/register")
	public String getUserRegistration(ModelMap modelMap) {
		modelMap.put("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String postUserRegistration(User user) {
		this.userService.saveUser(user);
		return "redirect:/register";
	}

	@PostMapping("/users/{user_id}/accounts")
	public String getAccount(ModelMap modelMap, @PathVariable Long user_id) {
		Account account = new Account();
		User user = this.userService.findById(user_id);
		int numbOfAccounts = 1;

		if (user.getAccounts() != null) {
			numbOfAccounts = user.getAccounts().size() + 1;
		}

		account.setAccountName("Account #" + numbOfAccounts);
		this.userService.addAccount(user_id, account);
		int size = this.userService.findById(user_id).getAccounts().size();
		Account re = this.userService.findById(user_id).getAccounts().get(size - 1);
		return "redirect:/users/" + user_id + "/accounts/" + re.getaccount_id();
	}

	@GetMapping("/users/{user_id}/accounts/{account_id}")
	public String getUserAccount(ModelMap modelMap, @PathVariable Long user_id, @PathVariable Long account_id) {
		modelMap.put("user", this.userService.findById(user_id));
		modelMap.put("account", this.accountService.findById(account_id));
		return "account";
	}

	@PostMapping("/users/{user_id}/accounts/{account_id}")
	public String postUserAccount(@PathVariable Long user_id, @PathVariable Long account_id, Account account) {
		this.accountService.saveAccount(account);
		return "redirect:/users/" + user_id + "/accounts/" + account_id;
	}
}
