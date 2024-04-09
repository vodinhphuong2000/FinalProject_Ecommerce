package com.finalproject.vdp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ValidationException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
}
