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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.transcript;

import ca.bc.gov.educ.isd.common.Predicate;

/**
 * TODO: How is this used?
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ParameterPredicate extends Predicate<String> {

    /**
     * TODO: How is this used?
     *
     * @param key Comment constraints and expected values.
     */
    void setKey(String key);
}
