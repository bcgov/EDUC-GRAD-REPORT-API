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
 *  File:                $Id:: StudentXRef.java 8289 2017-09-26 23:04:07Z CGOM#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.common.party.Identifier;

import java.util.List;

/**
 * Cross reference of a Student and all of the different identifiers issues by
 * organizations.
 *
 * This entity serves to disambiguate a unique individual student which may be
 * assigned many different identifiers as they move throw the school systems.
 * This is a cross reference, because the actual student record may be held in a
 * external system, or a composite of systems. Hence it only provides the
 * student entity identifier not the student record itself. This is to avoid
 * forcing an external call for simple searches of that records exists.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentXRef extends DomainEntity {

    /**
     * The unique individual student entity identifier for this student cross
     * reference.
     *
     * @return String entity identifier for the student.
     */
    String getStudentEid();

    /**
     * Retrieve the entity id of the User Profile associated with this student.
     *
     * @return String entity identifier for the user profile.
     */
    String getUserEid();

    /**
     * Retrieve the <b>current</b> personal education number for the student.
     *
     * A student may have been assigned many PEN over the course of time.
     * Historical PEN are listed in the collection of identifers.
     *
     * @return PersonalEducationNumber The student's current PEN.
     */
    PersonalEducationNumber getPen();

    /**
     * Retrieve the collection of <b>all</b> identifiers indexed by this cross
     * reference.
     *
     * This includes the current PEN, where it exists, but not the entity
     * identifier.
     *
     * @return List of all identifiers which are known for this student, or an
     * empty list if there are none.
     */
    List<Identifier> getIdentifiers();

}
