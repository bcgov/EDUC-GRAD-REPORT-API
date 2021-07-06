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

package ca.bc.gov.educ.isd.student;

import java.io.Serializable;

/**
 *
 * @author carlos.gomezteshima
 */


public interface OverwriteStudentValidations extends Serializable {
    
    public Boolean getShowSendInterim(final boolean overwriteForCurrentStudent);
    
    public Boolean getShowSendFinalMarks(final boolean overwriteForCurrentStudent);
    
    public Boolean getShowSendTransNow(final boolean overwriteForCurrentStudent);
    
}
