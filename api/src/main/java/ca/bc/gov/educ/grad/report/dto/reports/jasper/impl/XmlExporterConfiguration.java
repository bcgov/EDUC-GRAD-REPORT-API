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
package ca.bc.gov.educ.grad.report.dto.reports.jasper.impl;

import ca.bc.gov.educ.grad.report.model.common.Predicate;
import net.sf.jasperreports.export.SimpleExporterConfiguration;

/**
 * Responsible for storing the predicate used in filtering parameter elements.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class XmlExporterConfiguration extends SimpleExporterConfiguration {

    private Predicate<String> predicate;

    public XmlExporterConfiguration(final Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public Predicate<String> getPredicate() {
        return this.predicate;
    }

    public void setPredicate(final Predicate<String> predicate) {
        this.predicate = predicate;
    }
}
