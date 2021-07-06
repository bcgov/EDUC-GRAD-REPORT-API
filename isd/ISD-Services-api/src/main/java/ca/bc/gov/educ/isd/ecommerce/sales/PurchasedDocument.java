/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        PurchasedDocument.java
 *  Date of Last Commit: $Date:: 2018-10-03 17:05:03 -0700 (Wed, 03 Oct 2018)  $
 *  Revision Number:     $Rev:: 11214                                          $
 *  Last Commit by:      $Author:: catli                                       $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.common.DomainEntity;
import ca.bc.gov.educ.isd.reports.ReportFormat;
import java.util.Date;

/**
 * This is the interface for persistent PurchasedDocumentEntity bean
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PurchasedDocument extends DomainEntity {

    byte[] getData();

    String getFileName();

    Date getGeneratedOn();

    Date getSentOn();

    ReportFormat getMediaType();

    String getName();

}
