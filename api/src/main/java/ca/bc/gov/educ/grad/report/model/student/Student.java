/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Student.java 8289 2017-09-26 23:04:07Z CGOMEZTE $
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.model.student;

import ca.bc.gov.educ.grad.report.model.common.SignatureBlockType;
import ca.bc.gov.educ.grad.report.model.common.party.Person;
import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;
import ca.bc.gov.educ.grad.report.model.graduation.NonGradReason;
import ca.bc.gov.educ.grad.report.model.graduation.OtherProgram;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Represents a student business entity.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Student extends Person {

    /**
     * Retrieve the Student PEN.
     * <p>
     * The PEN is an identifier given to students. It is possible that a student
     * does not have a PEN assign or that a duplicate PEN exists.
     *
     * @return pen pen
     */
    PersonalEducationNumber getPen();

    /**
     * Date the student was born.
     *
     * @return A non-null date.
     */
    Date getBirthdate();

    /**
     * Student's current grade.
     *
     * @return Current grade.
     */
    String getGrade();

    /**
     * Student's current gender.
     *
     * @return Current gender.
     */
    String getGender();

    /**
     * Returns information about where to send physical items (such as the
     * student's transcripts). In the future, this might change to accommodate
     * international mailing addresses.
     *
     * @return A non-null mailing address.
     */
    PostalAddress getCurrentMailingAddress();

    /**
     * Gets mincode grad.
     *
     * @return the mincode grad
     */
    String getMincodeGrad();

    /**
     * Gets english cert.
     *
     * @return the english cert
     */
    String getEnglishCert();

    /**
     * Gets french cert.
     *
     * @return the french cert
     */
    String getFrenchCert();

    /**
     * Gets stud status.
     *
     * @return the stud status
     */
    String getStudStatus();

    /**
     * Gets signature block types.
     *
     * @return the Map of SignatureBlockType types
     */
    Map<String, SignatureBlockType> getSignatureBlockTypes();

    /**
     * Gets grad program.
     *
     * @return the grad program
     */
    String getGradProgram();

    /**
     * Gets grad program year.
     *
     * @return the grad program year
     */
    String getGradProgramYear();

    /**
     * Gets grad req year.
     *
     * @return the grad req year
     */
    String getGradReqYear();

    /**
     * Gets local id.
     *
     * @return the local id
     */
    String getLocalId();

    /**
     * Gets has other program.
     *
     * @return the has other program
     */
    String getHasOtherProgram();

    /**
     * Gets other program participation.
     *
     * @return the other program participation
     */
    List<OtherProgram> getOtherProgramParticipation();

    /**
     * Gets non grad reasons.
     *
     * @return non grad reasons
     */
    List<NonGradReason> getNonGradReasons();

    /**
     * Gets non grad reasons as string.
     *
     * @return non grad reasons
     */
    String getNonGradReasonsString();

    /**
     * Gets other program participationdata source.
     *
     * @return the other program participationdata source
     */
    JRBeanCollectionDataSource getOtherProgramParticipationdataSource();

    /**
     * Gets last update date
     *
     * @return last update date
     */
    Date getLastUpdateDate();


}
