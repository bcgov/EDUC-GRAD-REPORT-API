/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        AbstractDescribedEntity.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common.support;

import ca.bc.gov.educ.isd.common.DescribedEntity;

import java.util.Objects;

/**
 * A domain entity with a name and description.
 *
 * @author CGI Information Management Consultants Inc.
 */
public abstract class AbstractDescribedEntity extends AbstractDomainEntity implements DescribedEntity {

    private static final long serialVersionUID = -7011596282398302288L;

    private String name;

    private String description;

    public AbstractDescribedEntity() {
        super();
    }

    public AbstractDescribedEntity(String entityId) {
        super(entityId);
    }

    public AbstractDescribedEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public AbstractDescribedEntity(String name, String description, String entityId) {
        super(entityId);
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.description);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractDescribedEntity other = (AbstractDescribedEntity) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.description, other.description);
    }

    @Override
    public String toString() {
        return "AbstractDescribedEntity{" + "name=" + name + ", description=" + description + '[' + super.toString() + ']' + '}';
    }

}
