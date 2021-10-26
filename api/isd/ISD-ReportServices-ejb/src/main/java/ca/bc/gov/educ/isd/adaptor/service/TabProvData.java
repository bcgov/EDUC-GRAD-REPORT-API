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
package ca.bc.gov.educ.isd.adaptor.service;

import ca.bc.gov.educ.isd.eis.EISException;
import ca.bc.gov.educ.isd.eis.trax.db.TRAXData;
import ca.bc.gov.educ.isd.eis.trax.db.TabProvince;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TabProvData extends TRAXData, Serializable {

    public List<? extends TabProvince> findProvinceByCode(String provCode) throws EISException;

    public List<? extends TabProvince> findProvinceByName(String provName) throws EISException;

    public List<? extends TabProvince> findCountryProvinces(String cntryCode) throws EISException;

    public List<? extends TabProvince> findAll() throws EISException;

    public List<? extends TabProvince> search(String provCode, String provName, String cntryCode) throws EISException;
}
