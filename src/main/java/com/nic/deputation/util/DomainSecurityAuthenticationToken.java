package com.nic.deputation.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class DomainSecurityAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 9090138897638014894L;
	private String captcha = null;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	/**
	 * Constructor with one extra parameter for the domain.
	 * 
	 * @param principal
	 *            The user name.
	 * @param credentials
	 *            Contain the password.
	 * @param domain
	 *            The domain.
	 */
	public DomainSecurityAuthenticationToken(Object principal, Object credentials, String captcha) {
		super(principal, credentials);
		this.captcha = captcha;
	}

}
