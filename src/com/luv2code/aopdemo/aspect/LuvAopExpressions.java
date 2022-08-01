package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {
	//// DECLARATIONS POINTCUT COMMUNS AUX ASPECTS
	// puisqu'elles sont censés être disponible pour les autres aspects, on doit
	// changer leur scope de "private" en "public"
	// on doit également préciser leur nom qualifié dans les aspects comme ça:
	// "lenomqualifié.lenomdelexpressionpointcut()"

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {
	}

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	public void getter() {
	}

	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	public void setter() {
	}

	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {
	}
}
