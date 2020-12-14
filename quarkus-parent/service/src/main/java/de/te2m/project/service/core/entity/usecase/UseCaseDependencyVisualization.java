/*
 * UseCaseDependencyVisualization.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.usecase;

/**
 * Enumeration containing the different visualizations for use case dependencies.
 *
 * @author ffi
 */
public enum UseCaseDependencyVisualization {

	/**
	 * Uses: Solid arrow from source to target.
	 */
	USES,

	/**
	 * Generalization: Solid arrow from child to parent.
	 */
	GENERALIZATION,

	/**
	 * Include: Dashed arrow with &lt;&lt;include&gt;&gt; stererotype.
	 */
	INCLUDE,

	/**
	 * Extends Dashed arrow with &lt;&lt;extend&gt;&gt; stererotype.
	 */
	EXTEND,

	/**
	 * No vizualisation for this dependency.
	 */
	NONE

}
