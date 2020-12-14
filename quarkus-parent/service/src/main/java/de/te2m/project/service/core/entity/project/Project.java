/*
 * Project.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.project;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

import de.te2m.project.service.core.entity.*;
import de.te2m.project.service.core.entity.risk.Risk;
import de.te2m.project.service.core.entity.usecase.UseCase;
import de.te2m.project.service.core.entity.user.Organization;
import javax.persistence.*;

/**
 * The main object of temtools4projects.
 *
 * @author ffischer
 */
@Entity
@Table(name = "PROJECT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Project extends AbstractNamedEntity
		implements Serializable {

	/**
	 * Use serialVersionUID for interoperability.
	 */
	private final static long serialVersionUID = -4596718168081512529L;
	/**
	 * Milestones of the project.
	 */
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)

	Collection<Iteration> milestones;
	/**
	 * The config.
	 * <p>
	 * This is a One-To-One relationship.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	ProjectConfiguration config;
	/**
	 * Usecases associated to this project.
	 */
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)

	Collection<UseCase> usecases;
	/**
	 * Risks identified for this project.
	 */
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)

	Collection<Risk> risks;
	/**
	 * Prerequisites required by this project.
	 */
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)

	Collection<Prerequisite> prerequisites;
	/**
	 * ClosingCriterias used in this project.
	 */
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)

	Collection<ClosingCriteria> closingCriterias;
	/**
	 * The available skills.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)

	Collection<Skill> availableSkills;
	/**
	 * The creator.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne(cascade = CascadeType.REMOVE)

	Organization creator;
	/**
	 * The customer.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne(cascade = CascadeType.REMOVE)

	Organization customer;
	/**
	 * The project locale. All inputs should be made by using this locale. The locale will be also used for filtering
	 * the available reports.
	 */
	String projectLocale;
	@Basic
	private Character locked;

	/**
	 * Get the project locale.
	 *
	 * @return the project locale
	 */
	public String getProjectLocale() {
		return projectLocale;
	}

	/**
	 * Set the project locale.
	 *
	 * @param projectLocale the new project locale
	 */
	public void setProjectLocale(String projectLocale) {
		this.projectLocale = projectLocale;
	}

	/**
	 * Add a risk.
	 *
	 * @param m The new risk
	 */
	public void addRisks(Risk m) {
		if (null == risks) {
			risks = new TreeSet<Risk>();
		}

		risks.add(m);

		m.setProject(this);
	}

	/**
	 * Add a closing criteria.
	 *
	 * @param m The new closing criteria
	 */
	public void addClosingCriteria(ClosingCriteria m) {
		if (null == closingCriterias) {
			closingCriterias = new TreeSet<ClosingCriteria>();
		}

		closingCriterias.add(m);

		m.setProject(this);
	}

	/**
	 * Gets the available skills.
	 *
	 * @return the available skills
	 */

	public Collection<Skill> getAvailableSkills() {
		return availableSkills;
	}

	/**
	 * Sets the available skills.
	 *
	 * @param availableSkills the new available skills
	 */
	public void setAvailableSkills(Collection<Skill> availableSkills) {
		this.availableSkills = availableSkills;
	}

	/**
	 * Gets the closing criterias.
	 *
	 * @return the closing criterias
	 */

	public Collection<ClosingCriteria> getClosingCriterias() {
		return closingCriterias;
	}

	/**
	 * Sets the closing criterias.
	 *
	 * @param closingCriterias the new closing criterias
	 */
	public void setClosingCriterias(Collection<ClosingCriteria> closingCriterias) {
		this.closingCriterias = closingCriterias;
	}

	/**
	 * Add a prerequisite.
	 *
	 * @param m DOCUMENT ME!
	 */
	public void addPrerequisite(Prerequisite m) {
		if (null == prerequisites) {
			prerequisites = new TreeSet<Prerequisite>();
		}

		prerequisites.add(m);

		m.setProject(this);
	}

	/**
	 * Get the prerequisites.
	 *
	 * @return the prerequisites
	 */

	public Collection<Prerequisite> getPrerequisites() {
		return prerequisites;
	}

	/**
	 * Sets the prerequisites.
	 *
	 * @param prerequisites the new prerequisites
	 */
	public void setPrerequisites(Collection<Prerequisite> prerequisites) {
		this.prerequisites = prerequisites;
	}

	/**
	 * Add a milestone.
	 *
	 * @param m A new milestone
	 */
	public void addMilestone(Iteration m) {
		if (null == milestones) {
			milestones = new TreeSet<Iteration>();
		}

		milestones.add(m);

		m.setProject(this);
	}

	/**
	 * Add a usecase.
	 *
	 * @param u The new usecase
	 */
	public void addUsecase(UseCase u) {
		if (null == getUsecases()) {
			setUsecases(new HashSet<UseCase>());
		}

		if (getUsecases().contains(u)) {
			return;
		}

		getUsecases().add(u);

		u.setProject(this);
	}

	/**
	 * Get all milestones!.
	 *
	 * @return The milestones
	 */

	public Collection<Iteration> getMilestones() {
		return milestones;
	}

	/**
	 * Set the milestones.
	 *
	 * @param milestones the milestones
	 */
	public void setMilestones(Collection<Iteration> milestones) {
		this.milestones = milestones;
	}

	/**
	 * Get all usecases.
	 *
	 * @return The usecases
	 */

	public Collection<UseCase> getUsecases() {
		return usecases;
	}

	/**
	 * Set the usecases.
	 *
	 * @param usecases the usecases
	 */
	public void setUsecases(Collection<UseCase> usecases) {
		this.usecases = usecases;
	}

	/**
	 * Get all risks.
	 *
	 * @return The risks
	 */

	public Collection<Risk> getRisks() {
		return risks;
	}

	/**
	 * Set risks.
	 *
	 * @param risks The risks
	 */
	public void setRisks(Collection<Risk> risks) {
		this.risks = risks;
	}

	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	public ProjectConfiguration getConfig() {
		if (null == config) {
			config = new ProjectConfiguration();
		}
		return config;
	}

	/**
	 * Sets the config.
	 *
	 * @param config the new config
	 */
	public void setConfig(ProjectConfiguration config) {
		this.config = config;
	}

	public Boolean getLocked() {
		if (locked == null) {
			return Boolean.FALSE;
		}
		return locked == 'Y' ? Boolean.TRUE : Boolean.FALSE;
	}

	public void setLocked(Boolean isLocked) {
		if (locked == null) {
			this.locked = null;
		} else {
			this.locked = isLocked == true ? 'Y' : 'N';
		}
	}

	public Boolean isLocked() {
		return getLocked();
	}
}
