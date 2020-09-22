/*
 * UseCaseDependencySubType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

/**
 * The Enum UseCaseDependencySubType.
 *
 * @author ffi
 */
public enum UseCaseDependencySubType {

	/**
	 * The one to one.
	 */
	ONE_TO_ONE,

	/**
	 * The one to many.
	 */
	ONE_TO_MANY,

	/**
	 * The many to many.
	 */
	MANY_TO_MANY
}
