package br.com.grupodass.ted.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ApiErrors {

	
		private List<String> errors;
		
		
		public ApiErrors(String err) {
			this.errors = Arrays.asList(err);
		}
}
