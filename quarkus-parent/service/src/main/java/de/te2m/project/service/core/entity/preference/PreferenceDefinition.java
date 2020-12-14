/*
 * PreferenceDefinition.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.preference;

/**
 * The Enum PreferenceDefinition.
 *
 * @author ffischer
 */
public enum PreferenceDefinition {

	/**
	 * The string.
	 */
	STRING(null),
	/**
	 * The number.
	 */
	NUMBER(null),
	/**
	 * The emailaddress.
	 */
	EMAILADDRESS(null),
	/**
	 * The url.
	 */
	BOOL(null),
	/**
	 * The url.
	 */
	URL(null);
	/**
	 * The reg ex.
	 */
	private final String regEx;

	/**
	 * Instantiates a new preference definition.
	 *
	 * @param valregEx the valreg ex
	 */
	PreferenceDefinition(String valregEx) {
		regEx = valregEx;
	}
}
