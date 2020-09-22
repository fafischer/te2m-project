/*
 * UseCaseDeterminePriorityProcessType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

/**
 * The Enum UseCaseDeterminePriorityProcessType.
 *
 * @author ffischer
 */
public enum UseCaseDeterminePriorityProcessType {

	/**
	 * Everyone can edit the priority.
	 */
	SIMPLE,

	/**
	 * Only authorized user (like an product owner) can edit th epriority.
	 */
	AUTHORIZED,

	/**
	 * The priority is determined by using an voting process.
	 */
	VOTING

}
