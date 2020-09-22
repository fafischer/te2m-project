/*
 * AbstractEntity.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

import java.util.Date;
import java.util.UUID;

import de.te2m.project.service.core.entity.user.User;
import javax.persistence.*;

/**
 * The Class AbstractEntity.
 *
 * @author ffischer
 */
@MappedSuperclass
public abstract class AbstractEntity implements Identifyable, Comparable<AbstractEntity> {

	/**
	 * The Constant serialVersionUID.
	 */
	protected static final long serialVersionUID = 1L;
	/**
	 * Used for storing the user who has created this object.
	 */
	@ManyToOne
	User creator;
	/**
	 * Used for storing the user who was the last editor of the object.
	 */
	@ManyToOne
	User lastEditor;
	/**
	 * DOCUMENT ME!.
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	Date creationDate;

	/**
	 * DOCUMENT ME!.
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	Date lastModifiedDate;

	/**
	 * DOCUMENT ME!.
	 */
	@Id
	String uuid;
	/**
	 * The source refrence ID. This ID will be used for referencing the master object in case the object was cloned or
	 * imported from XML.
	 */
	String srcRefID;

	/**
	 * DOCUMENT ME!.
	 */
	@Version
	private int version;

	/**
	 * Instantiates a new abstract entity.
	 */
	public AbstractEntity() {
		super();
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param creationDate DOCUMENT ME!
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	@Override
	public String getId() {
		return uuid;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param newUuid DOCUMENT ME!
	 */
	@Override
	public void setId(String newUuid) {
		this.uuid = newUuid;
	}

	/**
	 * Gets the last modified date.
	 *
	 * @return the last modified date
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * Sets the last modified date.
	 *
	 * @param lastModifiedDate the new last modified date
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param version DOCUMENT ME!
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Postload.
	 */
	@PostLoad
	public void postload() {
	}

	/**
	 * Postupdate.
	 */
	@PostUpdate
	public void postupdate() {
	}

	/**
	 * Prepersist.
	 */
	@PrePersist
	public void prepersist() {
		if ((null == uuid) || (uuid.trim().length() == 0)) {
			uuid = UUID.randomUUID().toString();
		}
		if (null == creationDate) {
			creationDate = new Date();
		}
		lastModifiedDate = new Date();
	}

	/**
	 * Preupdate.
	 */
	@PreUpdate
	public void preupdate() {
		if (null == creationDate) {
			creationDate = new Date();
		}
		lastModifiedDate = new Date();
	}

	/**
	 * Gets the creator.
	 *
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * Sets the creator.
	 *
	 * @param creator the new creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * Gets the last editor.
	 *
	 * @return the last editor
	 */
	public User getLastEditor() {
		return lastEditor;
	}

	/**
	 * Sets the last editor.
	 *
	 * @param lastEditor the new last editor
	 */
	public void setLastEditor(User lastEditor) {
		this.lastEditor = lastEditor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AbstractEntity other = (AbstractEntity) obj;
		if ((this.uuid == null) ? (other.uuid != null) : !this.uuid.equals(other.uuid)) {
			return false;
		}
		return this.version == other.version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 7;
        /*
         hash = 83 * hash + (this.creator != null ? this.creator.hashCode() : 0);
         hash = 83 * hash + (this.lastEditor != null ? this.lastEditor.hashCode() : 0);
         */
		hash = 83 * hash + (this.uuid != null ? this.uuid.hashCode() : 0);
		//        hash = 83 * hash + this.version;
		return hash;
	}
    /*
     public String getMasterTemplateID() {
     return masterTemplateID;
     }

     public void setMasterTemplateID(String masterTemplateID) {
     this.masterTemplateID = masterTemplateID;
     }

     */

	/**
	 * Gets the src ref id.
	 *
	 * @return the src ref id
	 */
	public String getSrcRefID() {
		return srcRefID;
	}

	/**
	 * Sets the src ref id.
	 *
	 * @param srcRefID the new src ref id
	 */
	public void setSrcRefID(String srcRefID) {
		this.srcRefID = srcRefID;
	}

	/**
	 * Compares two entities If the identity of the entity is equal then the entities are compared by the creation
	 * date.
	 *
	 * @param o the o
	 * @return the int
	 */
	public int compareTo(AbstractEntity o) {
		if ((this.getId() == null)) {
			this.setId(UUID.randomUUID().toString());
		}

		if (this.getId().equals(o.getId())) {
			return 0;
		} else {
			return compareCreationDates(o);
		}
	}

	/**
	 * Compare creation dates.
	 *
	 * @param o the o
	 * @return the int
	 */
	public int compareCreationDates(AbstractEntity o) {
		if (this.creationDate == null) {
			this.creationDate = new Date();
		}

		if (o.getCreationDate() == null) {
			return -1;
		}

		if (this.getCreationDate().getTime() > o.getCreationDate().getTime()) {
			return 1;
		} else {
			return -1;
		}
	}
}
