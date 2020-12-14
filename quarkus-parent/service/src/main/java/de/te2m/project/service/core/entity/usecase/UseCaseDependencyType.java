/*
 * UseCaseDependencyType.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

import java.util.*;

/**
 * Determines the type of an use case dependency.
 *
 * @author ffischer
 */
public enum UseCaseDependencyType {

	/**
	 * Use -dependency Used for personas for dependencies to the following objects <ul> <li>Usecases</li>
	 * <li>Business objects (usually only of sub type Boundary)</li> </ul>.
	 */
	USES(UseCaseDependencyVisualization.USES),
	/**
	 * The source use case extends the target use case UML: Extends.
	 */
	EXTENDS(UseCaseDependencyVisualization.EXTEND),
	/**
	 * The target use case implements the source use case UML: Generalization.
	 */
	IMPLEMENTS(UseCaseDependencyVisualization.GENERALIZATION),
	/**
	 * The source use case includes the target use case UML: Include.
	 */
	INCLUDES(UseCaseDependencyVisualization.INCLUDE),
	/**
	 * The source use case is a epic and the target use case is part of it. The visualization is similar to the include
	 * dependency. UML. Include
	 */
	IS_CHILD(UseCaseDependencyVisualization.INCLUDE),
	/**
	 * The is attribute.
	 */
	IS_ATTRIBUTE(UseCaseDependencyVisualization.NONE),
	/**
	 * The is operation.
	 */
	IS_OPERATION(UseCaseDependencyVisualization.NONE),
	/**
	 * The is parameter.
	 */
	IS_PARAMETER(UseCaseDependencyVisualization.NONE),
	/**
	 * The is returnvalue.
	 */
	IS_RETURNVALUE(UseCaseDependencyVisualization.NONE),
	/**
	 * The is type.
	 */
	IS_TYPE(UseCaseDependencyVisualization.NONE),
	/**
	 * The package contains.
	 */
	PACKAGE_CONTAINS(UseCaseDependencyVisualization.NONE),
	/**
	 * The system contains.
	 */
	SYSTEM_CONTAINS(UseCaseDependencyVisualization.NONE),
	/**
	 * The utilizes.
	 */
	UTILIZES(UseCaseDependencyVisualization.NONE);

	static {

		// uses
		USES.setAllowedTargetTypes(UseCaseType.USECASE, UseCaseType.BUSINESS_OBJECT);
		USES.setValidEntry(UseCaseType.PERSONA, UseCaseType.USECASE, UseCaseType.BUSINESS_OBJECT);

		// Extends
		EXTENDS.setAllowedTargetTypes(UseCaseType.USECASE, UseCaseType.BUSINESS_OBJECT);
		EXTENDS.setValidEntry(UseCaseType.USECASE, UseCaseType.USECASE);
		EXTENDS.setValidEntry(UseCaseType.BUSINESS_OBJECT, UseCaseType.BUSINESS_OBJECT);

		// Implements
		IMPLEMENTS.setAllowedTargetTypes(UseCaseType.USECASE, UseCaseType.BUSINESS_OBJECT);
		IMPLEMENTS.setValidEntry(UseCaseType.USECASE, UseCaseType.USECASE);
		IMPLEMENTS.setValidEntry(UseCaseType.BUSINESS_OBJECT, UseCaseType.BUSINESS_OBJECT);

		// includes
		INCLUDES.setAllowedTargetTypes(UseCaseType.USECASE,
				UseCaseType.NONFUNCTIONAL_REQUIREMENT,
				UseCaseType.BUSINESS_OBJECT);
		INCLUDES.setValidEntry(UseCaseType.USECASE,
				UseCaseType.USECASE,
				UseCaseType.NONFUNCTIONAL_REQUIREMENT,
				UseCaseType.BUSINESS_OBJECT);

		UTILIZES.setAllowedTargetTypes(UseCaseType.BUSINESS_OBJECT, UseCaseType.ATTRIBUTE);
		UTILIZES.setValidEntry(UseCaseType.USECASE, UseCaseType.BUSINESS_OBJECT, UseCaseType.ATTRIBUTE);

		//IS_CHILD
		IS_CHILD.setAllowedTargetTypes(UseCaseType.USECASE);
		IS_CHILD.setValidEntry(UseCaseType.USECASE, UseCaseType.USECASE);

		//IS_ATTRIBUTE
		IS_ATTRIBUTE.setAllowedTargetTypes(UseCaseType.ATTRIBUTE);
		IS_ATTRIBUTE.setValidEntry(UseCaseType.BUSINESS_OBJECT, UseCaseType.ATTRIBUTE);

		//IS_OPERATION
		IS_OPERATION.setAllowedTargetTypes(UseCaseType.OPERATION);
		IS_OPERATION.setValidEntry(UseCaseType.BUSINESS_OBJECT, UseCaseType.OPERATION);

		//IS_PARAMETER
		IS_PARAMETER.setAllowedTargetTypes(UseCaseType.ATTRIBUTE);
		IS_PARAMETER.setValidEntry(UseCaseType.OPERATION, UseCaseType.ATTRIBUTE);

		//IS_RETURNVALUE
		IS_RETURNVALUE.setAllowedTargetTypes(UseCaseType.ATTRIBUTE);
		IS_RETURNVALUE.setValidEntry(UseCaseType.OPERATION, UseCaseType.ATTRIBUTE);
		//IS_TYPE

		IS_TYPE.setAllowedTargetTypes(UseCaseType.BUSINESS_OBJECT);
		IS_TYPE.setValidEntry(UseCaseType.ATTRIBUTE, UseCaseType.BUSINESS_OBJECT);

		//PACKAGE_CONTAINS
		PACKAGE_CONTAINS.setAllowedTargetTypes(UseCaseType.PACKAGE, UseCaseType.BUSINESS_OBJECT);
		PACKAGE_CONTAINS.setValidEntry(UseCaseType.PACKAGE, UseCaseType.PACKAGE, UseCaseType.BUSINESS_OBJECT);

		//SYSTEM_CONTAINS
		SYSTEM_CONTAINS.setAllowedTargetTypes(UseCaseType.SYSTEM, UseCaseType.USECASE, UseCaseType.SERVICE);
		SYSTEM_CONTAINS.setValidEntry(UseCaseType.SYSTEM, UseCaseType.SYSTEM, UseCaseType.USECASE);

	}

