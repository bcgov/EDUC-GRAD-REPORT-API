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
package ca.bc.gov.educ.isd.eis.trax.db;

import java.util.Date;

/**
 * Defines the public methods to access the ExamStudent object which contains
 * the student information for a student which has exam results data.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ExamStudent extends TRAXData {

    /**
     * get the PEN which the data corresponds to
     *
     * @return PEN
     */
    String getPen();

    /**
     * get the student's first name
     *
     * @return first name
     */
    String getFirstName();

    /**
     * get the student's last name
     *
     * @return last name
     */
    String getLastName();

    /**
     * get the date the exam record was last updated
     *
     * @return get last updated date
     */
    Date getLastUpdated();

    /**
     * get the date the exam record was last issued
     *
     * @return get last issued date
     */
    Date getReportDate();

    /**
     * get the display logo values: BC or YU
     *
     * @return logo
     */
    String getLogo();

    /**
     * get the ministry unique code identifier for the school.
     * <p>
     * @return
     */
    String getMincode();

    /**
     * get the school name.
     * <p>
     * @return
     */
    String getSchoolName();

    /**
     * get the student birth date.
     *
     * @return
     */
    Date getBirthdate();

    /**
     * get the student's middle name(s)
     *
     * @return middle name
     */
    String getMiddleName();

}
