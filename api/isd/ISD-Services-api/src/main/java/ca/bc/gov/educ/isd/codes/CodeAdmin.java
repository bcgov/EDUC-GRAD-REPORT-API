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
 *  File:                $Id:: CodeAdmin.java 10208 2018-05-18 15:40:07Z DAJAR#$
 *  Date of Last Commit: $Date:: 2018-05-18 08:40:07 -0700 (Fri, 18 May 2018)  $
 *  Revision Number:     $Rev:: 10208                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.codes;

/**
 * A code, its values, and description. A SIF Implementation Specification (NA)
 * 3.3 - Data Model.
 *
 * @author CGI Information Management Consultants Inc.
 * @see http://specification.sifassociation.org/Implementation/NA/3.3/
 */
public interface CodeAdmin extends Code {

    /**
     * Sets the SIF-compatiable code set.
     *
     * @param codeSet Associated with the code.
     */
    void setCodeSet(CodeSet codeSet);

    /**
     * Sets the symbolic hexadecimal code value.
     *
     * @param hex The code value as a hexadecimal string.
     */
    void setCodeHex(String hex);

    /**
     * A description of the term.
     *
     * @param text Text associated with the code.
     */
    void setCodeText(String text);

    /**
     * Names the code for organizational purposes.
     *
     * @param name The name of the code.
     */
    void setName(String name);

    /**
     * Determine the status of a coded value such as "ACTIVE".
     *
     * @param status The code state.
     */
    void setStatus(String status);

    /**
     * Returns the human-readable text for this code. If there is no description
     * this must return the code itself.
     *
     * @param description A non-null string.
     */
    void setDescription(String description);
}
