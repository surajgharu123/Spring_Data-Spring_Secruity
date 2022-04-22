package com.zensar.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 
 * Piece of Code you want to run only once while starting Spring Boot Application
 * than use runner
 *
 */
@Component
//@Order(1)
public class DatabaseRunner implements CommandLineRunner, Ordered {
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside Database :::: " + args);
		for(String arg: args)
			System.out.println(arg);
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 2;
	}

}
