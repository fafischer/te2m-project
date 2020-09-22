/*
 * ProjectConfiguration.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.project;

import java.io.Serializable;

import de.te2m.project.service.core.entity.AbstractNamedEntity;
import de.te2m.project.service.core.entity.UseCaseDeterminePriorityProcessType;
import de.te2m.project.service.core.entity.UseCaseEstimationProcessType;
import de.te2m.project.service.core.entity.UseCaseEstimationUnitType;
import javax.persistence.*;

/**
 * The Class ProjectConfiguration.
 *
 * <p>
 * This class is an persistent entity class. The table name is  PROJECTCFG  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "PROJECTCFG")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ProjectConfiguration extends AbstractNamedEntity implements Serializable {

	/**
	 * The estimation process used for the project.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	UseCaseEstimationProcessType estimationType;

	/**
	 * The process used for determining the priority of use cases.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	UseCaseDeterminePriorityProcessType priorizationType;

	/**
	 * The estimation unit used for the project.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	UseCaseEstimationUnitType estimationUnit;

	/**
	 * The type of the project.
	 */
	@Basic
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	ProjectType type;

	/**
	 * Gets the estimation type.
	 *
	 * @return the estimation type
	 */
	public UseCaseEstimationProcessType getEstimationType() {
		return estimationType;
	}

	/**
	 * Sets the estimation type.
	 *
	 * @param estimationType the new estimation type
	 */
	public void setEstimationType(UseCaseEstimationProcessType estimationType) {
		this.estimationType = estimationType;
	}

	/**
	 * Gets the estimation unit.
	 *
	 * @return the estimation unit
	 */
	public UseCaseEstimationUnitType getEstimationUnit() {
		return estimationUnit;
	}

	/**
	 * Sets the estimation unit.
	 *
	 * @param estimationUnit the new estimation unit
	 */
	public void setEstimationUnit(UseCaseEstimationUnitType estimationUnit) {
		this.estimationUnit = estimationUnit;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public ProjectType getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(ProjectType type) {
		this.type = type;
	}

	/**
	 * Gets the priorization type.
	 *
	 * @return the priorization type
	 */
	public UseCaseDeterminePriorityProcessType getPriorizationType() {
		return priorizationType;
	}

	/**
	 * Sets the priorization type.
	 *
	 * @param priorizationType the new priorization type
	 */
	public void setPriorizationType(UseCaseDeterminePriorityProcessType priorizationType) {
		this.priorizationType = priorizationType;
	}
}
