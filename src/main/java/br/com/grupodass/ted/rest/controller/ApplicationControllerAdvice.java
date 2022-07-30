package br.com.grupodass.ted.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.grupodass.ted.exception.TedException;
import br.com.grupodass.ted.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(TedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleTedExeption(TedException ex) {
		String err = ex.getMessage();
		return new ApiErrors(err);
	}
	
}
