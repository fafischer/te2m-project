/*
 * Address.java
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
import javax.persistence.*;

/**
 * The Class Address.
 *
 * <p>
 * This class is an persistent entity class. The table name is  ADDRESS  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "ADDRESS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Address extends AbstractEntity
		implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The address name.
	 */
	protected String addressName;

	/**
	 * DOCUMENT ME!.
	 */
	protected String city;

	/**
	 * DOCUMENT ME!.
	 */
	protected String companyName;

	/**
	 * DOCUMENT ME!.
	 */
	protected String country;

	/**
	 * DOCUMENT ME!.
	 */
	protected String firstname;

	/**
	 * DOCUMENT ME!.
	 */
	protected String middlename;

	/**
	 * DOCUMENT ME!.
	 */
	protected String name;

	/**
	 * DOCUMENT ME!.
	 */
	protected String postalCode;

	/**
	 * The salutation.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	protected Salutation salutation;

	/**
	 * DOCUMENT ME!.
	 */
	protected String state;

	/**
	 * DOCUMENT ME!.
	 */
	protected String street1;

	/**
	 * DOCUMENT ME!.
	 */
	protected String street2;

	/**
	 * The valid from.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	protected Date validFrom;

	/**
	 * The valid to.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	protected Date validTo;

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getAddressName() {
		return addressName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param addressName the new address name
	 */
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getCity() {
		return city;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
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
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param firstname the new firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getMiddlename() {
		return middlename;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param middlename the new middlename
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getName() {
		return name;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param zip the new postal code
	 */
	public void setPostalCode(String zip) {
		this.postalCode = zip;
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
	 * @param salutation the new salutation
	 */
	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getState() {
		return state;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getStreet1() {
		return street1;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param street1 the new street1
	 */
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public String getStreet2() {
		return street2;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param street2 the new street2
	 */
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public Date getValidFrom() {
		return validFrom;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param validFrom the new valid from
	 */
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @return DOCUMENT ME!
	 */
	public Date getValidTo() {
		return validTo;
	}

	/**
	 * DOCUMENT ME!.
	 *
	 * @param validTo the new valid to
	 */
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
}
