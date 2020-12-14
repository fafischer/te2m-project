/*
 * UseCaseType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Enum UseCaseType.
 *
 * @author ffischer
 */
public enum UseCaseType {

	/**
	 * Persona
	 * <p>
	 * A persona is a actor.
	 * </p>
	 */
	PERSONA(false, false),
	/**
	 * Use case
	 * <p>
	 * A use case is a requirement.
	 * </p>
	 */
	USECASE(true, false),
	/**
	 * Nonfunctional requirement
	 * <p>
	 * A nonfunctional requirement does not require any implementation tasks or to-do's. However, a nonfunctional
	 * requirement has a direct impact on the acceptance criteria of an relates use case.
	 * </p>
	 */
	NONFUNCTIONAL_REQUIREMENT(true, true),

	/**
	 * The business object.
	 */
	BUSINESS_OBJECT(true, true, UseCaseSubType.BOUNDARY, UseCaseSubType.CONTROLLER, UseCaseSubType.ENTITY),

	/**
	 * The attribute.
	 */
	ATTRIBUTE(true, true, UseCaseSubType.TRANSIENT, UseCaseSubType.PERSISTENT),

	/**
	 * The package.
	 */
	PACKAGE(true, true),

	/**
	 * The system.
	 */
	SYSTEM(true, true),

	/**
	 * Service.
	 */
	SERVICE(true, true),

	/**
	 * The operation.
	 */
	OPERATION(true, true),

	/**
	 * The view.
	 */
	VIEW(true, true);

	/**
	 * The is selectable.
	 */
	private boolean isSelectable;

	/**
	 * The is it only.
	 */
	private boolean isItOnly;

	/**
	 * The available sub types.
	 */
	private List<UseCaseSubType> availableSubTypes;

	/**
	 * Instantiates a new use case type.
	 *
	 * @param selectable the selectable
	 * @param itOnly     the it only
	 */
	UseCaseType(boolean selectable, boolean itOnly) {

		setSelectable(selectable);

		setItOnly(itOnly);
	}

	/**
	 * Instantiates a new use case type.
	 *
	 * @param selectable the selectable
	 * @param itOnly     the it only
	 * @param subtypes   the subtypes
	 */
	UseCaseType(boolean selectable, boolean itOnly, UseCaseSubType... subtypes) {

		setSelectable(selectable);

		setItOnly(itOnly);

		availableSubTypes = new ArrayList<UseCaseSubType>(Arrays.asList(subtypes));
	}

	/**
	 * Checks if is selectable.
	 *
	 * @return true, if is selectable
	 */
	public boolean isSelectable() {
		return isSelectable;
	}

	/**
	 * Sets the selectable.
	 *
	 * @param isSelectable the new selectable
	 */
	private void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
	}

	/**
	 * Checks if is it only.
	 *
	 * @return true, if is it only
	 */
	public boolean isItOnly() {
		return isItOnly;
	}

	/**
	 * Sets the it only.
	 *
	 * @param isItOnly the new it only
	 */
	private void setItOnly(boolean isItOnly) {
		this.isItOnly = isItOnly;
	}

	/**
	 * Gets the available sub types.
	 *
	 * @return the available sub types
	 */
	public List<UseCaseSubType> getAvailableSubTypes() {
		return availableSubTypes;
	}
}
