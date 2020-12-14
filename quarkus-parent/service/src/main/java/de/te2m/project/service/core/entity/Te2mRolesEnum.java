/*
 * Te2mRolesEnum.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

/**
 * The Enum Te2mRolesEnum.
 *
 * @author ffischer
 */
public enum Te2mRolesEnum {

	/**
	 * Guest Role Usually only read access.
	 */
	VISITOR("Guest role, only read access"),

	/**
	 * Team member Access to most project details.
	 */
	USER("Team member role, has access to most project details"),

	/**
	 * Architect role Can approve requirements for the project team.
	 */
	ARCHITECT("Architect role, can approve requirements for the project team"),
	/**
	 * Approver role Approves requirements for the end customer.
	 */
	APPROVER("Approver role, approves requirements for the end customer."),

	/**
	 * Stakeholder role Will receive reports, additional read access.
	 */
	STAKEHOLDER("Stakeholder role, will receive reports, additional read access"),
	/**
	 * Admin role Can manage the project.
	 */
	ADMIN("Admin role, can manage the project.");

	/**
	 * The desc.
	 */
	private final String desc;

	/**
	 * Instantiates a new te2m roles enum.
	 *
	 * @param newDesc the new desc
	 */
	Te2mRolesEnum(String newDesc) {
		this.desc = newDesc;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return desc;
	}
}
