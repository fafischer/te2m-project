/*
 * UserFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import java.util.List;

import de.te2m.project.service.core.engine.EngineUtils;
import de.te2m.project.service.core.entity.user.User;
import de.te2m.project.service.core.entity.user.UserStatus;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The Class UserFacade.
 *
 * @author ffischer
 */
@ApplicationScoped
public class UserFacade extends AbstractFacade<User> {

	/**
	 * DOCUMENT ME!.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new UserFacade object.
	 */
	public UserFacade() {
		super(User.class);
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param login DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	@SuppressWarnings("unchecked")
	public User determineUserByLogin(String login) {
		//EntityManager em = emf.createEntityManager();
		List<User> users = em.createNamedQuery("findUserWithLogin")
				.setParameter("loginName", login).getResultList();

		if ((null == users) || (users.size() != 1)) {
			return null;
		}

		return users.get(0);
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param login DOCUMENT ME!
	 * @param pw    DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public User loginUser(String login, String pw) {
		User u = determineUserByLogin(login);

		if (null != u) {
			if ((null != pw) && pw.equals(u.getPassword())) {
				return u;
			}
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see de.te2m.AbstractFacade#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Creates the admin user.
	 *
	 * @return the user
	 */
	public User createAdminUser() {
		return createUser("admin", "temtools", "Admin", UserStatus.ACTIVE);
	}

	/**
	 * Creates the user.
	 *
	 * @param name        the name
	 * @param pw          the pw
	 * @param displayname the displayname
	 * @param status      the status
	 * @return the user
	 */
	public User createUser(String name, String pw, String displayname, UserStatus status) {
		if (null == name || null == pw) {
			return null;
		}
		if (null == status) {
			status = UserStatus.UNKNOWN;
		}
		User u = new User();
		if (null != displayname) {
			u.setDisplayName(displayname);
		} else {
			u.setDisplayName(name);
		}
		u.setLogin(name);
		u.setPassword(EngineUtils.createMD5Hash(pw));
		u.setStatus(status);
		create(u);
		return u;
	}
}
