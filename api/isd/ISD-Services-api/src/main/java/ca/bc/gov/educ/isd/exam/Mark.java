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
 *  File:                $Id:: Mark.java 3760 2016-09-22 14:53:27Z cprince     $
 *  Date of Last Commit: $Date:: 2016-09-22 07:53:27 -0700 (Thu, 22 Sep 2016)  $
 *  Revision Number:     $Rev:: 3760                                           $
 *  Last Commit by:      $Author:: cprince                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.exam;

import ca.bc.gov.educ.isd.common.DomainEntity;

/**
 * Represents information about a mark associated with an examination.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Mark extends DomainEntity {

    /**
     * Returns the school percentage value for an examination result.
     *
     * @return A number from 0 - 100, or a three-letter code.
     */
     String getSchoolPercent();

    /**
     * Returns the best school percentage value for an examination result.
     *
     * @return A number from 0 - 100.
     */
     String getBestSchoolPercent();

    /**
     * Returns the exam percentage value for an examination result.
     *
     * @return A number from 0 - 100, or a three-letter code.
     */
     String getExamPercent();

    /**
     * Returns the best examination percentage value for an examination result.
     *
     * @return A number from 0 - 100.
     */
     String getBestExamPercent();

    /**
     * Final grade as a percentage, but also includes status codes.
     *
     * @return A number from 0 - 100 or a three-letter code.
     */
     String getFinalPercent();

    /**
     * Final grade percentage translated into a letter grade.
     *
     * @return A letter, typically between A through F with + and - symbols.
     */
     String getFinalLetterGrade();

}
