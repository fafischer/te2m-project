/*
 * UseCaseFacade.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.boundary;

import java.util.List;

import de.te2m.project.service.core.entity.project.Project;
import de.te2m.project.service.core.entity.usecase.UseCase;
import de.te2m.project.service.core.entity.usecase.UseCaseDependency;
import de.te2m.project.service.core.entity.usecase.UseCaseDependencyType;
import de.te2m.project.service.core.entity.usecase.UseCaseType;
import de.te2m.project.service.core.entity.user.User;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * The Class UseCaseFacade.
 *
 * @author ffischer
 */
@ApplicationScoped
public class UseCaseFacade extends AbstractFacade<UseCase> {

	/**
	 * The dependency facade.
	 */
	@Inject
	UseCaseDependencyFacade dependencyFacade;
	/**
	 * DOCUMENT ME!.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Creates a new UseCaseFacade object.
	 */
	public UseCaseFacade() {
		super(UseCase.class);
	}

	/* (non-Javadoc)
	 * @see de.te2m.core.boundary.AbstractFacade#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Gets the filtered list.
	 *
	 * @return the filtered list
	 */
	public List<Object> getFilteredList(Project project) {
		return getFilteredList(project, null);
	}

	/**
	 * Gets the epics.
	 *
	 * @param p the p
	 * @return the epics
	 */
	public List<UseCase> getEpics(Project p) {
		return em.createNamedQuery(UseCase.QUERY_EPICS_BY_PROJECT)
				.setParameter("project", p)
				.getResultList();
	}

	/**
	 * Gets the filtered list.
	 *
	 * @param p    the p
	 * @param type the type
	 * @return the filtered list
	 */
	public List<Object> getFilteredList(Project p, UseCaseType type) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		Root<UseCase> from = criteriaQuery.from(UseCase.class);
		CriteriaQuery<Object> select = criteriaQuery.select(from);
		Predicate predicate = criteriaBuilder.equal(from.get("project"), p);

		if (null != type) {
			Predicate predicate2 = criteriaBuilder.equal(from.get("usecaseType"), type);
			criteriaQuery.where(predicate, predicate2);
		} else {
			criteriaQuery.where(predicate);

		}
		TypedQuery<Object> typedQuery = em.createQuery(select);
		return typedQuery.getResultList();
	}

	/**
	 * Creates the dependency.
	 *
	 * @param srcid    the srcid
	 * @param targetid the targetid
	 * @param tt       the tt
	 */
	public void createDependency(User user, String srcid, String targetid, UseCaseDependencyType tt) {
		UseCase r = find(targetid);
		if (null == r) {
			return;
		}

		UseCase src = find(srcid);
		if (null == src) {
			return;
		}

		UseCaseDependency dep = new UseCaseDependency();
		dep.setSrc(src);
		dep.setType(tt);
		dep.setTarget(r);
		dep.setCreator(user);
		dep.setLastEditor(user);
		src.addRequiredUseCase(dep);
		r.addDependendUseCase(dep);
		dependencyFacade.create(dep);
		edit(src);
		edit(r);
	}
}
