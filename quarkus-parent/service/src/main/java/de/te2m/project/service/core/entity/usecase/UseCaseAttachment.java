/*
 * UseCaseAttachment.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

import de.te2m.project.service.core.entity.AbstractNamedEntity;
import de.te2m.project.service.core.entity.attachment.DocType;
import javax.persistence.*;

/**
 * The Class UseCaseAttachment.
 *
 * <p>
 * This class is an persistent entity class. The table name is  USECASEATTACHMENT  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "USECASEATTACHMENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class UseCaseAttachment extends AbstractNamedEntity {

	/**
	 * Value for the attachment relation. Depending on the document type this valiue may contain:
	 * <ul>
	 * <li>URL: The URL of the document.</li>
	 * <li>Database document: The ID of the attachment in the DB</li>
	 * <li>File system document: The path to the document</li>
	 * </ul>
	 */
	protected String value;
	/**
	 * The type.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	DocType type;

	/**
	 * Get the doc type.
	 *
	 * @return the doc type
	 */
	public DocType getType() {
		return type;
	}

	/**
	 * Set the doc type.
	 *
	 * @param type the doc type
	 */
	public void setType(DocType type) {
		this.type = type;
	}

	/**
	 * Get the value.
	 *
	 * @return the value
	 * @see UseCaseAttachment#value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set the value.
	 *
	 * @param value the new value
	 * @see UseCaseAttachment#value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
