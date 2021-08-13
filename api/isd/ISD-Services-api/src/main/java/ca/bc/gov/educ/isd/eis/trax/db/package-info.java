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
 *  File:                package-info.java
 *  Date of Last Commit: $Date::                   $
 *  Revision Number:     $Rev::                    $
 *  Last Commit by:      $Author::                 $
 *
 * ***********************************************************************
 */
/**
 * <h1> Package Description </h1>
 * This package defines interfaces for interaction with the TRAX adapter.
 * <p>
 * The TRAXAdapter service definition exposes methods which other services can
 * call to obtain TRAX data. The TRAXAdapter service definition returns a java
 * object containing TRAX data. The data object interfaces expose 'getter'
 * methods which allow calling services to read data of interest.</p>
 * <p>
 * Both the ISD-TRAXAdaptor and the ISD-TRAXAdaptor-stub service adhere to these
 * interfaces.
 * </p>
 * <h2> What the Package Contains </h2>
 * <ul>
 * <li>{@code TRAXData}
 * <li>{@code TRAXAdapter}
 * <li>{@code ExamResult}
 * <li>{@code ExamStudent}
 * <li>{@code Scholarship}
 * <li>{@code ScholarshipStudent}
 * <li>{@code StudentDemographic}
 * <li>{@code StudentInfo}
 * <li>{@code TranscriptCourse}
 */
package ca.bc.gov.educ.isd.eis.trax.db;
