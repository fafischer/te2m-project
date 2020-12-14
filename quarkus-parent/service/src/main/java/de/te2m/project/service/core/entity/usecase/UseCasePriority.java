/*
 * UseCasePriority.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

/**
 * Priority values for usecases. See MoSCoW method for more details.
 *
 * @author ffischer
 */
public enum UseCasePriority {

	/**
	 * The must.
	 */
	MUST,

	/**
	 * The should.
	 */
	SHOULD,

	/**
	 * The could.
	 */
	COULD,

	/**
	 * The wont.
	 */
	WONT
}
