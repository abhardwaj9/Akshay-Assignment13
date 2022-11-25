package com.coderscampus.AkshayAssignment13.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	private Long account_id;
	private String accountName;
	private List<Transaction> transactions = new ArrayList<>();
	private List<User> users = new ArrayList<>();

	public Account() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getaccount_id() {
		return account_id;
	}

	public void setaccount_id(Long account_id) {
		this.account_id = account_id;
	}

	@Column(length = 100)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@OneToMany(mappedBy = "account")
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@ManyToMany(mappedBy = "accounts")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", accountName=" + accountName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(account_id, accountName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(account_id, other.account_id) && Objects.equals(accountName, other.accountName);
	}
}
