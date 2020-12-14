/*
 * Iteration.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import de.te2m.project.service.core.entity.usecase.UseCase;
import javax.persistence.*;

/**
 * Iteration A iteration is a certain time box while realizinmg the project. The end point of a iteration is a target
 * milestone. In previous versions this object was named milestone but iteration is more correct.
 *
 * @author ffischer
 */
@Entity
@Table(name = "MILESTONE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Iteration extends AbstractProjectbasedEntity {

	/**
	 * Use serialVersionUID for interoperability.
	 */
	private final static long serialVersionUID = -4829327779052716065L;

	/**
	 * DOCUMENT ME!.
	 */
	@Temporal(TemporalType.DATE)
	Date endDate;

	/**
	 * DOCUMENT ME!.
	 */
	@Temporal(TemporalType.DATE)
	Date startDate;

	/**
	 * The state.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	TimeboxState state;

	/**
	 * DOCUMENT ME!.
	 */
	@OneToMany(mappedBy = "milestone", cascade = { CascadeType.ALL })

	Collection<UseCase> usecases;

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param endDate DOCUMENT ME!
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public TimeboxState getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(TimeboxState state) {
		this.state = state;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param scheduledDate DOCUMENT ME!
	 */
	public void setStartDate(Date scheduledDate) {
		this.startDate = scheduledDate;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param u DOCUMENT ME!
	 */
	public void addUsecase(UseCase u) {
		if (null == getUsecases()) {
			setUsecases(new HashSet<UseCase>());
		}

		if (getUsecases().contains(u)) {
			return;
		}

		getUsecases().add(u);
		u.setMilestone(this);
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */

	public Collection<UseCase> getUsecases() {
		return usecases;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param usecases DOCUMENT ME!
	 */
	public void setUsecases(Collection<UseCase> usecases) {
		this.usecases = usecases;
	}

	/**
	 * The Enum TimeboxState.
	 *
	 * @author ffischer
	 * @version 1.0
	 * @since 1.0
	 */
	public enum TimeboxState {

		/**
		 * The planned.
		 */
		PLANNED(false),

		/**
		 * The started.
		 */
		STARTED(true),

		/**
		 * The finished.
		 */
		FINISHED(false),

		/**
		 * The cancelled.
		 */
		CANCELLED(false);

		/**
		 * The running state.
		 */
		private final boolean runningState;

		/**
		 * Instantiates a new timebox state.
		 *
		 * @param isRunning the is running
		 */
		TimeboxState(boolean isRunning) {
			runningState = isRunning;
		}

		/**
		 * Checks if is running.
		 *
		 * @return true, if is running
		 */
		public boolean isRunning() {
			return runningState;
		}
	}
}
