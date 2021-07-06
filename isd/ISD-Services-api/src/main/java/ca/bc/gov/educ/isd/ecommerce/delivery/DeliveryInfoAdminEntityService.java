
/**
 * ***********************************************************************
 * Copyright (c) 2016, Ministry of Education, BC.
 *
 * All rights reserved. This information contained herein may not be used in
 * whole or in part without the express written consent of the Government of
 * British Columbia, Canada.
 *
 *
 * Revision Control Information File: DeliveryInfoAdminEntityService.java Date
 * of Last Commit: $Date:: 2016-09-22 07:53:27 -0700 (Thu, 22 Sep 2016) $
 * Revision Number: $Rev:: 37#$ Last Commit by: $Author:: cprince $
 * 
**************************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.delivery;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import ca.bc.gov.educ.isd.common.SearchObject;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.util.List;

/**
 * Administrative service for manipulating Delivery Information data.
 *
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface DeliveryInfoAdminEntityService extends CommonEntityService<DeliveryInfoAdmin, SearchResult> {

    DownloadDeliveryInfoAdmin createDownloadDeliveryInfoAdmin() throws DomainServiceException, DataException;

    List<DownloadDeliveryInfoAdmin> searchDownloadDeliveryInfoAdmin(List<SearchObject> criteria) throws DomainServiceException, DataException;

    ManualDeliveryInfoAdmin createManualDeliveryInfoAdmin() throws DomainServiceException, DataException;

    List<ManualDeliveryInfoAdmin> searchManualDeliveryInfoAdmin(List<SearchObject> criteria) throws DomainServiceException, DataException;

    PSIDeliveryInfoAdmin createPSIDeliveryInfoAdmin() throws DomainServiceException, DataException;

    List<PSIDeliveryInfoAdmin> searchPSIDeliveryInfoAdmin(List<SearchObject> criteria) throws DomainServiceException, DataException;

    /**
     * Create a Postal Mail Delivery Info (Mailing Address).
     *
     * @return
     * @throws DomainServiceException
     * @throws DataException
     */
    PostalDeliveryInfoAdmin createPostalDeliveryInfoAdmin() throws DomainServiceException, DataException;

    List<PostalDeliveryInfoAdmin> searchPostalDeliveryInfoAdmin(List<SearchObject> criteria) throws DomainServiceException, DataException;

}
