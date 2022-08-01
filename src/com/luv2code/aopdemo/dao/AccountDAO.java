package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	String name;
	String serviceCode;

	// ajoute nlle methode findAccounts()
	public List<Account> findAccounts() {
		List<Account> myAccounts = new ArrayList<>();

		// créer qq Accounts
		Account temp1 = new Account("Lumpy Space Princess", "Gold");
		Account temp2 = new Account("Lemongrab", "Silver");
		Account temp3 = new Account("Sir Slicer", "Bronze");

		// ajoutes ces Accounts à la liste
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);

		return myAccounts;
	}

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": DOING DB WORK: ADDING A VANILLA ACCOUNT");
	}

	public boolean doWork() {
		System.out.println(getClass() + ": è_é inside public boolean doWork() method è_é");
		return false;
	}

	public String getName() {
		System.out.println(getClass() + " in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " in setServiceCode()");
		this.serviceCode = serviceCode;
	}

}
