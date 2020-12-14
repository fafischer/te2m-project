/*
 * UseCaseRating.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

import java.io.Serializable;

import de.te2m.project.service.core.entity.AbstractEntity;
import de.te2m.project.service.core.entity.user.User;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * The Class UseCaseRating.
 *
 * <p>
 * This class is an persistent entity class. The table name is  UseCaseRating  *
 * </p>
 *
 * @author ffischer
 */
@Entity
class UseCaseRating extends AbstractEntity implements Serializable {

	/**
	 * The usecase.
	 */
	@ManyToOne
	UseCase usecase;

	/**
	 * The rating.
	 */
	Integer rating;

	/**
	 * The user.
	 */
	@ManyToOne
	User user;

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
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
