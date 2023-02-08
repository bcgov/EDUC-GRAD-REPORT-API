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
 *  File:                $Id:: TranscriptOrderTypeImpl.java 5479 2016-12-01 23#$
 *  Date of Last Commit: $Date:: 2016-12-01 15:43:31 -0800 (Thu, 01 Dec 2016)  $
 *  Revision Number:     $Rev:: 5479                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator;

import ca.bc.gov.educ.grad.report.model.reports.PaperType;
import ca.bc.gov.educ.grad.report.model.school.SchoolReportOrderType;

/**
 * Responsible for creating order types that can print school report on the
 * correct paper type.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class SchoolReportOrderTypeImpl extends OrderTypeImpl
        implements SchoolReportOrderType {

    private static final long serialVersionUID = 3L;

    private String name = "School";

    /**
     * Constructs with paper type YED4.
     */
    public SchoolReportOrderTypeImpl() {
        setPaperType(PaperType.SCHOOL);
    }

    /**
     * Returns the human-readable name for transcripts.
     *
     * @return "School"
     */
    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
