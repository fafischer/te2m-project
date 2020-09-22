/*
 * Task.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.task;

import java.io.Serializable;
import java.math.BigDecimal;

import de.te2m.project.service.core.entity.AbstractNamedEntity;
import de.te2m.project.service.core.entity.Skill;
import de.te2m.project.service.core.entity.usecase.UseCase;
import de.te2m.project.service.core.entity.user.User;
import javax.persistence.*;

/**
 * The Class Task.
 *
 * <p>
 * This class is an persistent entity class. The table name is  Task  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "Task")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
		@NamedQuery(
				name = Task.QUERY_BY_ASSIGNEE,
				query = "select t from Task t where t.taskAssignee = :user")
})

public class Task extends AbstractNamedEntity implements Serializable {

	/**
	 * The Constant QUERY_BY_ASSIGNEE.
	 */
	public static final String QUERY_BY_ASSIGNEE = "getAllTasksForUser";

	/**
	 * The estimation.
	 */
	@Column(scale = 2, precision = 4)
	BigDecimal estimation;

	/**
	 * The difficulty of the usecase.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	TaskState taskState;

	/**
	 * The task assignee.
	 * <p>
	 * This is a Many-To-One relationship.
	 */

	@ManyToOne
	User taskAssignee;

	/**
	 * The usecase.
	 * <p>
	 * This is a Many-To-One relationship.
	 */

	@ManyToOne
	UseCase usecase;

	/**
	 * The required skill.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne
	Skill requiredSkill;

	/**
	 * Gets the estimation.
	 *
	 * @return the estimation
	 */
	public BigDecimal getEstimation() {
		return estimation;
	}

	/**
	 * Sets the estimation.
	 *
	 * @param estimation the new estimation
	 */
	public void setEstimation(BigDecimal estimation) {
		this.estimation = estimation;
	}

	/**
	 * Gets the required skill.
	 *
	 * @return the required skill
	 */

	public Skill getRequiredSkill() {
		return requiredSkill;
	}

	/**
	 * Sets the required skill.
	 *
	 * @param requiredSkill the new required skill
	 */
	public void setRequiredSkill(Skill requiredSkill) {
		this.requiredSkill = requiredSkill;
	}

	/**
	 * Gets the usecase.
	 *
	 * @return the usecase
	 */

	public UseCase getUsecase() {
		return usecase;
	}

	/**
	 * Sets the usecase.
	 *
	 * @param usecase the new usecase
	 */
	public void setUsecase(UseCase usecase) {
		this.usecase = usecase;
	}

	/**
	 * Gets the task state.
	 *
	 * @return the task state
	 */
	public TaskState getTaskState() {
		return taskState;
	}

	/**
	 * Sets the task state.
	 *
	 * @param taskState the new task state
	 */
	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}

	/**
	 * Gets the task assignee.
	 *
	 * @return the task assignee
	 */

	public User getTaskAssignee() {
		return taskAssignee;
	}

	/**
	 * Sets the task assignee.
	 *
	 * @param taskAssignee the new task assignee
	 */
	public void setTaskAssignee(User taskAssignee) {
		this.taskAssignee = taskAssignee;
	}
}
