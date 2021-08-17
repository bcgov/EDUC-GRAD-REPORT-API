/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        TRAXAdaptor.java 
 *  Date of Last Commit: $Date:: 2016-02-29 18:09:58 -0800 (Mon, 29 Feb 2016)  $  
 *  Revision Number:     $Rev:: 950                                            $  
 *  Last Commit by:      $Author:: bbates                                      $ 
 *  
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.trax;

import ca.bc.gov.educ.isd.eis.EISException;

/**
 * Access to the TRAX system.
 *
 * <p>
 * The TRAX application provides access the data and business functions related
 * to a Student’s:
 * <ul>
 * <li>Transcripts
 * <ul>
 * <li>generation
 * <li>mailing
 * <li>electronic transmission
 * </ul>
 * <li>Demographics
 * <li>Graduation Certificates
 * <ul>
 * <li>generation
 * <li>mailing
 * </ul>
 * <li>Exam Results
 * <li>Awards and Scholarships
 * </ul>
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TRAXAdaptor {

    /**
     * Create the Student's Transcript.
     *
     * <p>
     * Triggers transcript creation in TRAX.
     *
     * <p>
     * If returns true, next step would be to getResults. If false, call
     * getLastErrors to see what issue was.
     *
     * @param PEN Personal Education Number for the Student.
     * @param isFinal True, only final transcript should be returned. (Use false
     * to include interim marks)
     * @param referenceNumber Unique Reference Number to assign to the operation
     * for retrieving the student's transcript data.
     * @return True, if the operation was successful.
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean generateTranscript(char[] PEN, Boolean isFinal, String referenceNumber) throws EISException;

    /**
     * Trigger sending hardcopy of transcript through BC Mail Plus.
     *
     * @param PEN Personal Education Number for the Student.
     * @param isFinal isFinal True, only final transcript should be returned.
     * (Use false to include interim marks)
     * @param psi Post Secondary Institution to send the transcript to
     * @return true for success, false otherwise
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean mailTranscript(char[] PEN, Boolean isFinal, PostSecondaryInstitution psi) throws EISException;

    /**
     * Trigger sending electronic transcript to the given PSI.
     *
     * @param PEN Personal Education Number for the Student.
     * @param isFinal isFinal True, only final transcript should be returned.
     * (Use false to include interim marks)
     * @param psi Post Secondary Institution to send the transcript to
     * @return true for success, false otherwise
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean transmitTranscript(char[] PEN, Boolean isFinal, PostSecondaryInstitution psi) throws EISException;

    /**
     * Triggers creation of student grad/completion certificate.
     *
     * <p>
     * If returns true, next step would be to getResults. If false, call
     * getLastErrors to see what issue was.
     *
     *
     * @param PEN Personal Education Number for the Student.
     * @return True, if the operation was successful.
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean generateCertificate(char[] PEN) throws EISException;

    /**
     * Trigger sending hardcopy of grad/completion certificate through BC Mail
     * Plus.
     *
     * @param PEN Personal Education Number for the Student.
     * @param psi Post Secondary Institution to send the transcript to
     * @return true for success, false otherwise
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean mailCertificate(char[] PEN, PostSecondaryInstitution psi) throws EISException;

    /**
     * Triggers creation of student demographics for given PEN.
     *
     * <p>
     * If returns true, next step would be to getResults. If false, call
     * getLastErrors to see what issue was.
     *
     * @param PEN Personal Education Number for the Student.
     * @param referenceNumber Unique Reference Number to assign to the operation
     * for retrieving the student's transcript data.
     * @return True, if the operation was successful.
     *
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean generateStudentDemog(char[] PEN, String referenceNumber) throws EISException;

    /**
     * Triggers creation of student exam results for given PEN.
     *
     * <p>
     * If returns true, next step would be to getResults. If false, call
     * getLastErrors to see what issue was.
     *
     * @param PEN Personal Education Number for the Student.
     * @param referenceNumber Unique Reference Number to assign to the operation
     * for retrieving the student's exam results.
     * @return True, if the operation was successful.
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean generateStudentExamResults(char[] PEN, String referenceNumber) throws EISException;

    /**
     * Triggers creation of student awards and scholarship results for given
     * PEN.
     *
     * <p>
     * If returns true, next step would be to getResults. If false, call
     * getLastErrors to see what issue was.
     *
     * @param PEN Personal Education Number for the Student.
     * @param referenceNumber Unique Reference Number to assign to the operation
     * for retrieving the student's scholarship and award data.
     * @return True, if the operation was successful.
     * @throws EISException Application exception for all underlying EIS
     * exceptions.
     * @since 1.0
     */
    Boolean generateStudentAwardsResults(char[] PEN, String referenceNumber) throws EISException;

    /**
     * Retrieve the error message resulting from an operation identified by a
     * given reference number.
     *
     * Retrieves the TRAX error contents for the reference number identified.
     *
     * @param referenceNumber the Unique Reference Number identifying the
     * results as they relate to an operation.
     *
     * @return Contents of the TRAX output file.
     * @since 1.0
     */
    String[] getErrors(String referenceNumber);   //TODO should this return a Reader? or a char [] for performance.

    /**
     * Retrieve the results of the operation identified by a given reference
     * number.
     *
     * Retrieves the TRAX output file for the reference number identified.
     *
     * @param referenceNumber the Unique Reference Number identifying the
     * results as they relate to an operation.
     * @return Contents of the TRAX output file.
     * @since 1.0
     */
    char[] getResults(String referenceNumber);  //TODO should this return a Reader? or a char [] for performance.

    /**
     * Retrieve the demographic record for a student given the Business
     * Identifier.
     *
     * @param PEN Personal Education Number for the student.
     * @return TRUE, if the student demographic were found and transfer file
     * created on the disk.
     */
    Boolean retrieveStudent(char[] PEN);

    String sendCommand(String command) throws EISException;
}
