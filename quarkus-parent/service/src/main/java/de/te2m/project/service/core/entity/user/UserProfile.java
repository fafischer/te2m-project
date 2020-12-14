/*
 * UserProfile.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.user;

import java.io.Serializable;
import java.util.Date;

import de.te2m.project.service.core.entity.AbstractEntity;
import de.te2m.project.service.core.entity.DataObject;
import javax.persistence.*;

/**
 * The Class UserProfile.
 *
 * <p>
 * This class is an persistent entity class. The table name is  PROFILE  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "PROFILE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserProfile extends AbstractEntity implements Serializable {

	/**
	 * DOCUMENT ME!.
	 */
	protected String companyName;

	/**
	 * DOCUMENT ME!.
	 */
	protected String firstName;

	/**
	 * DOCUMENT ME!.
	 */
	protected String middleName;

	/**
	 * DOCUMENT ME!.
	 */
	protected String name;

	/**
	 * DOCUMENT ME!.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	protected Address primaryAddress;
	/**
	 * The salutation.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	protected Salutation salutation;
	/**
	 * The profile image.
	 * <p>
	 * This is a One-To-One relationship.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	DataObject profileImage;
	/**
	 * DOCUMENT ME!.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	Date birthDate;

	/**
	 * DOCUMENT ME!.
	 */
	String email;

	/* (non-Javadoc)
	 * @see de.te2m.AbstractEntity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final UserProfile other = (UserProfile) obj;

		if ((this.name == null) ? (other.name != null)
				: (!this.name.equals(other.name)))
		{
			return false;
		}

		if ((this.firstName == null) ? (other.firstName != null)
				: (!this.firstName.equals(
				other.firstName)))
		{
			return false;
		}

		if ((this.middleName == null) ? (other.middleName != null)
				: (!this.middleName.equals(
				other.middleName)))
		{
			return false;
		}

		if ((this.companyName == null) ? (other.companyName != null)
				: (!this.companyName.equals(
				other.companyName)))
		{
			return false;
		}

		if (this.salutation != other.salutation) {
			return false;
		}

		if ((this.email == null) ? (other.email != null)
				: (!this.email.equals(other.email)))
		{
			return false;
		}

		return (this.birthDate == other.birthDate)
				|| ((this.birthDate != null)
				&& this.birthDate.equals(other.birthDate));
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param birthDate DOCUMENT ME!
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param companyName DOCUMENT ME!
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param email DOCUMENT ME!
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param firstName DOCUMENT ME!
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param middleName DOCUMENT ME!
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * .*
	 *
	 * @return .
	 */
	public String getName() {
		return name;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param name DOCUMENT ME!
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param primaryAddress DOCUMENT ME!
	 */
	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public Salutation getSalutation() {
		return salutation;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param salutation DOCUMENT ME!
	 */
	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

	/* (non-Javadoc)
	 * @see de.te2m.AbstractEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 5;
		hash = (17 * hash) + ((this.name != null) ? this.name.hashCode() : 0);
		hash = (17 * hash)
				+ ((this.firstName != null) ? this.firstName.hashCode() : 0);
		hash = (17 * hash)
				+ ((this.middleName != null) ? this.middleName.hashCode() : 0);
		hash = (17 * hash)
				+ ((this.companyName != null) ? this.companyName.hashCode() : 0);
		hash = (17 * hash)
				+ ((this.salutation != null) ? this.salutation.hashCode() : 0);
		hash = (17 * hash)
				+ ((this.email != null) ? this.email.hashCode() : 0);
		hash = (17 * hash)
				+ ((this.birthDate != null) ? this.birthDate.hashCode() : 0);

		return hash;
	}

	/**
	 * Gets the profile image.
	 *
	 * @return the profile image
	 */
	public DataObject getProfileImage() {
		return profileImage;
	}

	/**
	 * Sets the profile image.
	 *
	 * @param profileImage the new profile image
	 */
	public void setProfileImage(DataObject profileImage) {
		this.profileImage = profileImage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserProfile{" + "name=" + name + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", companyName=" + companyName
				+ ", salutation=" + salutation + '}';
	}

	/* (non-Javadoc)
	 * @see de.te2m.AbstractEntity#prepersist()
	 */
	@Override
	@PrePersist
	public void prepersist() {
		super.prepersist();
		if (null == salutation) {
			salutation = Salutation.UNKNOWN;
		}
	}

}
