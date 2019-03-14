package com.revature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.cognito.dtos.CognitoTokenClaims;
import com.revature.cognito.utils.CognitoUtil;

//@SpringBootApplication
//@EnableFeignClients
//@RestController
public class CognitoAuthApplication {

	@Autowired
	private CognitoUtil cogUtil;

	public static void main(String[] args) {
		SpringApplication.run(CognitoAuthApplication.class, args);
	}

	@GetMapping("/auth-test")
	public CognitoTokenClaims test() {
		return cogUtil.getRequesterClaims();
	}
}
