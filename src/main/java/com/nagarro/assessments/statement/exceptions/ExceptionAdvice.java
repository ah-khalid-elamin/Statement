package com.nagarro.assessments.statement.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


 @ControllerAdvice
 public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	 @ExceptionHandler(InvalidAccountException.class)
	    public ResponseEntity<Object> handleInvalidAccountException(
	    		InvalidAccountException ex, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "Account Id is invalid");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(InvalidUserException.class)
	    public ResponseEntity<Object> handleInvalidUserException(
	    		InvalidUserException ex, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "User Credentials are invalid");

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }

	    
	
	@Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex, HttpHeaders headers, 
	        HttpStatus status, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<String, Object>();
	        body.put("timestamp", LocalDate.now());
	        body.put("status", status.value());

	        List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(x -> x.getDefaultMessage())
	                .collect(Collectors.toList());

	        //body.put("errors", errors);

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
 }