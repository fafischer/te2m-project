/*
 * Text.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.text;

import java.io.Serializable;

import de.te2m.project.service.core.entity.AbstractEntity;
import de.te2m.project.service.core.entity.user.User;
import javax.persistence.*;

/**
 * The Class Text.
 *
 * <p>
 * This class is an persistent entity class. The table name is TEXT * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "TEXT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Text extends AbstractEntity
		implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * DOCUMENT ME!.
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private User author;
	/**
	 * DOCUMENT ME!.
	 */
	@Lob
	private String contentData;
	/**
	 * DOCUMENT ME!.
	 */
	private String name;

	/**
	 * Instantiates a new text.
	 */
	public Text() {
		super();
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param owner the new author
	 */
	public void setAuthor(User owner) {
		this.author = owner;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getContent() {
		return contentData;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.contentData = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getId() + ":[" + author + "] " + contentData;
	}
}
