package com.zensar.actuator;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthActuator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		// TODO Auto-generated method stub
		
		Random random = new Random();
		int randomNumber = random.nextInt(100);
		
		if(randomNumber%2 == 0)
			builder.up();
		
		else
			builder.down();
		
	}

}
