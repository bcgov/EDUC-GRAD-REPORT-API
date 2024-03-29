/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information
 *  File:                PersonalEducationNumberSimple.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.student.PersonalEducationNumber;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */

public class PersonalEducationNumberObject implements PersonalEducationNumber {

    private String pen;
    private String entityId;

    public PersonalEducationNumberObject() {}

    public PersonalEducationNumberObject(String pen) {
        this.pen = pen;
    }

    public PersonalEducationNumberObject(String pen, String entityId) {
        this.pen = pen;
        this.entityId = entityId;
    }

    @Override
    public String getValue() {
        return pen;
    }

    @Override
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPen() {
        return pen;
    }

    public void setPen(String pen) {
        this.pen = pen;
    }

    @Override
    public String toString() {
        return getPen();
    }
}
