/*
 * Named.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

/**
 * The Interface Named.
 *
 * @author ffischer
 */
public interface Named extends Identifyable {

	/**
	 * .*
	 *
	 * @return .
	 */
	String getDescription();

	/**
	 * DOCUMENT ME!.
	 *
	 * @param description the new description
	 */
	void setDescription(String description);

	/**
	 * .*
	 *
	 * @return .
	 */
	String getName();

	/**
	 * DOCUMENT ME!.
	 *
	 * @param name the new name
	 */
	void setName(String name);

}
