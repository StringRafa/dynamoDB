package com.panamby.dynamodb.model.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.panamby.dynamodb.model.exceptions.DynamoException;
import com.panamby.dynamodb.model.exceptions.DynamoGeneralDataErrorResponse;
import com.panamby.dynamodb.model.exceptions.DynamoGeneralErrorResponse;
import com.panamby.dynamodb.utils.StatusConstantUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GeneralExceptionsHandler extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {

	HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	log.error(String.format("ERROR_MESSAGE [%s]", ex.getMessage()));
	
	return new ResponseEntity<>(
		new DynamoGeneralErrorResponse(new DynamoGeneralDataErrorResponse(
			"Unexpected error occurs", "Internal server error.", StatusConstantUtils.FAIL)),
		httpStatus);
    }

    @ExceptionHandler(DynamoException.class)
    public ResponseEntity<Object> handleDynamoException(DynamoException ex,
	    HttpServletRequest request) {

	HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

	log.error(String.format("ERROR_MESSAGE [%s] - TRANSACTION_TYPE [%s]", ex.getMessage(), ex.getTransactionType()));
	
	return new ResponseEntity<>(
		new DynamoGeneralErrorResponse(new DynamoGeneralDataErrorResponse(
			"Unprocessable Entity", ex.getMessage(), StatusConstantUtils.FAIL)),
		httpStatus);
    }
   
}
