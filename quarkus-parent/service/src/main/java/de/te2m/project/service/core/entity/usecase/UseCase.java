/*
 * UseCase.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import de.te2m.project.service.core.entity.*;
import de.te2m.project.service.core.entity.risk.Risk;
import de.te2m.project.service.core.entity.task.Task;
import de.te2m.project.service.core.entity.user.User;
import javax.persistence.*;

/**
 * Use case A use case is the persistent pepresentation of several similar objects: <ul> <li>Usecases: A usecase (SCRUM:
 * User-Story) is a requirement. Beside common requirements there are epics. A epic is too large to be implemented in a
 * single time box and therefore split into several child use case.</li> <li>Epics: Epics are special usecases. </li>
 * <li>Personas: Personas are the different actors in a system like user, adminstrator,...</li> <li>Nonfunctional
 * requirements</li> </ul>
 *
 * @author ffischer
 */
@Entity
@Table(name = "USECASE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
		@NamedQuery(
				name = UseCase.QUERY_BY_PROJECT,
				query = "select u from UseCase u where u.project = :project"),
		@NamedQuery(
				name = UseCase.QUERY_EPICS_BY_PROJECT,
				query = "select u from UseCase u where u.epic='Y' and u.project = :project")
})

public class UseCase extends AbstractProjectbasedEntity implements Serializable {

	/**
	 * The Constant QUERY_BY_PROJECT.
	 */
	public static final String QUERY_BY_PROJECT = "getUsecaseByProject";
	/**
	 * The Constant QUERY_EPICS_BY_PROJECT.
	 */
	public static final String QUERY_EPICS_BY_PROJECT = "getEpicsByProject";
	/**
	 * The external ref id.
	 */
	String externalRefID;
	/**
	 * The state of the usecase.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	UseCaseState usecaseState;
	/**
	 * The priority of the usecase.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	UseCasePriority priority;
	/**
	 * The difficulty of the usecase.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	UseCaseDifficulty difficulty;
	/**
	 * The type of the usecase.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	UseCaseType usecaseType;
	/**
	 * The type of the usecase.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	UseCaseSubType usecaseSubType;
	/**
	 * The requestor of the usecase The requestor is the direct reposible person for the reqirements of this usecase
	 * <br>.
	 */
	@ManyToOne
	@JoinColumn(nullable = true)
	User requestor;
	/**
	 * The approver of the usecase.
	 */
	@ManyToOne
	@JoinColumn(nullable = true)
	User approver;
	/**
	 * The milestone.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne
	@JoinColumn(nullable = true)

	Iteration milestone;
	/**
	 * True if the UseCase is an epic.
	 */
	@Basic
	private Character epic;
	//@ManyToMany(mappedBy="affectedUsecases")
	/**
	 * The risks.
	 * <p>
	 * This is a Many-To-Many relationship.
	 */
	@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinTable(name = "USECASE_RISK", joinColumns = {
			@JoinColumn(name = "RISKID", nullable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "USECASEID", nullable = false) })

	private Collection<Risk> risks;
	/**
	 * The closing criterias.
	 * <p>
	 * This is a Many-To-Many relationship.
	 */
	@ManyToMany(mappedBy = "affectedUsecases")

	private Collection<ClosingCriteria> closingCriterias;
	/**
	 * The prerequisites.
	 * <p>
	 * This is a Many-To-Many relationship.
	 */
	@ManyToMany(mappedBy = "affectedUsecases")

	private Collection<Prerequisite> prerequisites;
	/**
	 * The required use cases.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany(mappedBy = "src", cascade = CascadeType.REMOVE)
	private Collection<UseCaseDependency> requiredUseCases;
	/**
	 * The dependend use cases.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany(mappedBy = "target")
	private Collection<UseCaseDependency> dependendUseCases;
	/**
	 * The ratings.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany(mappedBy = "usecase", cascade = CascadeType.ALL)
	private Collection<UseCaseRating> ratings;
	/**
	 * The tasks for this usecase.
	 */
	@OneToMany(mappedBy = "usecase", cascade = CascadeType.PERSIST)
	private Collection<Task> tasks;

