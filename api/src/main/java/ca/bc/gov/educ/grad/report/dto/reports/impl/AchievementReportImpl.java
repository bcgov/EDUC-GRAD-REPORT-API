/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education and Child Care, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: TranscriptReportImpl.java 9753 2018-03-23 22:21#$
 *  Date of Last Commit: $Date:: 2018-03-23 15:21:32 -0700 (Fri, 23 Mar 2018)  $
 *  Revision Number:     $Rev:: 9753                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.impl;

import ca.bc.gov.educ.grad.report.dto.reports.data.adapter.BusinessEntityAdapter;
import ca.bc.gov.educ.grad.report.dto.reports.data.impl.Status;
import ca.bc.gov.educ.grad.report.model.assessment.Assessment;
import ca.bc.gov.educ.grad.report.model.graduation.GradProgram;
import ca.bc.gov.educ.grad.report.model.graduation.GraduationProgramCode;
import ca.bc.gov.educ.grad.report.model.graduation.NonGradReason;
import ca.bc.gov.educ.grad.report.model.reports.AchievementReport;
import ca.bc.gov.educ.grad.report.model.transcript.GraduationData;

import java.util.Date;
import java.util.List;

import static ca.bc.gov.educ.grad.report.dto.reports.data.adapter.BusinessEntityAdapter.adapt;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.HTML;
import static ca.bc.gov.educ.grad.report.model.reports.ReportFormat.XML;

