/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        Code.java
 *  Date of Last Commit: $Date:: 2018-05-18 08:40:07 -0700 (Fri, 18 May 2018)  $
 *  Revision Number:     $Rev:: 10208                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.codes;

import ca.bc.gov.educ.grad.report.model.common.DomainEntity;

/**
 * A code, its values, and description. A SIF Implementation Specification (NA)
 * 3.3 - Data Model.
 *
 * @author CGI Information Management Consultants Inc.
 * @see http://specification.sifassociation.org/Implementation/NA/3.3/
 */
public interface Code extends DomainEntity {

    /**
     * Retrieve the Code Set that this code belongs to.
     *
     * @return Code Set.
     */
    CodeSet getCodeSet();

    /**
     * Retrieve the symbolic hexadecimal value of a code.
     *
     * @return The code value as a hexadecimal string.
     */
    String getCodeHex();

    /**
     *
     * @return
     */
    String getCodeText();

    /**
     *
     * @return
     */
    String getName();

    /**
     * Get the fully qualified name of the code. The fully qualified name is the
     * code is the code name appended to the code set name space.
     *
     * The name space ensures that codes received from multiple sources,
     * organizations and integrated systems can be uniquely represented.
     *
     * @return The fully qualified name of the code.
     */
    String getAbsoluteName();

    /**
     * Determine the status of a coded value such as "ACTIVE".
     *
     * @return The status of this code.
     */
    String getStatus();

    /**
     * Is this code active?
     *
     * This replaced the Status property in the SIF code set model. When a code
     * is used for a user interface or processed by business rules it must be
     * determined if the status is &quot;ACTIVE&quot;. It is simpler to process
     * a boolean than a string in these cases.
     *
     * @return True, if the code may be used for new and updated values in a
     * domain object.
     */
    Boolean isActive();

    /**
     * Returns the human-readable text for this code. If there is no description
     * this should return the code itself.
     *
     * @return A non-null String.
     */
    String getDescription();

}
