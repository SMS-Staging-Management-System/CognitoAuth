package com.revature.cognito.utils;

import java.util.Arrays;
import java.util.List;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.cognito.dtos.CognitoTokenClaims;
import com.revature.cognito.intercomm.CognitoClient;

@Component
public class CognitoUtil {

	private Logger logger = Logger.getRootLogger();

	@Autowired
	private CognitoClient cognitoClient;

	@Autowired
	private Provider<CognitoTokenClaims> claimsProvider;

	/**
	 * 
	 * @return List of roles user has or null if the user is not authenticated
	 */
	public List<String> getRequesterRoles() {
		CognitoTokenClaims claims = getRequesterClaims();
		if (claims == null) {
			return null;
		}

		return Arrays.asList(claims.getGroups().split(","));
	}

	public CognitoTokenClaims getRequesterClaims() {
		String cognitoToken = getCurrentUserToken();

		System.out.println(cognitoToken);
		if (cognitoToken == null) {
			return null;
		}
//		CognitoTokenClaims claims = new CognitoTokenClaims();
		CognitoTokenClaims claims = claimsProvider.get();
		if (claims.getEmail() == null) {
			CognitoTokenClaims retreivedClaims = cognitoClient.authenticateToken(cognitoToken);
			System.out.println("setting value for retreived Claims" + retreivedClaims);
			claims.setEmail(retreivedClaims.getEmail());
			claims.setGroups(retreivedClaims.getGroups());
		}
		return claims;
	}

	public String getCurrentUserToken() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return req.getHeader(HttpHeaders.AUTHORIZATION);
	}

}
