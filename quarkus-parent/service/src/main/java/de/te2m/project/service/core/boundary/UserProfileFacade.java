/*
 * UserProfileFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import de.te2m.project.service.core.entity.user.UserProfile;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The Class UserProfileFacade.
 *
 * @author ffischer
 */
@ApplicationScoped
public class UserProfileFacade extends AbstractFacade<UserProfile> {

	/**
	 * DOCUMENT ME!.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new UserProfileFacade object.
	 */
	public UserProfileFacade() {
		super(UserProfile.class);
	}

	/* (non-Javadoc)
	 * @see de.te2m.AbstractFacade#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
