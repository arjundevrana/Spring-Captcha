package com.nic.deputation.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nic.deputation.util.DomainSecurityAuthenticationToken;

public class CustomFilter extends UsernamePasswordAuthenticationFilter {
	private static final String USERNAME_PARAM_KEY = "ssoId";
	private static final String PASSWORD_PARAM_KEY = "password";
	private static final String CAPTCHA_PARAM_KEY = "captcha";
	public static final String CAPTCHA_KEY = "captchaRam";
	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
    throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }
     
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
    throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
	public Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws AuthenticationException {
		String username = request.getParameter(USERNAME_PARAM_KEY);
		String password = request.getParameter(PASSWORD_PARAM_KEY);
		String captcha = request.getParameter(CAPTCHA_PARAM_KEY);
		String remdoamCaptcha = (String) request.getSession().getAttribute(CAPTCHA_KEY);
		if(!remdoamCaptcha.equals(captcha)) {
			throw new AuthenticationServiceException("Please enter correct captcha!! ");
		};
		
		DomainSecurityAuthenticationToken domsecToken = new DomainSecurityAuthenticationToken(username, password,
				captcha);
		return domsecToken;
		

	}

}