	/**
	 * The vizualization.
	 */
	private final UseCaseDependencyVisualization vizualization;
	/**
	 * The allowed target types.
	 */
	private List<UseCaseType> allowedTargetTypes;
	/**
	 * The validation matrix.
	 */
	private Map<UseCaseType, List<UseCaseType>> validationMatrix;

	/**
	 * Instantiates a new use case dependency type.
	 *
	 * @param vizualization the vizualization
	 */
	UseCaseDependencyType(UseCaseDependencyVisualization vizualization) {
		this.vizualization = vizualization;
	}

	/**
	 * Determines all valid use case dependency types for the provided use case type.
	 *
	 * @param type the type
	 * @return all valid dependency types
	 */
	public static List<UseCaseDependencyType> getValidDependencyTypes(UseCaseType type) {
		List<UseCaseDependencyType> validTypes = new ArrayList<UseCaseDependencyType>();

		for (UseCaseDependencyType tmpType : UseCaseDependencyType.values()) {
			if (tmpType.getAllowedSrcTypes().contains(type)) {
				validTypes.add(tmpType);
			}
		}

		return validTypes;
	}

	/**
	 * Returns all valid valid target use case types for the provided use case type.
	 *
	 * @param type the type
	 * @return the valid target types
	 */
	public static List<UseCaseType> getValidTargetTypes(UseCaseType type) {
		List<UseCaseType> validTypes = new ArrayList<UseCaseType>();

		for (UseCaseDependencyType tmpType : getValidDependencyTypes(type)) {
			for (UseCaseType t : tmpType.getAllowedTargetTypes()) {
				if (!validTypes.contains(t)) {
					validTypes.add(t);
				}
			}
		}

		return validTypes;
	}

	/**
	 * Checks if is valid target type.
	 *
	 * @param src    the src
	 * @param target the target
	 * @return true, if is valid target type
	 */
	public static boolean isValidTargetType(UseCaseType src, UseCaseType target) {
		if (null == src || null == target) {
			return false;
		}
		List<UseCaseType> validtypes = getValidTargetTypes(src);
		return validtypes.contains(target);
	}

	/**
	 * Gets the vizualization.
	 *
	 * @return the vizualization
	 */
	public UseCaseDependencyVisualization getVizualization() {
		return vizualization;
	}

	/**
	 * Gets the allowed src types.
	 *
	 * @return the allowed src types
	 */
	public List<UseCaseType> getAllowedSrcTypes() {
		return new ArrayList<UseCaseType>(validationMatrix.keySet());
	}

	/**
	 * Sets the valid entry.
	 *
	 * @param src            the src
	 * @param allowedtargets the allowedtargets
	 */
	private void setValidEntry(UseCaseType src, UseCaseType... allowedtargets) {
		List<UseCaseType> targets = new ArrayList<UseCaseType>();
		targets.addAll(Arrays.asList(allowedtargets));
		if (null == validationMatrix) {
			validationMatrix = new EnumMap<UseCaseType, List<UseCaseType>>(UseCaseType.class);
		}
		validationMatrix.put(src, targets);

	}

	/**
	 * Gets the allowed target types.
	 *
	 * @return the allowed target types
	 */
	public List<UseCaseType> getAllowedTargetTypes() {
		if (null == allowedTargetTypes) {
			allowedTargetTypes = new ArrayList<UseCaseType>();
		}
		return allowedTargetTypes;
	}

	/**
	 * Sets the allowed target types.
	 *
	 * @param allowedTargetTypes the new allowed target types
	 */
	private void setAllowedTargetTypes(UseCaseType... allowedTargetTypes) {
		this.allowedTargetTypes = Arrays.asList(allowedTargetTypes);
	}

	/**
	 * Gets the valid targets.
	 *
	 * @param src the src
	 * @return the valid targets
	 */
	public List<UseCaseType> getValidTargets(UseCaseType src) {
		return validationMatrix.get(src);
	}

	/**
	 * Checks if is source allowed.
	 *
	 * @param t the t
	 * @return true, if is source allowed
	 */
	public boolean isSourceAllowed(UseCaseType t) {
		if (null == t) {
			return false;
		}
		return validationMatrix.get(t) != null;
	}

	/**
	 * Checks if is target allowed.
	 *
	 * @param t the t
	 * @return true, if is target allowed
	 */
	public boolean isTargetAllowed(UseCaseType t) {
		if (null == t) {
			return false;
		}
		return getAllowedTargetTypes().contains(t);
	}
}
