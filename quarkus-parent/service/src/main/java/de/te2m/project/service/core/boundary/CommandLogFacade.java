/*
 * DataObjectFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import de.te2m.project.service.core.entity.CommandLog;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author ffischer
 */
@ApplicationScoped
public class CommandLogFacade extends AbstractFacade<CommandLog> {

	@PersistenceContext
	private EntityManager em;

	public CommandLogFacade() {
		super(CommandLog.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public boolean isCommandNotProcessedYet(String messageID) {
		return em.createNamedQuery(CommandLog.QUERY_BY_MESSAGE_ID)
				.setParameter("messageID", messageID)
				.getResultList().isEmpty();
	}

	public boolean isCommandAlreadyProcessed(String messageID) {
		return !isCommandNotProcessedYet(messageID);
	}
}
