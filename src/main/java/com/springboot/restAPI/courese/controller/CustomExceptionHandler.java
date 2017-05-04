package com.springboot.restAPI.courese.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<?> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		Map<String, String> map = new HashMap<>();
		map.put("status", HttpStatus.BAD_REQUEST.getReasonPhrase());
		for (FieldError fieldError : fieldErrors)
			map.put(fieldError.getField(), messageSource.getMessage(fieldError, null));
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

}
