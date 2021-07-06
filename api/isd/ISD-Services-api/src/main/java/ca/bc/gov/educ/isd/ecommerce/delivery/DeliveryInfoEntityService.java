/*
 * *********************************************************************
 *  Copyright (c) 2017, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: DeliveryInfoEntityService.java 7677 2017-07-08 #$
 *  Date of Last Commit: $Date:: 2017-07-07 17:27:49 -0700 (Fri, 07 Jul 2017)  $
 *  Revision Number:     $Rev:: 7677                                           $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.delivery;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchObject;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.List;

/**
 * non-Administrative / readonly service for manipulating Delivery Information
 * data.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DeliveryInfoEntityService extends CommonEntityService<DeliveryInfo, SearchResult> {

    /**
     * Delivery Info it self cannot be created, only one of its subclasses can
     * be created.
     *
     * <p>
     * Use one of the other create methods to get a specific sub-class of
     * Delivery Information.
     *
     * @throws DomainServiceException in all cases.
     */
    @Override
    DeliveryInfo create() throws DomainServiceException;

    DownloadDeliveryInfo createDownloadDeliveryInfo() throws DomainServiceException, DataException;

    List<DownloadDeliveryInfo> searchDownloadDeliveryInfo(List<SearchObject> criteria) throws DomainServiceException, DataException;

    ManualDeliveryInfo createManualDeliveryInfo() throws DomainServiceException, DataException;

    List<ManualDeliveryInfo> searchManualDeliveryInfo(List<SearchObject> criteria) throws DomainServiceException, DataException;

    PSIDeliveryInfo createPSIDeliveryInfo() throws DomainServiceException, DataException;

    List<PSIDeliveryInfo> searchPSIDeliveryInfo(List<SearchObject> criteria) throws DomainServiceException, DataException;

    /**
     * Searches for PSI delivery information given a PSI code.
     *
     * @param psiCode The PSI code to use to look up the PSI delivery details.
     * @return A non-null instance.
     * @throws DomainServiceException Could not find the PSI code.
     * @throws DataException
     */
    PSIDeliveryInfo searchPSIDeliveryInfo(String psiCode) throws DomainServiceException, DataException;

    /**
     * Create a Postal Mail Delivery Info (Mailing Address).
     *
     * @return
     * @throws DomainServiceException
     * @throws DataException
     */
    PostalDeliveryInfo createPostalDeliveryInfo() throws DomainServiceException, DataException;

    List<PostalDeliveryInfo> searchPostalDeliveryInfo(List<SearchObject> criteria) throws DomainServiceException, DataException;

}
