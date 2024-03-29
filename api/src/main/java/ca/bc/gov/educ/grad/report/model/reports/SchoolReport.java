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
 *  File:                $Id:: StudentReport.java 10969 2018-07-25 16:13:39Z D#$
 *  Date of Last Commit: $Date:: 2018-07-25 09:13:39 -0700 (Wed, 25 Jul 2018)  $
 *  Revision Number:     $Rev:: 10969                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.reports;

import ca.bc.gov.educ.grad.report.model.district.District;
import ca.bc.gov.educ.grad.report.model.school.School;

/**
 * Superclass for various reports (transcript, provincial examination,
 * scholarships, certificates, etc.) that involve student information.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SchoolReport extends Report {

    /**
     * Sets the school to use for populating the reports. The default logo code
     * will be BC.
     *
     * @param school The school instance with data used for filling reports.
     */
    void setSchool(School school);

    /**
     * Sets the District to use for populating the reports. The default logo code
     * will be BC.
     *
     * @param district The District instance with data used for filling reports.
     */
    void setDistrict(District district);
}
