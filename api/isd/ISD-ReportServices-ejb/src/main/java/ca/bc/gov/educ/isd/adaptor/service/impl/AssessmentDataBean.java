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
package ca.bc.gov.educ.isd.adaptor.service.impl;

import ca.bc.gov.educ.exception.EntityNotFoundException;
import ca.bc.gov.educ.grad.dao.GradToIsdDataConvertBean;
import ca.bc.gov.educ.grad.dto.ReportData;
import ca.bc.gov.educ.isd.adaptor.dao.utils.TRAXThreadDataUtility;
import ca.bc.gov.educ.isd.adaptor.impl.LiteracyAssessmentResultImpl;
import ca.bc.gov.educ.isd.adaptor.impl.NumeracyAssessmentResultImpl;
import ca.bc.gov.educ.isd.adaptor.service.AssessmentData;
import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.assessment.AssessmentCourseCode;
import ca.bc.gov.educ.isd.eis.trax.db.LitAssessmentResult;
import ca.bc.gov.educ.isd.eis.trax.db.NumAssessmentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
@Repository
@DeclareRoles({TRAX_READ})
public class AssessmentDataBean implements AssessmentData, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String CLASSNAME = AssessmentDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String QUERY_FIND_ASSESSMENTS_BY_PEN
            = "SELECT"
            + "  new " + NumeracyAssessmentResultImpl.class.getCanonicalName() + "("
            + "  r.studentNumber, r.assessmentCode, r.assessmentSession,"
            + "  r.assessmentProficiencyScore, "
            + "  r.assessmentTotalSelectedResponse, r.studentAssessmentTotalSeletedResponse,"
            + "  r.assessmentTotalExtendedResponse, r.studentAssessmentTotalExtendedResponse,"
            + "  r.assessmentSelectedResponseName1, r.assessmentSelectedResponseValue1, r.assessmentSelectedResponseTotal1,"
            + "  r.assessmentSelectedResponseName2, r.assessmentSelectedResponseValue2, r.assessmentSelectedResponseTotal2,"
            + "  r.assessmentSelectedResponseName3, r.assessmentSelectedResponseValue3, r.assessmentSelectedResponseTotal3,"
            + "  r.assessmentSelectedResponseName4, r.assessmentSelectedResponseValue4, r.assessmentSelectedResponseTotal4,"
            + "  r.assessmentExtendedResponseName1, r.assessmentExtendedResponseValue1, r.assessmentExtendedResponseTotal1,"
            + "  r.assessmentExtendedResponseName2, r.assessmentExtendedResponseValue2, r.assessmentExtendedResponseTotal2) "
            + "FROM"
            + "  TswMailerAssessmentEntity r "
            + "WHERE"
            + "  r.studentNumber = ?1 AND r.assessmentSession = ?2 AND r.assessmentCode = ?3";
    
    private static final String QUERY_FIND_LITERACY_ASSESSMENTS_BY_PEN
            = "SELECT"
            + "  new " + LiteracyAssessmentResultImpl.class.getCanonicalName() + "("
            + "  r.studentNumber, r.assessmentCode, r.assessmentSession,"
            + "  r.assessmentProficiencyScore, "
            + "  r.assessmentTotalClaimC, r.studentAssessmentTotalClaimC, r.assessmentNameClaimC,"
            + "  r.assessmentTotalClaimW, r.studentAssessmentTotalClaimW, r.assessmentNameClaimW,"
            + "  r.assessmentSelectedResponseNameTaskA, r.assessmentSelectedResponseValueTaskA, r.assessmentSelectedResponseTotalTaskA,"
            + "  r.assessmentSelectedResponseNameTaskEI, r.assessmentSelectedResponseValueTaskEI, r.assessmentSelectedResponseTotalTaskEI,"
            + "  r.assessmentExtendedResponseNameTaskEI, r.assessmentExtendedResponseValueTaskEI, r.assessmentExtendedResponseTotalTaskEI,"
            + "  r.assessmentExtendedResponseNameConceptsGO, r.assessmentExtendedResponseValueConceptsGO, r.assessmentExtendedResponseTotalConceptsGO,"
            + "  r.assessmentExtendedResponseNameConceptsWRA, r.assessmentExtendedResponseValueConceptsWRA, r.assessmentExtendedResponseTotalConceptsWRA,"
            + "  r.choicePathID)"
            + "FROM"
            + "  TswMailerLiteracyAssessmentEntity r "
            + "WHERE"
            + "  r.studentNumber = ?1 AND r.assessmentSession = ?2 AND r.assessmentCode = ?3";
    
    private static final String QUERY_FIND_LITERACY_ASSESSMENTS_FR_BY_PEN
            = "SELECT"
            + "  new " + LiteracyAssessmentResultImpl.class.getCanonicalName() + "("
            + "  r.studentNumber, r.assessmentCode, r.assessmentSession,"
            + "  r.assessmentProficiencyScore, "
            + "  r.assessmentTotalClaimC, r.studentAssessmentTotalClaimC, r.assessmentNameClaimC,"
            + "  r.assessmentTotalClaimW, r.studentAssessmentTotalClaimW, r.assessmentNameClaimW,"
            + "  r.assessmentTotalClaimO, r.studentAssessmentTotalClaimO, r.assessmentNameClaimO,"
            + "  r.assessmentSelectedResponseNameTaskA, r.assessmentSelectedResponseValueTaskA, r.assessmentSelectedResponseTotalTaskA,"
            + "  r.assessmentSelectedResponseNameTaskEI, r.assessmentSelectedResponseValueTaskEI, r.assessmentSelectedResponseTotalTaskEI,"
            + "  r.assessmentExtendedResponseNameTaskEI, r.assessmentExtendedResponseValueTaskEI, r.assessmentExtendedResponseTotalTaskEI,"
            + "  r.assessmentExtendedResponseNameConceptsGO, r.assessmentExtendedResponseValueConceptsGO, r.assessmentExtendedResponseTotalConceptsGO,"
            + "  r.assessmentExtendedResponseNameConceptsWRA, r.assessmentExtendedResponseValueConceptsWRA, r.assessmentExtendedResponseTotalConceptsWRA,"
            + "  r.assessmentExtendedResponseNameConceptsWRS, r.assessmentExtendedResponseValueConceptsWRS, r.assessmentExtendedResponseTotalConceptsWRS,"
            + "  r.assessmentNameConcepts1, r.assessmentValueConcepts1, r.assessmentTotalConcepts1,"
            + "  r.assessmentNameConcepts2, r.assessmentValueConcepts2, r.assessmentTotalConcepts2,"
            + "  r.assessmentNameConcepts3, r.assessmentValueConcepts3, r.assessmentTotalConcepts3,"
            + "  r.choicePathID)"
            + "FROM"
            + "  TswMailerLiteracyAssessmentFrenchEntity r "
            + "WHERE"
            + "  r.studentNumber = ?1 AND r.assessmentSession = ?2 AND r.assessmentCode = ?3";

    @Autowired
    GradToIsdDataConvertBean gradtoIsdDataConvertBean;

    @Override
    @RolesAllowed(TRAX_READ)
    public List<? extends NumAssessmentResult> findAssessments(
            final String pen,
            final String sessionDate,
            final AssessmentCourseCode code) throws EISException {
        final String methodName = "findAssessments(String)";
        LOG.entering(CLASSNAME, methodName);

        List<NumeracyAssessmentResultImpl> resultsList = null;

        if (resultsList == null) {
            resultsList = new ArrayList<>();
        }

        LOG.exiting(CLASSNAME, methodName);
        return resultsList;
    }
    
    @Override
    @RolesAllowed(TRAX_READ)
    public List<? extends LitAssessmentResult> findLiteracyAssessments(
            final String pen,
            final String sessionDate,
            final AssessmentCourseCode code) throws EISException {
        final String methodName = "findAssessments(String)";
        LOG.entering(CLASSNAME, methodName);
        
        String queryStr = AssessmentCourseCode.LITERACY_ENGLISH.equals(code) ? QUERY_FIND_LITERACY_ASSESSMENTS_BY_PEN : QUERY_FIND_LITERACY_ASSESSMENTS_FR_BY_PEN;

        List<LiteracyAssessmentResultImpl> resultsList = null;

        if (resultsList == null) {
            resultsList = new ArrayList<>();
        }

        LOG.exiting(CLASSNAME, methodName);
        return resultsList;
    }

    @Override
    public List<? extends NumAssessmentResult> findAssessments(String pen) throws EISException {
        final String methodName = "findAssessments(String)";
        LOG.entering(CLASSNAME, methodName);

        ReportData reportData = TRAXThreadDataUtility.getGenerateReportData();

        if (reportData == null) {
            EntityNotFoundException dse = new EntityNotFoundException(
                    null,
                    "Report Data not exists for the current report generation");
            LOG.throwing(CLASSNAME, methodName, dse);
            throw dse;
        }

        List<NumAssessmentResult> resultsList = gradtoIsdDataConvertBean.getAssessmentCourses(reportData);

        if (resultsList == null) {
            resultsList = new ArrayList<>();
        }

        LOG.exiting(CLASSNAME, methodName);
        return resultsList;
    }

}
