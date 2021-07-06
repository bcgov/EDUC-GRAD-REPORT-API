/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                Constants.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.trax;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class Constants {

    public static final String DATE_YEAR = "yyyy";

    public static final String DATE_PSI = "YYddhhmmss";

    public static final String DATETIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date format used predominately in TRAX data.
     */
    public static final String DATE_TRAX_YMD = "yyyyMMdd";
    public static final String DATE_TRAX_YM = "yyyyMM";

    public static final Date DATE_UNKNOWN_BIRTHDATE = (new GregorianCalendar(1900, 00, 01)).getTime();
}
