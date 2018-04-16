package com.nic.deputation.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such resource found")
public class ResourceNotFound  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5410213733734302371L;

}
