package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		// lire la classe spring config
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// choper bean du conteneur spring
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

		// appeler methode pour trouver les accounts
		List<Account> theAccounts = theAccountDAO.findAccounts();

		// afficher les accounts
		System.out.println("\n\nMain Program: AfterReturningDemoApp");
		System.out.println("____________");
		System.out.println(theAccounts);
		System.out.println("\n");

		// close the spring context
		context.close();
	}

}
