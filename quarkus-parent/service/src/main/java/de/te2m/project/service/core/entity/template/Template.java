/*
 * Template.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.entity.template;

import de.te2m.project.service.core.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * The Class Template.
 *
 * <p>
 * This class is an persistent entity class. The table name is  TEMPLATE  * The inheritance strategy is
 * InheritanceType.TABLE_PER_CLASS.
 * </p>
 *
 * @author ffischer
 */
@Entity
@Table(name = "TEMPLATE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Template extends AbstractEntity {

}
