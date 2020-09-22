/*
 * TaskFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import java.util.List;

import de.te2m.project.service.core.entity.task.Task;
import de.te2m.project.service.core.entity.user.User;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The Class TaskFacade.
 *
 * @author ffischer
 */
@ApplicationScoped
public class TaskFacade extends AbstractFacade<Task> {

	/**
	 * DOCUMENT ME!.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new MilestoneFacade object.
	 */
	public TaskFacade() {
		super(Task.class);
	}

	/* (non-Javadoc)
	 * @see de.te2m.core.boundary.AbstractFacade#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Gets the tasks for user.
	 *
	 * @param p the p
	 * @return the tasks for user
	 */
	public List<Task> getTasksForUser(User p) {

		List<Task> tasks = em.createNamedQuery(Task.QUERY_BY_ASSIGNEE)
				.setParameter("user", p)
				.getResultList();

		return tasks;
	}
}
