/*
 * Attachment.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.attachment;

import de.te2m.project.service.core.entity.AbstractNamedEntity;

/**
 * The Interface Attachment.
 *
 * @author ffischer
 */
public interface Attachment {

	/**
	 * The owner of the attachment.
	 *
	 * @return the owner
	 */
	AbstractNamedEntity getOwner();

	/**
	 * Set the owner of the attachment.
	 *
	 * @param owner the owner
	 */
	void setOwner(AbstractNamedEntity owner);

	/**
	 * Get the doc type.
	 *
	 * @return the doc type
	 */
	DocType getType();

	/**
	 * Set the doc type.
	 *
	 * @param type the doc type
	 */
	void setType(DocType type);

	/**
	 * Get the value.
	 *
	 * @return the value
	 * @see UseCaseAttachment#value
	 */
	String getValue();

	/**
	 * Set the value.
	 *
	 * @param value the new value
	 * @see UseCaseAttachment#value
	 */
	void setValue(String value);

}
