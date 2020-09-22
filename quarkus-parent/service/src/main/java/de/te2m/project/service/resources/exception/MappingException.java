package de.te2m.project.service.resources.exception;

import java.io.Serializable;

public class MappingException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public MappingException() {
	}

	public MappingException(String message) {
		super(message);
	}

	public MappingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MappingException(Throwable cause) {
		super(cause);
	}

	public MappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
