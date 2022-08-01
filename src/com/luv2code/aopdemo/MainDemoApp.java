package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		// read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		// get vanilla bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		// call the business method
		Account myAccount = new Account();
		myAccount.setName("Mahdu");
		myAccount.setLevel("Platinum");
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();

		// call the accountDAO getters/setters to see if advices are being applied
		// just to see if they work to begin with
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("gold");

		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();

		// get membership bean from spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		// call the membership business method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();

		// close the spring context
		context.close();
	}

}
