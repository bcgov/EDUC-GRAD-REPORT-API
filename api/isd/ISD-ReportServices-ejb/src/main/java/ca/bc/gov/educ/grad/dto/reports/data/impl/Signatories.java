/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Signatories.java 6287 2017-02-23 21:25:42Z DAJ#$
 *  Date of Last Commit: $Date:: 2017-02-23 13:25:42 -0800 (Thu, 23 Feb 2017)  $
 *  Revision Number:     $Rev:: 6287                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.dto.reports.data.impl;

import ca.bc.gov.educ.grad.dto.reports.data.BusinessEntity;

/**
 * Represents a set of signatures passed into each certificate report. The
 * signatures for the Minister of Education (MoE) and Minister of Advanced
 * Education (MoAE) are usually fixed while the schoolSignatory signatory
 * (schoolSignatory) varies with the Ministry School code. The signature set
 * contains filenames without filename extensions.
 *
 * @author CGI Information Management Consultants Inc.
 */
public final class Signatories extends BusinessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Filename for the signature of the Minister of Education.
     */
    private final static String MINISTER_OF_EDUCATION = "MOE";

    /**
     * Filename for the signature of the Minister of Advanced Education.
     */
    private final static String MINISTER_OF_ADV_EDUCATION = "MOAE";

    /**
     * Filename for the signature of the School Superintendent / Principal /
     * Inspector. By default, the "independent" signature is used.
     */
    private String schoolSignatory;

    /**
     * Default (empty) constructor.
     */
    public Signatories() {
    }

    /**
     * Changes the signature file for the schoolSignatory signatory.
     *
     * @param schoolSignatory The 2-digit signatory code, based on the
     * schoolSignatory's mincode.
     */
    public void setSchoolSignatory(final String schoolSignatory) {
        this.schoolSignatory = schoolSignatory;
    }

    /**
     * Sets the signature file for the schoolSignatory signatory.
     *
     * @return Signature filename.
     */
    public String getSchoolSignatory() {
        return this.schoolSignatory;
    }

    /**
     * Sets the signature file for the MoE.
     *
     * @return Signature filename.
     */
    public String getMinisterOfEducation() {
        return MINISTER_OF_EDUCATION;
    }

    /**
     * Sets the signature file for the MoAE.
     *
     * @return Signature filename.
     */
    public String getMinisterOfAdvancedEducation() {
        return MINISTER_OF_ADV_EDUCATION;
    }
}
