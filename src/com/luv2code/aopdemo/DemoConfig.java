package com.luv2code.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // spring pure java configuration
@EnableAspectJAutoProxy // spring aop proxy support
@ComponentScan("com.luv2code.aopdemo") // component scan for components and aspects - recurse packages
public class DemoConfig {

}
