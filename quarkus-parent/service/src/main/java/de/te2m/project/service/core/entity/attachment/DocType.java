/*
 * DocType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.attachment;

/**
 * The Enum DocType.
 *
 * @author ffischer
 */
public enum DocType {

	/**
	 * The document is stored in the local filesystem.
	 */
	FSDocument,

	/**
	 * The document is stored in the database.
	 */
	DBDocument,

	/**
	 * Its a URL.
	 */
	URL

}
