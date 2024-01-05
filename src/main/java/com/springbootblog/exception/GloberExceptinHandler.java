package com.springbootblog.exception;

import java.lang.reflect.Field;
import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springbootblog.Paylode.ExceptionDetails;

@ControllerAdvice
public class GloberExceptinHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFountExceeption.class)
	public ResponseEntity<ExceptionDetails> handlingResourceNotFountException(ResourceNotFountExceeption exception, WebRequest request){
		
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ExceptionDetails> handlingBlogApiException(BlogApiException exception, WebRequest request){
		
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
	}
	

// The Exception will used by all exceptions in the project "Global Exception"
// this exception will handle All the Exception Except above one
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> handlingGlobalException(Exception exception, WebRequest request){
		
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders header, HttpStatus status, WebRequest request) {
		Map<String,String> error=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((errors)->{
			String fieldName=((FieldError)error).getField();
			String message=errors.getDefaultMessage();
			error.put(fieldName,message);
		});
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionDetails> handlingAccessDeniedException(AccessDeniedException exception, WebRequest request){
		
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(), exception.getMessage(),request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.UNAUTHORIZED);
	}
		
	}
