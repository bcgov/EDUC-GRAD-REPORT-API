/*
 * *********************************************************************
 *  Copyright (c) 2018, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: SSOPrincipal.java 10182 2018-05-14 22:34:13Z DA#$
 *  Date of Last Commit: $Date:: 2018-05-14 15:34:13 -0700 (Mon, 14 May 2018)  $
 *  Revision Number:     $Rev:: 10182                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common;

/**
 * Extention of the basic security Principal interface.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SSOPrincipal extends java.security.Principal {

    /**
     * Returns the single sign-on GUID.
     *
     * @return A non-null instance.
     */
    GuidUserType getGuidUserType();

    /**
     * Changes the principal ID associated with this object.
     *
     * @param principalId A non-null String, typically a GUID.
     */
    void setName(String principalId);
}
