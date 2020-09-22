/*
 * Role.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.user;

import de.te2m.project.service.core.entity.AbstractEntity;
import javax.persistence.*;

/**
 * The Class Role.
 *
 * <p>
 * This class is an persistent entity class. The table name is  ROLE  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "ROLE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Role extends AbstractEntity {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The description.
	 */
	protected String description;

	/**
	 * The name.
	 */
	@Column(nullable = false)
	protected String name;

	/**
	 * Instantiates a new role.
	 */
	public Role() {
		super();
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getName() {
		return name;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
