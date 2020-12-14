/*
 * OrganizationFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import de.te2m.project.service.core.entity.user.Organization;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DOCUMENT ME!.
 *
 * @author ffischer
 */
@ApplicationScoped
public class OrganizationFacade extends AbstractFacade<Organization> {

	/**
	 * DOCUMENT ME!.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new PreferenceFacade object.
	 */
	public OrganizationFacade() {
		super(Organization.class);
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
