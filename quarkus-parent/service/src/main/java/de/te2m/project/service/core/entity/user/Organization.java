/*
 * Organization.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.user;

import java.io.Serializable;
import java.util.Collection;

import de.te2m.project.service.core.entity.AbstractNamedEntity;
import de.te2m.project.service.core.entity.text.Text;
import javax.persistence.*;

/**
 * The Class Organization.
 *
 * <p>
 * This class is an persistent entity class. The table name is  ORGANIZATION  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "ORGANIZATION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Organization extends AbstractNamedEntity implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The display name.
	 */
	protected String displayName;

	/**
	 * The notes.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany
	protected Collection<Text> notes;

	/**
	 * Instantiates a new organization.
	 */
	public Organization() {
		super();
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param displayName DOCUMENT ME!
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getId() + ": " + getDisplayName();
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public Collection<Text> getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(Collection<Text> notes) {
		this.notes = notes;
	}
}
