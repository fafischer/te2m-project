/*
 * Risk.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.risk;

import java.util.Collection;
import java.util.HashSet;

import de.te2m.project.service.core.entity.AbstractProjectbasedEntity;
import de.te2m.project.service.core.entity.usecase.UseCase;
import javax.persistence.*;

/**
 * The Class Risk.
 *
 * <p>
 * This class is an persistent entity class. The table name is  Risk  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "Risk")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Risk extends AbstractProjectbasedEntity {

	/**
	 * The severity.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Severity severity;
	/**
	 * The severity.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	Likelyhood likelyhood;
	/**
	 * The affected usecases.
	 * <p>
	 * This is a Many-To-Many relationship.
	 */
	@ManyToMany(mappedBy = "risks", cascade = { CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.REMOVE })
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
		//u.addRisk(this);
		if (null == affectedUsecases) {
			affectedUsecases = new HashSet<UseCase>();

		}

		if (affectedUsecases.contains(u)) {
			return;
		}

		u.addRisk(this);
		//affectedUsecases.add(u);
	}

	public Severity getSeverity() {
		return severity;
	}

	/**
	 * Sets the risk severity.
	 *
	 * @param severity the new risk severity
	 */
	public Risk setSeverity(Severity severity) {
		this.severity = severity;
		return this;
	}

	public Likelyhood getLikelyhood() {
		return likelyhood;
	}

	public Risk setLikelyhood(Likelyhood likelyhood) {
		this.likelyhood = likelyhood;
		return this;
	}
}
