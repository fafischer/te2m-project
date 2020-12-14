/*
 * RoleFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import de.te2m.project.service.core.entity.user.Role;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The Class RoleFacade.
 *
 * @author ffischer
 */
@ApplicationScoped
public class RoleFacade extends AbstractFacade<Role> {

	/**
	 * DOCUMENT ME!.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new RoleFacade object.
	 */
	public RoleFacade() {
		super(Role.class);
	}

	/* (non-Javadoc)
	 * @see de.te2m.AbstractFacade#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
