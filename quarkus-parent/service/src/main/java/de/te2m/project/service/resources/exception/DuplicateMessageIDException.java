package de.te2m.project.service.resources.exception;

import java.io.Serializable;

public class DuplicateMessageIDException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public DuplicateMessageIDException() {
	}

	public DuplicateMessageIDException(String message) {
		super(message);
	}

	public DuplicateMessageIDException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateMessageIDException(Throwable cause) {
		super(cause);
	}

	public DuplicateMessageIDException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
