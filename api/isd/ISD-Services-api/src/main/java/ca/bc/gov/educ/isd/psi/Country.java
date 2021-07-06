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
 *  File:                Country.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.psi;

import java.io.Serializable;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Country extends Serializable {

    public String getName();

    public void setName(String name);

    public String getIsoCode();

    public void setIsoCode(String isoCode);

    public String getTraxCode();

    public void setTraxCode(String traxCode);

    public String getCode();

}
