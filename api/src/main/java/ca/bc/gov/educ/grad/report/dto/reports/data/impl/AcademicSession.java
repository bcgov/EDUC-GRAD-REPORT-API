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
 *  File:                AcademicSession.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.data.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants I
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AcademicSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private AcademicSessionDetail academicSessionDetail;

    @XmlElement(name = "course")
    private List<TranscriptResult> course = new ArrayList<>();

    @XmlElement(name = "achievement")
    private List<AchievementResult> achievement = new ArrayList<>();

    public AcademicSessionDetail getAcademicSessionDetail() {
        return academicSessionDetail;
    }

    public void setAcademicSessionDetail(final AcademicSessionDetail academicSessionDetail) {
        this.academicSessionDetail = academicSessionDetail;
    }

    public List<TranscriptResult> getCourse() {
        return course;
    }

    public void setCourse(final List<TranscriptResult> course) {
        this.course = course;
    }

    public List<AchievementResult> getAchievement() {
        return achievement;
    }

    public void setAchievement(final List<AchievementResult> achievement) {
        this.achievement = achievement;
    }

    /**
     * Helper method to add a transcript result to the student's list of
     * transcript results.
     *
     * @param transcriptResult The transcript result to add to the internal list
     * of transcript results.
     */
    public void addTranscriptResult(final TranscriptResult transcriptResult) {
        getCourse().add(transcriptResult);
    }

    public void addAchievementResult(final AchievementResult achievementResult) {
        getAchievement().add(achievementResult);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "academicSessionDetail=" + academicSessionDetail + ", course=" + course + '}';
    }
}
