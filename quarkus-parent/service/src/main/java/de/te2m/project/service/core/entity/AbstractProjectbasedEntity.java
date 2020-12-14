/*
 * AbstractProjectbasedEntity.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

import java.util.*;

import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.core.entity.text.Text;
import de.te2m.project.service.core.entity.usecase.UseCaseAttachment;
import javax.persistence.*;

/**
 * The Class AbstractProjectbasedEntity.
 *
 * @author ffischer
 */
@MappedSuperclass
public abstract class AbstractProjectbasedEntity
		extends AbstractNamedEntity {

	/**
	 * The comments.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany(cascade = CascadeType.ALL)
	Collection<Text> comments;
	/**
	 * DOCUMENT ME!.
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Project project;
	/**
	 * Attachments.
	 */
	@OneToMany
	private Collection<UseCaseAttachment> attachments;

	/**
	 * Instantiates a new abstract projectbased entity.
	 */
	public AbstractProjectbasedEntity() {
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param project DOCUMENT ME!
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 */
	public void addComment(Text comment) {
		if (null == comment) {
			return;
		}

		if (null == comments) {
			comments = new HashSet<Text>();
		}

		if (!comments.contains(comment)) {
			comments.add(comment);
		}
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public Collection<Text> getComments() {
		if (null == comments) {
			comments = new ArrayList<Text>();
		}
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(Collection<Text> comments) {
		this.comments = comments;
	}

	/**
	 * Gets the sorted comments.
	 *
	 * @return the sorted comments
	 */
	public List<Text> getSortedComments() {
		List<Text> l = new ArrayList<Text>(getComments());

		Comparator<Text> textcomparator = new Comparator<Text>() {
			public int compare(Text c1, Text c2) {
				long t1 = c1.getCreationDate().getTime();
				long t2 = c2.getCreationDate().getTime();
				if (t1 == t2) {
					return 0;
				}
				return t1 < t2 ? -1 : 1;
			}
		};

		Collections.sort(l, textcomparator);
		return l;

	}

	/**
	 * Get all attachments.
	 *
	 * @return The attachments
	 */
	public Collection<UseCaseAttachment> getAttachments() {
		if (null == attachments) {
			attachments = new HashSet<UseCaseAttachment>();
		}
		return attachments;
	}

	/**
	 * Set the attachments.
	 *
	 * @param attachments the new attachments
	 */
	public void setAttachments(Collection<UseCaseAttachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * Add a new attachment.
	 *
	 * @param a the new attachment
	 */
	public void addAttachment(UseCaseAttachment a) {
		if (null == a) {
			return;
		}
		if (null == attachments) {
			attachments = new HashSet<UseCaseAttachment>();
		}
		if (attachments.contains(a)) {
			return;
		}
		attachments.add(a);
	}
}
