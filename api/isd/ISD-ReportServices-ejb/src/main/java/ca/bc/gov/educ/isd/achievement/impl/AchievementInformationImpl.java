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
package ca.bc.gov.educ.isd.achievement.impl;

import ca.bc.gov.educ.isd.achievement.Achievement;
import ca.bc.gov.educ.isd.achievement.AchievementResult;
import ca.bc.gov.educ.isd.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.isd.grad.GraduationProgramCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Basic Achievement information without any courses - use isEmpty to determine
 * if actual Achievement would contain courses.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class AchievementInformationImpl extends AbstractDomainEntity
        implements Achievement, Serializable {

    private static final long serialVersionUID = 3L;

    private Date issueDate;
    private boolean isEmpty;

    public AchievementInformationImpl() {
    }

    public AchievementInformationImpl(final Date issueDate, final boolean isEmpty) {
        this.issueDate = issueDate;
        this.isEmpty = isEmpty;
    }

    /**
     * Throws an exception when called.
     *
     * @return Nothing.
     */
    @Override
    public List<AchievementResult> getResults() {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Throws an exception when called.
     *
     * @param code Not used.
     * @return Nothing.
     */
    @Override
    public List<AchievementResult> getResults(
            final GraduationProgramCode code) {
        return getResults();
    }

    @Override
    public Date getIssueDate() {
        return this.issueDate;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
