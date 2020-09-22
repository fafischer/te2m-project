/*
 * AbstractNamedEntity.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * The Class AbstractNamedEntity.
 *
 * @author ffischer
 */
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractEntity implements Named {

	/**
	 * DOCUMENT ME!.
	 */

	private String name;

	/**
	 * DOCUMENT ME!.
	 */
	@Lob
	private String description;

	/**
	 * Instantiates a new abstract named entity.
	 */
	public AbstractNamedEntity() {
		super();
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param description the new description
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param name the new name
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
}
