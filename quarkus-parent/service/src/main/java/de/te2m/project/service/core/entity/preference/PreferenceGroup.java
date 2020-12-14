/*
 * PreferenceGroup.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.preference;

import java.util.Collection;
import java.util.TreeSet;

import de.te2m.project.service.core.entity.AbstractNamedEntity;
import javax.persistence.*;

/**
 * The Class PreferenceGroup.
 *
 * <p>
 * This class is an persistent entity class. The table name is  PREFERENCEGROUP  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "PREFERENCEGROUP")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PreferenceGroup extends AbstractNamedEntity {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Milestones of the project.
	 *
	 * @see Iteration
	 */
	@OneToMany(mappedBy = "group")

	Collection<Preference> preferences;

	/**
	 * Instantiates a new preference group.
	 */
	public PreferenceGroup() {
		super();
	}

	/**
	 * Add a milestone.
	 *
	 * @param m A new milestone
	 */
	public void addPreference(Preference m) {
		if (null == preferences) {
			preferences = new TreeSet<Preference>();
		}

		preferences.add(m);

		m.setGroup(this);
	}

	/**
	 * Gets the preferences.
	 *
	 * @return the preferences
	 */
	public Collection<Preference> getPreferences() {
		return preferences;
	}

	/**
	 * Sets the preferences.
	 *
	 * @param preferences the new preferences
	 */
	public void setPreferences(Collection<Preference> preferences) {
		this.preferences = preferences;
	}
}
