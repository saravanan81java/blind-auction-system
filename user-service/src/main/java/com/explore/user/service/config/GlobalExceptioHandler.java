package com.explore.user.service.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptioHandler {
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleGlobalException(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getLocalizedMessage());
	}

}
