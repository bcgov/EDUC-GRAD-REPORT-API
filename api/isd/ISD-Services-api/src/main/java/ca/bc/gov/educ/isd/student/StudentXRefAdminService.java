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
 *  File:                $Id:: StudentXRefAdminService.java 8289 2017-09-26 23#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.List;
import java.util.Map;

/**
 * Provides a way to work with students using identifiers from a variety of
 * systems.
 *
 *
 * @author CGI Information Management Consultants Inc.
 * @version 0.1
 * @since 0.1
 *
 */
public interface StudentXRefAdminService extends CommonEntityService<StudentXRef, SearchResult> {

    /**
     * Load the student cross reference entity for the given PEN.
     *
     * <p>
     * Retrieves the Students identifier cross reference. These identifiers can
     * then be used to obtain the specific Student entity needed.
     *
     * @param pen Personal Education number of the student.
     * @return Student identifier cross reference which contains the given PEN.
     * @throws DataException If the data base cannot be accessed, a NULL or
     * invalid PEN is provided, or more that one Student cross reference is
     * associated with the specific PEN.
     */
    StudentXRef read(PersonalEducationNumber pen) throws DataException;

    StudentXRef read(Student student) throws DataException;

    /**
     * Add an identifier to a student's id cross reference.
     *
     * @param xref The student xref entity to update.
     * @param issuedByEid The entity id of the issuing organization / department
     * / system.
     * @param eID The entity id for which the student is known by the issuer
     * @param idName The textual name of the identifier.
     * @return An updated Student cross reference with the new identifier
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    StudentXRef addIdentifier(StudentXRef xref, String issuedByEid, String eID, String idName) throws DataException;

    /**
     * Reads and returns the StudentXRef for a given Single Sign On GUID.
     *
     * @param guid
     * @return
     * @throws DataException
     */
    StudentXRef readBySsoGuid(String guid) throws DataException;

    /**
     * Method used to take an existing PEN with a Merged status and fetch the
     * true PEN from the record and update the STS StudentXRef with the current
     * PEN.
     *
     * @param pen of the merged record.
     * @return a map with Student data from TRAX to update the User Profile
     * with. The map will contain firstName, middleName, lastName, DOB(as a
     * Date()), and the profileEid of the User Profile.
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    Map<String, Object> updateXrefFromMergeStatus(String pen) throws DataException;

    /**
     * Method to return the StudentXRef by PEN.
     *
     * @param pen
     * @return
     * @throws DataException
     */
    StudentXRef searchForExisting(String pen) throws DataException;

    /**
     * Method to return the StudentXRef by PEN.
     *
     * @param pen
     * @return
     * @throws DataException
     */
    List<StudentXRef> searchForExistingLike(String pen) throws DataException;

    /**
     * Method returns a non-JPA version for use outside the service. This
     * prevents the Hibernate errors around the bidirectional relationship with
     * the StudentXRef ie PersistentBag error.
     *
     * @param profileEntityId
     * @return
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    PersonalEducationNumber findPEN(String profileEntityId) throws DomainServiceException;
}
