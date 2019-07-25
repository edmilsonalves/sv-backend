package com.api.exception;

import java.util.Arrays;
import java.util.List;

import com.api.enums.ErrorCode;

public class BusinessException extends RuntimeException {

	private ErrorCode errorCode;
	private Object[] parameters;

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(final ErrorCode errorCode) {
		super(errorCode.getMensagem());
		this.errorCode = errorCode;
	}

	public BusinessException(final ErrorCode errorCode, final Object... parameters) {
		super(errorCode.getMensagem());
		this.errorCode = errorCode;
		this.parameters = parameters;
	}

	public BusinessException(final ErrorCode errorCode, final Throwable cause, final Object... parameters) {
		super(errorCode.getMensagem(), cause);
		this.errorCode = errorCode;
		this.parameters = parameters;
	}

	public final ErrorCode getErrorCode() {
		return errorCode;
	}

	public List<Object> getParameters() {

		if (parameters != null) {
			return Arrays.asList(parameters);
		}

		return null;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

}
