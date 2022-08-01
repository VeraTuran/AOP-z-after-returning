package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	public boolean addSillyMember() {

		System.out.println(getClass() + ": DOING DB WORK : ADDING A MEMBERSHIP ACCOUNT");
		return true;
	}

	// on ajoute une méthode pour tester si le dernier pointcut prend vraiment tt
	// les méthodes d'un même package:
	public boolean goToSleep() {
		System.out.println(getClass() + ": Zzz ... inside public boolean goToSleep() method ... Zzz");
		return false;
	}
}
