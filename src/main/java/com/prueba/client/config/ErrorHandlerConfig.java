package com.prueba.client.config;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.prueba.client.exceptions.GeneralServiceException;
import com.prueba.client.exceptions.NoDataFoundException;
import com.prueba.client.exceptions.ValidateServiceException;
import com.prueba.client.util.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> all(Exception e, WebRequest request){
		log.error(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, "Internal Server Error", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request){
		log.info(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request){
		log.info(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GeneralServiceException.class)
	public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request){
		log.error(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, "Internal Server Error", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(MethodArgumentNotValidException ex,
			WebRequest request) {

		WrapperResponse<?> response = new WrapperResponse<>(false, "Internal Server Error", null);
		if (CollectionUtils.isEmpty(ex.getBindingResult().getFieldErrors())) {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		StringBuilder errorMsg = new StringBuilder();
		errorMsg.append("");
		List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
		for (FieldError fieldError : fieldErrorList) {
			errorMsg.append("[").append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage())
					.append("]").append(System.lineSeparator());
		}
		response = new WrapperResponse<>(false, errorMsg.toString(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}
}