/**
 * Represents a student's transcript report.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AchievementReportImpl extends StudentReportImpl implements AchievementReport {

    private static final long serialVersionUID = 8L;

    public static final String SUFFIX_PREVIEW = "-bw";
    public static final String SUFFIX_HTML = "_HTML";
    public static final String SUFFIX_INTERIM = "_interim";

    public static final String ACHIEVEMENT_REPORT_NAME = "Achievement";
    public static final String ACHIEVEMENT_DRAFT_REPORT_NAME = "AchievementDraft";

    private Assessment assessment;

    private GraduationProgramCode graduationProgramCode;

    /**
     * Assume unofficial transcripts by default.
     */
    private boolean preview = true;

    /**
     * Assume no interim marks by default.
     */
    private boolean interim;

    /**
     * Constructs a new report using the default report template.
     */
    public AchievementReportImpl() {
        this(ACHIEVEMENT_REPORT_NAME);
    }

    /**
     * Constructs a new report using the named report template.
     */
    public AchievementReportImpl(String reportName) {
        super(reportName);

        // Prevent two HTML header/footers from being added to the
        // HTML version of transcripts.
        setWrapHtml(false);
    }

    /**
     * Sets the report name according to the graduation program code. This must
     * be called before the summary report can be created. Without calling this
     * method, another transcript report would be produced, instead of the
     * summary pages (the back pages to the interleave within the transcript).
     */
    @Override
    public void preprocessReportName() {

    }

    /**
     * Returns the type of report to generate. This value is passed into the
     * report as the P_REPORT_TYPE parameter.
     *
     * @return A GraduationProgramCode string.
     * @throws RuntimeException The student's requirement year could not be
     * ascertained (i.e., unknown report type).
     */
    private String getReportType() {
        return getGraduationProgramCode().toString();
    }

    /**
     * Returns the Assessment
     *
     * @return A non-null value.
     */
    public Assessment getAssessment() {
        return assessment;
    }

    /**
     *
     * Sets the Assessment
     *
     * @param assessment Data to be set.
     */
    @Override
    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    /**
     * Returns the graduation program code, which is used to determine the
     * report type and summary page. This also is used to control the resource
     * bundle name.
     *
     * @return A non-null enumerated type.
     */
    private GraduationProgramCode getGraduationProgramCode() {
        return this.graduationProgramCode;
    }

    /**
     * TODO: Model the domain.
     *
     * Sets the Graduation Data which involves graduation and course requirement
     * details used the XML transcripts.
     *
     * @param graduationData Data to be set.
     */
    @Override
    public void setGraduationData(final GraduationData graduationData) {
        final ca.bc.gov.educ.grad.report.dto.reports.data.impl.AcademicAward aa
                = BusinessEntityAdapter.adapt(graduationData, getStudent(), getGraduationProgramCode());
        getStudent().setAcademicAward(aa);
    }

    /**
     * Sets the graduation program code, which directs the type of report to
     * create.
     *
     * @param code The student's graduation program code.
     */
    private void setGraduationProgramCode(final GraduationProgramCode code) {
        this.graduationProgramCode = code;
    }

    /**
     * Sets information to use for filling various reports.
     *
     * @param achievement Student transcript information (e.g., issue date and
     * transcript result list).
     */
    @Override
    public void setAchievement(final ca.bc.gov.educ.grad.report.model.achievement.Achievement achievement) {
        ensureValidStudent("setAchievement");
        BusinessEntityAdapter.adapt(achievement, getStudent());
        setReportDate(achievement.getIssueDate());
    }

    /**
     * Sets the graduation program (code and description) from which the student
     * might have graduated. If the student didn't graduate from this program,
     * then the graduation message text contains the reason.
     *
     * @param gradProgram The graduation program code and description.
     */
    @Override
    public void setGraduationProgram(final GradProgram gradProgram) {
        setGraduationProgramCode(gradProgram.getCode());
    }

    /**
     * Associates a list of non-grad reasons with a student.
     *
     * @param reasons The list of reasons the student has yet to graduate,
     * possibly an empty list, never null.
     * @param graduationMessageText Explains why the student graduated (or not).
     */
    @Override
    public void setGraduationStatus(final List<NonGradReason> reasons,
            final String graduationMessageText) {
        final Status status = adapt(reasons, graduationMessageText);
        //getStudent().setStatus(status);
    }

    /**
     * Returns the portion of the file name to add after the prefix but before
     * the filename extension. This is useful for when the same report type is
     * used but differs by some other element (such as the logo or school
     * district).
     *
     * @return Various report parameter values separated by hyphens.
     */
    @Override
    protected String getFilenameSuffix() {
        return new Date().toString();
    }

    /**
     * Returns true if the report is to be previewed for verification.
     *
     * @return true The report is unofficial.
     */
    public final boolean isPreview() {
        return this.preview;
    }

    /**
     * Sets the interim flag to true, which indicates that interim marks are to
     * be displayed on the report.
     *
     * @param interim Set the interim flag.
     */
    @Override
    public void setInterim(final boolean interim) {
        this.interim = interim;
    }

    /**
     * Returns true if the report should include the interim marks columns. This
     * value is only valid for HTML versions of the reports.
     *
     * @return false Do not include interim marks.
     */
    public final boolean isInterim() {
        return this.interim;
    }

    /**
     * Sets whether the report should be made print-ready in an official
     * capacity.
     *
     * @param preview false The report must be ready for print on official
     * transcript paper.
     */
    @Override
    public void setPreview(final boolean preview) {
        this.preview = preview;
    }

    /**
     * Inject the P_REPORT_PREVIEW parameter. Not used by certificate reports
     * but passed into the report anyway.
     */
    @Override
    protected void preprocessParameters() {
        super.preprocessParameters();
        setParameter("P_REPORT_PREVIEW", isPreview());

        if (!isFormat(XML)) {
            setParameter(P_REPORT_TYPE, getReportType());
        }
    }

    /**
     * Returns Transcript_GraduationProgramCode where GraduationProgramCode is
     * the code from the graduation program code enumeration instance associated
     * with the student.
     *
     * @return The report name.
     */
    @Override
    protected String getResourceBundleName() {
        // Get the report namem.
        return "StudentAchievement";
    }

    /**
     * Gets the base report name (e.g., "Transcript") with the desired suffixes
     * appended (e.g., "_HTML" or "_HTML_interim").
     *
     * @return The base report name, or the base report name with an HTML or
     * HTML/interim suffix appended.
     */
    @Override
    protected String getName() {
        return super.getName() + getReportTemplateFilenameSuffix();
    }

    /**
     * Returns the suffix used for the filename
     *
     * @return "", "_HTML", or "_HTML_interim", never null.
     */
    protected String getReportTemplateFilenameSuffix() {
        // 16 characters suffices for _HTML_interim.
        final StringBuilder suffix = new StringBuilder(16);

        if (isFormat(HTML)) {
            suffix.append(SUFFIX_HTML);
        }

        if (isInterim()) {
            suffix.append(SUFFIX_INTERIM);
        }

        // Could return the empty string.
        return suffix.toString();
    }

    /**
     * Returns a description of this report (name, media type, etc.).
     *
     * @return The report description.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(64);
        sb.append(super.toString());
        sb.append("; grad program: <");
        sb.append(getGraduationProgramCode());
        sb.append(">");

        return sb.toString();
    }
}
