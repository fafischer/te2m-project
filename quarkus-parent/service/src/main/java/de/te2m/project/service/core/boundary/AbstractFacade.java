/*
 * AbstractFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * DOCUMENT ME!.
 *
 * @param <T> DOCUMENT ME!
 * @author ffischer
 */
public abstract class AbstractFacade<T> {

	/**
	 * DOCUMENT ME!.
	 */
	private final Class<T> entityClass;

	/**
	 * Creates a new AbstractFacade object.
	 *
	 * @param entityClass DOCUMENT ME!
	 */
	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public int count() {

		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));

		javax.persistence.Query q = getEntityManager().createQuery(cq);

		return ((Long) q.getSingleResult()).intValue();
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param entity DOCUMENT ME!
	 */
	@Transactional
	public void create(T entity) {
		getEntityManager().persist(entity);

		//getEntityManager().flush();
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param entity DOCUMENT ME!
	 */
	@Transactional
	public void edit(T entity) {
		getEntityManager().merge(entity);
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param id DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public T find(Object id) {
		if (null == id) {
			return null;
		}

		return getEntityManager().find(entityClass, id);
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public List<T> findAll() {

		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));

		//return getEntityManager().createQuery(cq).getResultList();
		return getEntityManager().createQuery(cq).getResultList();
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param range DOCUMENT ME!
	 * @return DOCUMENT ME!
	 */
	public List<T> findRange(int[] range) {

		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));

		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);

		return q.getResultList();
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param entity DOCUMENT ME!
	 */
	public void remove(T entity) {

		getEntityManager().remove(getEntityManager().merge(entity));
	}

	/**
	 * Refresh.
	 *
	 * @param entity the entity
	 */
	public void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	protected abstract EntityManager getEntityManager();
}
