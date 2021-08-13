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
package ca.bc.gov.educ.isd.reports.jasper.impl;

import net.sf.jasperreports.engine.ReportContext;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Implementation to contain a data source outside of the parameter map to be
 * used in marshaling.
 *
 */
public class XmlReportContext implements ReportContext {

    private Object dataSource;

    private Map<String, Object> linkedParameters;

    private final String id = System.currentTimeMillis()
            + "_" + System.identityHashCode(this)
            + "_" + (new Random()).nextInt();

    public XmlReportContext() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Object getParameterValue(String parameterName) {
        return getParameterValues().get(parameterName);
    }

    @Override
    public void setParameterValue(String parameterName, Object value) {
        getParameterValues().put(parameterName, value);
    }

    @Override
    public boolean containsParameter(String parameterName) {
        return getParameterValues().containsKey(parameterName);
    }

    public Object getDataSource() {
        return dataSource;
    }

    public void setDataSource(Object dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Map<String, Object> getParameterValues() {
        if (this.linkedParameters == null) {
            this.linkedParameters = createParameterMap();
        }

        return this.linkedParameters;
    }

    protected Map<String, Object> createParameterMap() {
        return new LinkedHashMap<>();
    }

    @Override
    public Object removeParameterValue(final String key) {
        return getParameterValues().remove(key);
    }

    @Override
    public void clearParameterValues() {
        getParameterValues().clear();
    }

}
