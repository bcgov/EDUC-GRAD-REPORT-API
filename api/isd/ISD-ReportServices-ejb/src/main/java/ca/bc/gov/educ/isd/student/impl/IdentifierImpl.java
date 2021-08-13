/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student.impl;

import ca.bc.gov.educ.isd.common.party.Identifier;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.common.support.AuditableDetail;

import java.util.Objects;

/**
 * <p>
 * JPA implementation class for the <code>Identifier</code> interface.</p>
 * <p>
 * Implements the <code>AuditableDetail</code> interface in order to maintain
 * the correct values for the audit columns, since there is no specific service
 * class for this entity. Identifiers are part of a strict composition
 * relationship with user profiles, so the user profile administration service
 * is responsible for maintaining them.</p>
 *
 * @author CGI Information Management Consultants Inc.
 */
public class IdentifierImpl extends AbstractDomainEntity implements Identifier, AuditableDetail {

    private static final String CLASSNAME = IdentifierImpl.class.getName();

    private static final long serialVersionUID = 7526472295622776147L;

    private String issuedByOrganizationEntityId;
    private String nameCode;
    private String typeCode;
    private String value;

    private StudentXRefEntity studentXRefEntity;

    /**
     * The ID field of this entity.
     */
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id.
     * <p>
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNameCode() {
        return nameCode;
    }

    @Override
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    @Override
    public String getIssuedByOrganizationEntityId() {
        return issuedByOrganizationEntityId;
    }

    @Override
    public void setIssuedByOrganizationEntityId(String issuedByOrganizationEntityId) {
        this.issuedByOrganizationEntityId = issuedByOrganizationEntityId;
    }

    @Override
    public String getTypeCode() {
        return typeCode;
    }

    @Override
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    // TODO issue with column name being too long for db, 30+ characters
    /**
     * Gets the User Profile associated with the identity.
     *
     * @return UserProfile used with this type of identity.
     */

    public StudentXRefEntity getStudentXRefEntity() {
        return studentXRefEntity;
    }

    /**
     * Sets the Student X Ref.
     *
     * @param studentXRefEntity
     */
    public void setStudentXRefEntity(StudentXRefEntity studentXRefEntity) {
        this.studentXRefEntity = studentXRefEntity;
    }

    /**
     * Used by <code>AuditEntityListener</code> in order to correctly set the
     * audit fields on create or update.
     */
    @Override
    public AbstractDomainEntity getParent() {
        return studentXRefEntity;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.issuedByOrganizationEntityId);
        hash = 53 * hash + Objects.hashCode(this.nameCode);
        hash = 53 * hash + Objects.hashCode(this.typeCode);
        hash = 53 * hash + Objects.hashCode(this.value);
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
        final IdentifierImpl other = (IdentifierImpl) obj;
        if (!Objects.equals(this.issuedByOrganizationEntityId, other.issuedByOrganizationEntityId)) {
            return false;
        }
        if (!Objects.equals(this.nameCode, other.nameCode)) {
            return false;
        }
        if (!Objects.equals(this.typeCode, other.typeCode)) {
            return false;
        }
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append(CLASSNAME);
        sb.append("{");
        sb.append("issuedByOrganizationEntityId=");
        sb.append("issuedByOrganizationEntityId=");
        sb.append(issuedByOrganizationEntityId);
        sb.append(", nameCode=");
        sb.append(nameCode);
        sb.append(", typeCode=");
        sb.append(typeCode);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
