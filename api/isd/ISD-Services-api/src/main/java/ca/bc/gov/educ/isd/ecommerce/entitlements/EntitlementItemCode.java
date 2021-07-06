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
 *  File:                $Id:: EntitlementItemCode.java 5945 2017-01-27 20:36:08#$
 *  Date of Last Commit: $Date:: 2017-01-27 12:36:08 -0800 (Fri, 27 Jan 2017)  $
 *  Revision Number:     $Rev:: 5945                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.entitlements;

import static ca.bc.gov.educ.isd.common.Constants.TRANSCRIPT_IDENTIFIER;
import ca.bc.gov.educ.isd.student.StudentTypeEnum;

/**
 * Represents item codes for entitlement entities.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum EntitlementItemCode {

    PSI_F("PSI_" + StudentTypeEnum.FORMER.getValue()),
    PSI_C("PSI_" + StudentTypeEnum.CURRENT.getValue()),
    TRANSCRIPT_F(TRANSCRIPT_IDENTIFIER + "_" + StudentTypeEnum.FORMER.getValue()),
    TRANSCRIPT_C(TRANSCRIPT_IDENTIFIER + "_" + StudentTypeEnum.CURRENT.getValue());

    private final String code;

    private EntitlementItemCode(final String name) {
        this.code = name;
    }

    @Override
    public String toString() {
        return this.code;
    }

    public static boolean isPSI(EntitlementItemCode code) {
        return PSI_F.equals(code) || PSI_C.equals(code);
    }

    public static boolean isTranscript(EntitlementItemCode code) {
        return TRANSCRIPT_F.equals(code) || TRANSCRIPT_C.equals(code);
    }
}
