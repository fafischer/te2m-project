/*
 * UseCaseState.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

/**
 * The Enum UseCaseState.
 *
 * @author ffischer
 */
public enum UseCaseState {

	/**
	 * The new.
	 */
	NEW(false),

	/**
	 * The in work.
	 */
	IN_WORK(false),

	/**
	 * The approved.
	 */
	APPROVED(false),

	/**
	 * The deferred.
	 */
	DEFERRED(true),

	/**
	 * The rejected.
	 */
	REJECTED(true),

	/**
	 * The finished.
	 */
	FINISHED(true),

	UNKNOWN(true);

	/**
	 * The final state.
	 */
	private boolean finalState;

	/**
	 * Instantiates a new use case state.
	 *
	 * @param isFinalState the is final state
	 */
	UseCaseState(boolean isFinalState) {
		this.finalState = isFinalState;
	}

	/**
	 * Checks if is final state.
	 *
	 * @return true, if is final state
	 */
	public boolean isFinalState() {
		return finalState;
	}

	/**
	 * Sets the final state.
	 *
	 * @param finalState the new final state
	 */
	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}
}