	/**
	 * Gets the risks.
	 *
	 * @return the risks
	 */

	public Collection<Risk> getRisks() {
		if (null == risks) {
			return Collections.EMPTY_LIST;
		}
		return risks;
	}

	/**
	 * Sets the risks.
	 *
	 * @param risks the new risks
	 */
	public void setRisks(Collection<Risk> risks) {
		this.risks = risks;
	}

	/**
	 * Gets the requestor.
	 *
	 * @return the requestor
	 */
	public User getRequestor() {
		return requestor;
	}

	/**
	 * Sets the requestor.
	 *
	 * @param requestor the new requestor
	 */
	public void setRequestor(User requestor) {
		this.requestor = requestor;
	}

	/**
	 * Returns the Approver.
	 *
	 * @return the approver
	 */
	public User getApprover() {
		return approver;
	}

	/**
	 * Set the Approver.
	 *
	 * @param approver the new approver
	 */
	public void setApprover(User approver) {
		this.approver = approver;
	}

	/**
	 * Returns the difficulty of this UseCase.
	 *
	 * @return the difficulty
	 */
	public UseCaseDifficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Sets the difficulty of this UseCase.
	 *
	 * @param difficulty the new difficulty
	 */
	public void setDifficulty(UseCaseDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Returns the target milestone A UseCase can only belong to one Milestone (Branching and merging is not
	 * supported).
	 *
	 * @return the milestone
	 */

	public Iteration getMilestone() {
		return milestone;
	}

	/**
	 * Sets the target milestone for this UseCase A UseCase can only belong to one Milestone (Branching and merging is
	 * not supported).
	 *
	 * @param milestone the new milestone
	 */
	public void setMilestone(Iteration milestone) {
		this.milestone = milestone;
	}

	/**
	 * Returns the priority of the UseCase.
	 *
	 * @return the priority
	 */
	public UseCasePriority getPriority() {
		return priority;
	}

	/**
	 * Sets the priority of the UseCase.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(UseCasePriority priority) {
		this.priority = priority;
	}

	/**
	 * Returns the state of the UseCase.
	 *
	 * @return the usecase state
	 */
	public UseCaseState getUsecaseState() {
		return usecaseState;
	}

	/**
	 * Sets the state of the UseCase.
	 *
	 * @param usecaseState the new usecase state
	 */
	public void setUsecaseState(UseCaseState usecaseState) {
		this.usecaseState = usecaseState;
	}

	/**
	 * Returns the external refernce ID This ID is the ID of this usecase used in external documents.
	 *
	 * @return the external ref id
	 */
	public String getExternalRefID() {
		return externalRefID;
	}

	/**
	 * Sets the external reference ID This ID is the ID of this usecase used in external documents.
	 *
	 * @param externalRefID the new external ref id
	 */
	public void setExternalRefID(String externalRefID) {
		this.externalRefID = externalRefID;
	}

	/* (non-Javadoc)
	 * @see de.te2m.core.entity.AbstractEntity#prepersist()
	 */
	@Override
	public void prepersist() {
		super.prepersist();

		if (null == usecaseState) {
			usecaseState = UseCaseState.NEW;
		}

		if (null == difficulty) {
			difficulty = UseCaseDifficulty.MEDIUM;
		}

		if (null == priority) {
			priority = UseCasePriority.SHOULD;
		}
	}

	/**
	 * Returns the state of the UseCase.
	 *
	 * @return the state
	 */
	public UseCaseState getState() {
		return usecaseState;
	}

	/**
	 * Sets the state of the UseCase.
	 *
	 * @param state the new state
	 */
	public void setState(UseCaseState state) {
		this.usecaseState = state;
	}

	/**
	 * Retuns th enumber of the required UseCases.
	 *
	 * @return the required use case count
	 */
	public int getRequiredUseCaseCount() {
		if (null == requiredUseCases) {
			return 0;
		}
		return requiredUseCases.size();
		//throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Adds a required UseCase.
	 *
	 * @param uc the uc
	 */
	public void addRequiredUseCase(UseCaseDependency uc) {
		if (null == uc) {
			return;
		}
		if (null == requiredUseCases) {
			requiredUseCases = new HashSet<UseCaseDependency>();
		}
		if (requiredUseCases.contains(uc)) {
			return;
		}
		requiredUseCases.add(uc);

	}

	/**
	 * Returns the required UseCases.
	 *
	 * @return the required use cases
	 */
	public Collection<UseCaseDependency> getRequiredUseCases() {
		if (null == requiredUseCases) {
			requiredUseCases = new HashSet<UseCaseDependency>();
		}
		return requiredUseCases;
		//throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Sets the required UseCases.
	 *
	 * @param ucs the new required use cases
	 */
	public void setRequiredUseCases(Collection<UseCaseDependency> ucs) {
		this.requiredUseCases = ucs;
	}

	/**
	 * Retuns the number of the dependend UseCases.
	 *
	 * @return the dependend use case count
	 */
	public int getDependendUseCaseCount() {
		return getDependendUseCases().size();
		//throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Add a dependend UseCase.
	 *
	 * @param uc the uc
	 */
	public void addDependendUseCase(UseCaseDependency uc) {
		if (null == uc) {
			return;
		}
		if (null == dependendUseCases) {
			dependendUseCases = new HashSet<UseCaseDependency>();
		}
		if (dependendUseCases.contains(uc)) {
			return;
		}
		dependendUseCases.add(uc);
	}

	/**
	 * Returns all UseCases depending on the current one.
	 *
	 * @return the dependend use cases
	 */
	public Collection<UseCaseDependency> getDependendUseCases() {
		if (null == dependendUseCases) {
			dependendUseCases = new HashSet<UseCaseDependency>();
		}
		return dependendUseCases;
		//throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Sets the dependen UseCases.
	 *
	 * @param ucs the new dependend use cases
	 */
	public void setDependendUseCases(Collection<UseCaseDependency> ucs) {
		this.dependendUseCases = ucs;
	}

	/**
	 * Add a Risk.
	 *
	 * @param r the r
	 */
	public void addRisk(Risk r) {

		if (null == risks) {
			risks = new HashSet<Risk>();
		}
		if (null == r || risks.contains(r)) {
			return;
		}
		risks.add(r);
	}

	/**
	 * Add a ClosingCriteria.
	 *
	 * @param r the r
	 */
	public void addClosingCriteria(ClosingCriteria r) {
		if (null == closingCriterias) {
			closingCriterias = new HashSet<ClosingCriteria>();
		}
		if (null == r || closingCriterias.contains(r)) {
			return;
		}
		closingCriterias.add(r);
	}

	/**
	 * Returns all ClosingCriterias.
	 *
	 * @return the closing criterias
	 */

	public Collection<ClosingCriteria> getClosingCriterias() {
		if (null == closingCriterias) {
			closingCriterias = new HashSet<ClosingCriteria>();
		}
		return closingCriterias;
	}

	/**
	 * Sets the ClosingCriterias.
	 *
	 * @param closingCriterias the new closing criterias
	 */
	public void setClosingCriterias(Collection<ClosingCriteria> closingCriterias) {
		this.closingCriterias = closingCriterias;
	}

	/**
	 * Adds a Prerequisite.
	 *
	 * @param r the r
	 */
	public void addPrerequisite(Prerequisite r) {

		if (null == r) {
			return;
		}
		if (null == prerequisites) {
			prerequisites = new HashSet<Prerequisite>();
		}
		if (prerequisites.contains(r)) {
			return;
		}
		prerequisites.add(r);
	}

	/**
	 * Returns all Prerequisites.
	 *
	 * @return the prerequisites
	 */

	public Collection<Prerequisite> getPrerequisites() {
		if (null == prerequisites) {
			prerequisites = new HashSet<Prerequisite>();
		}
		return prerequisites;
	}

	/**
	 * Sets the Prerequisites.
	 *
	 * @param prerequisites the new prerequisites
	 */
	public void setPrerequisites(Collection<Prerequisite> prerequisites) {
		this.prerequisites = prerequisites;
	}

	/**
	 * Returns all Tasks.
	 *
	 * @return the tasks
	 */
	public Collection<Task> getTasks() {
		if (null == tasks) {
			tasks = new HashSet<Task>();
		}
		return tasks;
	}

	/**
	 * Set the Tasks.
	 *
	 * @param tasks the new tasks
	 */
	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Add a new task.
	 *
	 * @param t the t
	 */
	public void addTask(Task t) {
		if (null == t) {
			return;
		}
		if (null == tasks) {
			tasks = new HashSet<Task>();
		}
		if (tasks.contains(t)) {
			return;
		}
		tasks.add(t);

	}

	/**
	 * Returns the estimation of all getEpic()Tesks identified for this UseCase.
	 *
	 * @return the estimation
	 */
	public BigDecimal getEstimation() {
		Iterator<Task> ti = getTasks().iterator();

		BigDecimal est = BigDecimal.ZERO;
		while (ti.hasNext()) {
			Task task = ti.next();
			if (null != task && null != task.getEstimation()) {
				est = est.add(task.getEstimation());
			}

		}
		return est;
	}

	/**
	 * Returns a collection of all skills required for implementing this usecase. Internally all assigned Tasks will be
	 * analyzed and a collection of all requested skills will be returned.
	 *
	 * @return the required skills
	 */
	public Collection<Skill> getRequiredSkills() {
		// TODO Implement
		return null;
	}

	/**
	 * Gets the usecase type.
	 *
	 * @return the usecase type
	 */
	public UseCaseType getUsecaseType() {
		return usecaseType;
	}

	/**
	 * Sets the usecase type.
	 *
	 * @param usecaseType the new usecase type
	 */
	public void setUsecaseType(UseCaseType usecaseType) {
		this.usecaseType = usecaseType;
	}

	/**
	 * Gets the epic.
	 *
	 * @return the epic
	 */
	public Boolean getEpic() {
		if (epic == null) {
			return Boolean.FALSE;
		}
		return epic == 'Y' ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Sets the epic.
	 *
	 * @param isEpic the new epic
	 */
	public void setEpic(Boolean isEpic) {
		if (isEpic == null) {
			this.epic = null;
		} else {
			this.epic = isEpic == true ? 'Y' : 'N';
		}
	}

	/**
	 * Checks if is epic.
	 *
	 * @return the boolean
	 */
	public Boolean isEpic() {
		return getEpic();
	}

	/**
	 * Checks if is part of epic.
	 *
	 * @return true, if is part of epic
	 */
	public boolean isPartOfEpic() {
		Collection<UseCaseDependency> deps = getDependendUseCases();
		for (UseCaseDependency dep : deps) {
			if (UseCaseDependencyType.IS_CHILD == dep.getType()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Integer getRating() {
		if (null == ratings || ratings.isEmpty()) {
			return 0;
		}
		long l = 0;
		for (UseCaseRating rating : ratings) {
			l = l + rating.getRating();
		}
		return Math.round(l / ratings.size());
	}

	/**
	 * Gets the user rating.
	 *
	 * @param u the u
	 * @return the user rating
	 */
	public Integer getUserRating(User u) {
		if (null == ratings || ratings.isEmpty()) {
			return 0;
		}

		if (u == null || null == u.getId()) {
			return 0;
		}

		for (UseCaseRating rating : ratings) {
			if (null != rating.getUser() && u.getId().equals(rating.getUser().getId())) {
				return rating.getRating();
			}
		}
		return 0;
	}

	/**
	 * Adds the rating.
	 *
	 * @param u      the u
	 * @param rating the rating
	 */
	public void addRating(User u, int rating) {
		UseCaseRating r = new UseCaseRating();
		r.setUsecase(this);
		r.setUser(u);
		r.setRating(rating);
		if (null == ratings) {
			ratings = new ArrayList<UseCaseRating>();
		}
		ratings.add(r);
	}

}
