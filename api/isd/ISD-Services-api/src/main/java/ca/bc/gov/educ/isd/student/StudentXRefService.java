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
 *  File:                $Id:: StudentXRefService.java 10182 2018-05-14 22:34:#$
 *  Date of Last Commit: $Date:: 2018-05-14 15:34:13 -0700 (Mon, 14 May 2018)  $
 *  Revision Number:     $Rev:: 10182                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.student;

import ca.bc.gov.educ.isd.common.BusinessService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchObject;
import java.util.Date;
import java.util.List;

/**
 * A (student) users access to their own identifier cross reference.
 *
 * The service is in contrast the the Student X Ref Administration service which
 * allows administrative processes to create and edit identifier cross
 * references.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface StudentXRefService extends BusinessService {

    //TODO - refactor / pull-up the exists method to common entity services and add id parameter
    /**
     * Check if an identifier cross reference exists for the currently
     * authenticated user.
     *
     * This method performs a search for the identifier cross reference
     * currently authenticated user based the principal object retrieved from
     * the Session Context. This query will search match properties of the
     * custom principal object to the SSO account records in the underlying data
     * store.
     *
     * @return TRUE if the user profile exists.
     * @throws DataException If there is an error accessing the data store.
     */
    Boolean exists() throws DomainServiceException;

    /**
     * Check if an identifier cross reference exists for the submitted PEN
     *
     * This method performs a search for the identifier cross reference based on
     * a provided PEN.
     *
     * @param pen
     * @return The found StudentXRef if it exists.
     * @throws DataException If there is an error accessing the data store.
     */
    StudentXRef searchForExisting(String pen) throws DataException;

    /**
     * Gets the Student information from TRAX adaptor
     *
     * @param pen
     * @return the information stored in the TRAX STUDENT MASTER table.
     * @throws ca.bc.gov.educ.isd.common.DataException
     */
    StudentDetails searchDetailedInfoForExisting(String pen) throws DataException;

    /**
     * Create an initial user account for the currently authenticated user.
     *
     * Use this method for the self-service initialization of a users cross
     * reference.
     * <p>
     * For an authenticated user which does not have an existing cross
     * reference:
     * <ol>
     * <li>The currently authenticated user's identity is populated by calling
     * the User Profile Services.
     *
     * <li>The student information is verified against the (TRAX) student
     * record. (Only currently authenticated users that are students can have a
     * cross reference)
     *
     * <li>The cross reference is populated with the user entity id and the
     * user's sso guid (if applicable)
     *
     * </ol>
     *
     * <p>
     * If the currently authenticated user has an existing cross reference, an
     * exception is thrown.
     * <p>
     * If the student information does not match a (TRAX) student, then a Domain
     * Service Exception is thrown.
     *
     * @param PEN Personal Education Number
     * @param lastName the Student Last Name as it appears in the school
     * records.
     * @param firstName the Student First Name as is appears in the School
     * records.
     * @param dob the Student date of birth as it appears in the School records.
     * @throws DataException If the user profile already exists, or there is any
     * error communicating with the underlying data store.
     */
    void initialize(String PEN, String lastName, String firstName,
            Date dob)
            throws DomainServiceException;

    /**
     * Retrieve the identifier cross reference table for the currently
     * authenticated user.
     *
     * @return The cross reference for a
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException if the current
     * user does not have a cross reference.
     */
    StudentXRef read() throws DomainServiceException;

    /**
     * Search based on the passed criteria.
     *
     * @param criteria
     * @return
     * @throws DomainServiceException
     * @throws DataException
     */
    public List<StudentXRef> search(List<SearchObject> criteria) throws DomainServiceException, DataException;

    /**
     * Match a person to a student record.
     *
     * The person is matched to a student record if each of the parameters match
     * exactly. (Case Insensitive)
     *
     * <p>
     * Details of the Student can then be retrieved with the read method.
     *
     * @param PEN Personal Education Number
     * @param lastName the Student Last Name as it appears in the school
     * records.
     * @param firstName the Student First Name as is appears in the School
     * records.
     * @param dob the Student date of birth as it appears in the School records.
     * @return TRUE, if a matching student record is found.
     *
     * @throws DataException
     */
    Boolean match(String PEN, String lastName, String firstName,
            Date dob) throws DataException;

    /**
     * Writes the studentXRef.
     *
     * @param studentXRef The reference to be persisted.
     * @return The managed entity.
     * @throws DataException
     * @throws DomainServiceException
     */
    StudentXRef write(StudentXRef studentXRef) throws DataException, DomainServiceException;

    /**
     * Checks if a TRAX record has a M or D status.
     *
     * @param pen the PEN of the record to check.
     * @return If the status matches the criteria.
     * @throws DataException
     * @throws DomainServiceException
     */
    String checkRecordStatus(String pen) throws DataException, DomainServiceException;

    /**
     * Removed a XRef in the case of a registration restart.
     *
     * @param studentXRef the StudentXRef to remove.
     * @throws DataException
     * @throws DomainServiceException
     */
    void remove(StudentXRef studentXRef) throws DataException, DomainServiceException;

    /**
     * Search for students by their partial PEN and last name, only return the
     * students meet all given criteria.
     *
     * @param tokens List of pen or last name.
     * @return list of <code>StudentProfileMasterLite</code> who match all given
     * PENs and last names.
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    List<StudentProfileMasterInfo> searchStudentPartialMatchAll(List<String> tokens)
            throws DomainServiceException;
}
