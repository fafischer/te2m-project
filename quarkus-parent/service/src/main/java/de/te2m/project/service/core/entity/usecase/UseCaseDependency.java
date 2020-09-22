/*
 * UseCaseDependency.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

import java.io.Serializable;

import de.te2m.project.service.core.entity.AbstractEntity;
import javax.persistence.*;

/**
 * The Class UseCaseDependency.
 *
 * <p>
 * This class is an persistent entity class. The table name is  USECASEDEPENDENCY  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "USECASEDEPENDENCY")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class UseCaseDependency extends AbstractEntity implements Serializable {

	/**
	 * The src.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne

	UseCase src;

	/**
	 * The target.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne

	UseCase target;

	/**
	 * The type.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	UseCaseDependencyType type;

	/**
	 * The comment.
	 */
	@Lob
	String comment;

	/**
	 * The subtype.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	UseCaseDependencySubType subtype;

	/**
	 * Gets the src.
	 *
	 * @return the src
	 */
	public UseCase getSrc() {
		return src;
	}

	/**
	 * Sets the src.
	 *
	 * @param src the new src
	 */
	public void setSrc(UseCase src) {
		this.src = src;
	}

	/**
	 * Gets the target.
	 *
	 * @return the target
	 */
	public UseCase getTarget() {
		return target;
	}

	/**
	 * Sets the target.
	 *
	 * @param target the new target
	 */
	public void setTarget(UseCase target) {
		this.target = target;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public UseCaseDependencyType getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(UseCaseDependencyType type) {
		this.type = type;
	}
}
