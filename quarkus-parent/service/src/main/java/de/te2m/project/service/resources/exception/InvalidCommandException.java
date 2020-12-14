package de.te2m.project.service.resources.exception;

import java.io.Serializable;

public class InvalidCommandException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public InvalidCommandException() {
	}

	public InvalidCommandException(String message) {
		super(message);
	}

	public InvalidCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidCommandException(Throwable cause) {
		super(cause);
	}

	public InvalidCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
