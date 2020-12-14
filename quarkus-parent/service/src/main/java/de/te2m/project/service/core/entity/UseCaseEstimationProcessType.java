/*
 * UseCaseEstimationProcessType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

/**
 * The Enum UseCaseEstimationProcessType.
 *
 * @author ffischer
 */
public enum UseCaseEstimationProcessType {

	/**
	 * Estimations are based on the sum of all task estimations.
	 */
	TASKBASED_ESTIMATION,

	/**
	 * Usecase estimations are based on a process like planning poker.
	 */
	PLANNING_POKER,

	/**
	 * Estimations are simply entered into the uscase details form.
	 */
	DIRECT_ENTRY

}
