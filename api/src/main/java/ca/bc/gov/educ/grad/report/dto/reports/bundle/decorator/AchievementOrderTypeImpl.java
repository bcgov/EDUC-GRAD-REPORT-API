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
 *  File:                $Id:: CertificateOrderTypeImpl.java 5479 2016-12-01 2#$
 *  Date of Last Commit: $Date:: 2016-12-01 15:43:31 -0800 (Thu, 01 Dec 2016)  $
 *  Revision Number:     $Rev:: 5479                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import ca.bc.gov.educ.grad.report.model.achievement.AchievementOrderType;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementType;

/**
 * Responsible for creating order types that can print certificates on the
 * correct paper type.
 *
 * @author CGI Information Management Consultants Inc.
 */

public class AchievementOrderTypeImpl extends OrderTypeImpl
        implements AchievementOrderType {

    private static final long serialVersionUID = 3L;

    private String name;
    private AchievementType achievementType;

    public AchievementOrderTypeImpl() {
    }

    /**
     * Constructs with paper type based on the certificate that was ordered.
     *
     * @param achievementType Type of certificate ordered.
     */
    public AchievementOrderTypeImpl(final AchievementType achievementType) {
        this.achievementType = achievementType;
        setPaperType(achievementType.getPaperType());
    }

    /**
     * Returns the human-readable name for certificates.
     *
     * @return "Certificates"
     */
    @Override
    public String getName() {
        return "Achievements";
    }

    public void setName(String name) {
        this.name = name;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(AchievementType achievementType) {
        this.achievementType = achievementType;
        this.setPaperType(this.achievementType.getPaperType());
    }

    @Override
    public String toString() {
        return "AchievementOrderTypeImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
