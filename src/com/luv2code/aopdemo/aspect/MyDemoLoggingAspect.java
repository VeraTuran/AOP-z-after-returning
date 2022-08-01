package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	// PLUS DE TYPE D'ADVICE : en plus de @Before il y a @AfterReturning,
	// @AfterThrowing, @After et @Around. On s'occupe de @AfterReturning en premier;
	// il permet de rajouter du code une fois que la fonction annoté à été executé
	// avec succès, c'est-à-dire sans exceptions. C'est utlisé pourdu logging, de la
	// securité, des transactions, comme pour de l'audit logging (qui, quoi, quand,
	// où), mais le plus interessant, pour retravailler des données avant de les
	// rendre à l'émetteur de la demande (post-precessing, formatting, enrichment).

	// Exemple banal: en front on permet à l'utilisateur de saisir son numéro de tel
	// avec des espaces ou des points ou des parenthèses entre les chiffres, mais on
	// enlève ça avant de l'envoyer en BDD.

	// @AfterReturning s'utilise comme @Before mais en plus il faut déclarer les
	// valeurs de retour avec returning="maVariable". (On peut mettre n'importe quel
	// nom, ça reste au niveau de l'AOP, il feut juste mettre le même nom). Donc ça
	// s'écrit comme ça:

	// (@AfterReturning(pointcut="execution(chemin etc.)", returning="maVariable")
	// public void afterReturningResultFindAccountsAdvice(Joinpoint theJoinPoint,
	//// List<Account> maVariable)

	// 1. ajouter constructeurs et toString() à la classe Account
	// 2. ajouter nlle methode findAccounts() dans AccountDAO
	// 3. mettre à jour MainDemoAppqui appellera findAccounts()
	// 4. ajouter advice @AfterReturning
	// 5. on modifie les données transmises avec l'advice

	// ajouter nouveau advice pour@AfterReturning sur le findAccounts method
	//// le pointcut ici ne s'execute que sur une methode : findAccounts()
	@AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		// imprimer le nom de la methode sur laquelle on aura le advice
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n---------------> Executing @AfterReturning on method: " + method);
		// imprimer les resultats de la methode
		System.out.println("\n---------------> result is: " + result);
		// modifier les données
		// convertir les noms des Account en MAJUSCULES
		convertAccountNamesToUppercase(result);
		System.out.println("\n---------------> result is: " + result);
	}

	private void convertAccountNamesToUppercase(List<Account> result) {
		// boucler sur les accounts
		for (Account tempAccount : result) {
			// choper version en majuscules
			String theUpperName = tempAccount.getName().toUpperCase();
			// mettre à jour le nom de l'account
			tempAccount.setName(theUpperName);
		}
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n----------> Executing @Before advice on addAccount()");
		// afficher la signature de la methode
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		// afficher les args de la methode, pour ce faire:
		//// 1. choper les args
		Object[] args = theJoinPoint.getArgs();
		//// 2. boucler sur les args
		for (Object tempArg : args) {
			System.out.println(tempArg);

			if (tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());

			}

		}
	}

	// NEXT UP: @AfterThrowing advice
	// s'execute après qu'une methode rencontre une exception. C'est bien utile pour
	// faire des vérifications ou encore pour notifier l'équipe devOps par message.
	// C'est bien de l'encapsuler pour pouvoir facilement le reutiliser.

	// @AfterThrowing(pointcut="execution(fsdfjij)", throwing="lExceptionVoulue")
	// public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable
	// lExceptionVoulue) {}

	// D'abord on crée un advice qui s'execute en cas d'exception
	// Ensuite on logge l'exception en chopant l'objet d'exception

	// /!\ @AfterThrowingAdvice n'empêche pas l'exception d'apparaître dans le
	// programme main, juste de pouvoir le lire et de le logger.
	// Si on veut s'occuper de l'exception, ou de l'avaler dans le programme, il
	// faut utiliser @Around.

	// 1. ajouter try-catch block dans main
	// 2. modifier AccountDAO pour qu'il génère une exception
	// 3. ajouter l'advice @AfterThrowing
}
