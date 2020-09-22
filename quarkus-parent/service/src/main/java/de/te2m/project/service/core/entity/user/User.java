/*
 * User.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import de.te2m.project.service.core.entity.AbstractNamedEntity;
import de.te2m.project.service.core.entity.BooleanAttribute;
import de.te2m.project.service.core.entity.text.Text;
import javax.persistence.*;

/**
 * The Class User.
 *
 * <p>
 * This class is an persistent entity class. The table name is  USRDATA  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "USRDATA")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends AbstractNamedEntity implements Serializable {

	/**
	 * DOCUMENT ME!.
	 */
	public static final String ANONYMOUS_USER_ID = "anonymous";

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The display name.
	 */
	protected String displayName;

	/**
	 * The last login date.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastLoginDate;

	/**
	 * The logged in.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	protected BooleanAttribute loggedIn;

	/**
	 * The login.
	 */
	@Column(nullable = false)
	protected String login;

	/**
	 * The notes.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany(mappedBy = "author")
	protected Collection<Text> notes;

	/**
	 * The password.
	 */
	@Column(nullable = false)
	protected String password;

	/**
	 * The organization.
	 * <p>
	 * This is a Many-To-One relationship.
	 */
	@ManyToOne
	protected Organization organization;

	/**
	 * The roles.
	 * <p>
	 * This is a One-To-Many relationship.
	 */
	@OneToMany
	@JoinTable(name = "USER_ROLE_ASSIGNEMENT", joinColumns =
	@JoinColumn(name = "USER_ID"), inverseJoinColumns =
	@JoinColumn(name = "ROLE_ID"))
	protected Collection<Role> roles;

	/**
	 * The status.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	protected UserStatus status;

	/**
	 * DOCUMENT ME!.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private UserProfile profile;

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
		roles = new ArrayList<Role>();
		profile = new UserProfile();
		status = UserStatus.UNKNOWN;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param role DOCUMENT ME!
	 */
	public void addRole(Role role) {
		if (null == roles) {
			roles = new ArrayList<Role>();
		}

		if (!roles.contains(role)) {
			roles.add(role);
		}
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param displayName DOCUMENT ME!
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param lastLoginDate DOCUMENT ME!
	 */
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param login DOCUMENT ME!
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param password DOCUMENT ME!
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public UserProfile getProfile() {
		if (null == profile) {
			profile = new UserProfile();
		}
		return profile;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param profile DOCUMENT ME!
	 */
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param newRoles DOCUMENT ME!
	 */
	public void setRoles(Collection<Role> newRoles) {
		roles = newRoles;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param status DOCUMENT ME!
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public boolean isLoggedIn() {
		return loggedIn == BooleanAttribute.TRUE;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param newloggedIn the new logged in
	 */
	public void setLoggedIn(boolean newloggedIn) {
		if (newloggedIn) {
			this.loggedIn = BooleanAttribute.TRUE;

		} else {
			this.loggedIn = BooleanAttribute.FALSE;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getId() + ":[" + login + "] " + password;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public Collection<Text> getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(Collection<Text> notes) {
		this.notes = notes;
	}

	/**
	 * Gets the organization.
	 *
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * Sets the organization.
	 *
	 * @param organization the new organization
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
