package com.paschoalini.handler;

import java.io.ObjectInputStream.GetField;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.paschoalini.error.ResourceNotFoundDetails;
import com.paschoalini.error.ResourceNotFoundException;
import com.paschoalini.error.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.Builder.newBuilder()
		.timestamp(new Date().getTime())
		.status(HttpStatus.NOT_FOUND.value())
		.title("Resource not found")
		.detail(rnfException.getMessage())
		.developerMessage(rnfException.getClass().getName())
		.build();
		
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException manvException) {
		// Recuperando todos os erros de validação
		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		
		String fields = "", fieldsMessages = "";
		
		for(FieldError fe : fieldErrors) {
			fields += fe.getField() + ", ";
			fieldsMessages += fe.getDefaultMessage() + ", ";
		}

		ValidationErrorDetails rnfDetails = ValidationErrorDetails.Builder.newBuilder()
		.timestamp(new Date().getTime())
		.status(HttpStatus.BAD_REQUEST.value())
		.title("Field validation error")
		.detail("Field validation error")
		.developerMessage(manvException.getClass().getName())
		.field(fields)
		.fieldMessage(fieldsMessages)
		.build();
		
		return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
	}
}