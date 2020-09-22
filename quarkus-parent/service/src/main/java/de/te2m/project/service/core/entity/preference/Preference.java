/*
 * Preference.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.preference;

import de.te2m.project.service.core.entity.AbstractEntity;
import javax.persistence.*;

/**
 * The Class Preference.
 *
 * <p>
 * This class is an persistent entity class. The table name is  PREFERENCES  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "PREFERENCES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Preference extends AbstractEntity {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The definition.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	protected PreferenceDefinition definition;

	/**
	 * The group.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne
	protected PreferenceGroup group;

	/**
	 * The preference key.
	 */
	protected String preferenceKey;

	/**
	 * The value.
	 */
	protected String value;

	/**
	 * Instantiates a new preference.
	 */
	public Preference() {
		super();
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public PreferenceDefinition getDefinition() {
		return definition;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param definition DOCUMENT ME!
	 */
	public void setDefinition(PreferenceDefinition definition) {
		this.definition = definition;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return preferenceKey;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.preferenceKey = key;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getValue() {
		return value;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param value DOCUMENT ME!
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public PreferenceGroup getGroup() {
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group the new group
	 */
	public void setGroup(PreferenceGroup group) {
		this.group = group;
	}
}
