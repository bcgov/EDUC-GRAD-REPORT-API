/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 *
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 *
 *   Revision Control Information
 *   File:                $Id:: OrderPerson.java 3171 2016-08-27 20:05:06Z cpri#$
 *   Date of Last Commit: $Date:: 2016-08-27 13:05:06 -0700 (Sat, 27 Aug 2016)  $
 *   Revision Number:     $Rev:: 3171                                           $
 *   Last Commit by:      $Author:: cprince                                    $
 *
 *
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.sales.nonpen;

import ca.bc.gov.educ.isd.ecommerce.sales.ged.OrderPerson;

/**
 * @deprecated There should not be different types of person.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface NonPENOrderPerson extends OrderPerson {

    /**
     * TODO: Can find a School interface, but only a demo school impl, so using
     * String for now.
     *
     * @return the lastSchool
     */
    String getLastSchool();

    /**
     * @param lastSchool the lastSchool to set
     */
    void setLastSchool(String lastSchool);

    /**
     * @return the graduatedYear
     */
    String getGraduatedYear();

    /**
     * @param graduatedYear the graduatedYear to set
     */
    void setGraduatedYear(String graduatedYear);

    /**
     * @return the gradeAchieved
     */
    String getLastGradeAchieved();

    /**
     *
     * @param lastGradeAchieved
     */
    void setLastGradeAchieved(String lastGradeAchieved);

}
