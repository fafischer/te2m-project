/*
 * UseCaseEstimationUnitType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

/**
 * The Enum UseCaseEstimationUnitType.
 *
 * @author ffischer
 */
public enum UseCaseEstimationUnitType {

	/**
	 * The hour.
	 */
	HOUR("h"),

	/**
	 * The staff days.
	 */
	STAFF_DAYS("SD"),

	/**
	 * The story points.
	 */
	STORY_POINTS("Storypoints");

	/**
	 * The display value.
	 */
	private final String displayValue;

	/**
	 * Instantiates a new use case estimation unit type.
	 *
	 * @param unit the unit
	 */
	UseCaseEstimationUnitType(String unit) {
		displayValue = unit;
	}

	/**
	 * Gets the display value.
	 *
	 * @return the display value
	 */
	public String getDisplayValue() {
		return displayValue;
	}
}
