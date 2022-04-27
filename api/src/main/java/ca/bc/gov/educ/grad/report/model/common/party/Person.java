/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Person.java 8289 2017-09-26 23:04:07Z CGOMEZTE  $
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.common.party;

/**
 * @author CGI Information Management Consultants Inc.
 */
public interface Person extends Party {

    String getLastName();

    String getFirstName();

    String getMiddleName();

    String getFullName();
}
