/*
 * Prerequisite.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.TreeSet;

import de.te2m.project.service.core.entity.usecase.UseCase;
import javax.persistence.*;

/**
 * Prerequisite A prerequisite is required for realizing one or more usecases.
 *
 * @author ffischer
 */
@Entity
@Table(name = "Prerequisite")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Prerequisite extends AbstractProjectbasedEntity implements Serializable {

	/**
	 * The affected usecases.
	 * <p>
	 * This is a Many-To-Many relationship.
	 */
	@ManyToMany

	private Collection<UseCase> affectedUsecases;

	/**
	 * Gets the affected usecases.
	 *
	 * @return the affected usecases
	 */

	public Collection<UseCase> getAffectedUsecases() {
		return affectedUsecases;
	}

	/**
	 * Sets the affected usecases.
	 *
	 * @param affectedUsecases the new affected usecases
	 */
	public void setAffectedUsecases(Collection<UseCase> affectedUsecases) {
		this.affectedUsecases = affectedUsecases;
	}

	/**
	 * Adds the affected usecase.
	 *
	 * @param u the u
	 */
	public void addAffectedUsecase(UseCase u) {
		if (null == affectedUsecases) {
			affectedUsecases = new TreeSet<UseCase>();

		}
		affectedUsecases.add(u);
	}
}
