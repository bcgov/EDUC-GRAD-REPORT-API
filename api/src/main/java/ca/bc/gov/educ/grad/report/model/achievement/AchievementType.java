/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: CertificateType.java 5479 2016-12-01 23:4#$
 *  Date of Last Commit: $Date:: 2016-12-01 15:43:31 -0800 (Thu, 01 Dec 2016)  $
 *  Revision Number:     $Rev:: 5479                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.grad.report.model.achievement;

import ca.bc.gov.educ.grad.report.model.reports.PaperType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the classifications of certificates. This is used in combination
 * with CertificateReportSubtype to select the certificate to generate.
 *
 * @author CGI Information Management Consultants Inc.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)

public enum AchievementType {

    DEFAULT("Default", PaperType.ACHIEVEMENT);

    private String reportName;
    private PaperType paperType;

    AchievementType() {
    }

    /**
     * Constructs a new enumerated type for a certificate.
     *
     * @param reportName Certificate subreport template filename.
     */
    AchievementType(final String reportName, final PaperType paperType) {

        this.reportName = reportName;
        this.paperType = paperType;
    }

    /**
     * Returns the paper type required for printing the certificate.
     *
     * @return A non-null instance for controlling a Xerox printer.
     */
    public PaperType getPaperType() {
        return this.paperType;
    }

    /**
     * Returns the file name for this certificate report type.
     *
     * @return The report file name.
     */
    @Override
    public String toString() {
        return this.reportName;
    }

    public String getReportName() {
        return reportName;
    }
  
    void setReportName(String reportName) {
        this.reportName = reportName;
    }

    void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    @JsonCreator
    public static AchievementType forValue(@JsonProperty("reportName") final String reportName) {
        for (AchievementType certificateType : AchievementType.values()) {
            if (certificateType.getReportName().equals(reportName)) {
                return certificateType;
            }
        }
        return null;
    }
}
