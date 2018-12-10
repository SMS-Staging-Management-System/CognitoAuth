package com.revature.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.revature.models.CognitoAuthResponse;

@Component
public class CognitoRestTemplate {
	
	final String baseUrl = "https://t4o3pxu8dj.execute-api.us-west-2.amazonaws.com/";
	final String registerUrl = "dev/cognito/users";
	final String authUrl = "dev/auth";
	
	
	public  ResponseEntity<String> registerUser(String email) {
		System.out.println("here");
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url= baseUrl + registerUrl;
		String requestJson = "{\"email\":\"" + email + "\"}";
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		System.out.println("Entity = " + entity);
		
		try{
			return rt.exchange(url ,HttpMethod.POST, entity , String.class );
		}catch(HttpClientErrorException e) {
			System.out.print(e);
			return new ResponseEntity<String>(HttpStatus.CONFLICT);	
		}
	}
	
	public ResponseEntity<String> checkAuth(String token) {
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authentication", token);
		String requestJson = "";
		String url= baseUrl + authUrl;
		System.out.println(headers.toString());
		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		System.out.println("Entity = " + entity.toString());
		try{
			return rt.exchange(url,HttpMethod.GET ,entity , String.class );
		}catch(HttpClientErrorException e) {
			System.out.print("Error is " + e);
			return new ResponseEntity<String>(HttpStatus.CONFLICT);	
		}
	}
	
	
	
}