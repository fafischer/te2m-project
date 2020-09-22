/*
 * ProjectType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.te2m.project.service.core.entity.usecase.UseCaseType;

/**
 * The Enum ProjectType.
 *
 * @author ffischer
 */
public enum ProjectType {

	/**
	 * Generic project type Does not limit anything. Is used as a default if no project type is specified.
	 */
	GENERIC,
	/**
	 * IT project type Does not limit anything (at least as long as the generic type does not provide any features not
	 * suitable for IT projects.
	 */
	IT_PROJECT,
	/**
	 * Event project type. Does not provide the following features:
	 * <ul>
	 * <li>Business objects</li>
	 * <li>UseCases of type 'view'</li>
	 * </ul>
	 */
	EVENT;

	static {

		GENERIC.addValidUseCaseTypes(UseCaseType.USECASE, UseCaseType.VIEW, UseCaseType.NONFUNCTIONAL_REQUIREMENT);
		GENERIC.setBusinessObjectsAllowed(true);
		EVENT.addValidUseCaseTypes(UseCaseType.USECASE, UseCaseType.NONFUNCTIONAL_REQUIREMENT);
		EVENT.setBusinessObjectsAllowed(false);
		IT_PROJECT.addValidUseCaseTypes(UseCaseType.USECASE, UseCaseType.VIEW, UseCaseType.NONFUNCTIONAL_REQUIREMENT);
		IT_PROJECT.setBusinessObjectsAllowed(true);

	}

	/**
	 * The valid use case types.
	 */
	private List<UseCaseType> validUseCaseTypes;
	/**
	 * The business objects allowed.
	 */
	private boolean businessObjectsAllowed;

	/**
	 * Adds the valid use case types.
	 *
	 * @param types the types
	 */
	private void addValidUseCaseTypes(UseCaseType... types) {
		if (null == validUseCaseTypes) {
			validUseCaseTypes = new ArrayList<UseCaseType>();
		}
		validUseCaseTypes.addAll(Arrays.asList(types));
	}

	/**
	 * Gets the valid use case types.
	 *
	 * @return the valid use case types
	 */
	public List<UseCaseType> getValidUseCaseTypes() {
		return validUseCaseTypes;
	}

	/**
	 * Checks if is business objects allowed.
	 *
	 * @return true, if is business objects allowed
	 */
	public boolean isBusinessObjectsAllowed() {
		return businessObjectsAllowed;
	}

	/**
	 * Sets the business objects allowed.
	 *
	 * @param businessObjectsAllowed the new business objects allowed
	 */
	public void setBusinessObjectsAllowed(boolean businessObjectsAllowed) {
		this.businessObjectsAllowed = businessObjectsAllowed;
	}
}
