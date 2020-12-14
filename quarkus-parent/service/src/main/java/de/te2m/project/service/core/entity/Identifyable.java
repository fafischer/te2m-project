/*
 * Identifyable.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

/**
 * The Interface Identifyable.
 *
 * @author ffischer
 */
public interface Identifyable {

	/**
	 * .*
	 *
	 * @return .
	 */
	String getId();

	/**
	 * DOCUMENT ME!.
	 *
	 * @param newUuid the new id
	 */
	void setId(String newUuid);

}
