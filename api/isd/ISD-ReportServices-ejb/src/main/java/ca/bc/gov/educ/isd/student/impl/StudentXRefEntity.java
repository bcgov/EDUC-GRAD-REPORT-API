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
import ca.bc.gov.educ.isd.student.PersonalEducationNumber;
import ca.bc.gov.educ.isd.student.StudentXRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */

public class StudentXRefEntity extends AbstractDomainEntity implements StudentXRef {

    private static final long serialVersionUID = 1L;

    private String studentEid;

    private PersonalEducationNumber pen;

    private List<Identifier> identifiers = new ArrayList<>();

    private Long id;

    private String userEid;

    public StudentXRefEntity() {
    }

    public StudentXRefEntity(String entityId) {
        super(entityId);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getStudentEid() {
        return this.studentEid;
    }

    @Override
    public PersonalEducationNumber getPen() {
        return this.pen;
    }

    @Override
    public List<Identifier> getIdentifiers() {
        return this.identifiers;
    }

    @Override
    public String getUserEid() {
        return userEid;
    }

    public void setStudentEid(String studentEid) {
        this.studentEid = studentEid;
    }

    public void setPen(PersonalEducationNumber pen) {
        this.pen = pen;
    }

    public void setIdentifiers(List<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public void setUserEid(String userEid) {
        this.userEid = userEid;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.pen);
        hash = 53 * hash + Objects.hashCode(this.identifiers);
        hash = 53 * hash + Objects.hashCode(this.userEid);
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
        final StudentXRefEntity other = (StudentXRefEntity) obj;
        if (!Objects.equals(this.pen.getValue(), other.pen.getValue())) {
            return false;
        }
        if (!Objects.equals(this.identifiers, other.identifiers)) {
            return false;
        }
        return Objects.equals(this.userEid, other.userEid);
    }

}
