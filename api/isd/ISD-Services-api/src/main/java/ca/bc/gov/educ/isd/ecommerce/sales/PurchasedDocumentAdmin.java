
/* *************************************************************************
 *   Copyright (c) 2015, Ministry of Education, BC.
 *
 *   All rights reserved.
 *     This information contained herein may not be used in whole
 *     or in part without the express written consent of the
 *     Government of British Columbia, Canada.
 *
 *   Revision Control Information
 *   File:                $Id::                                                 $
 *   Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *   Revision Number:     $Rev:: 36                                             $
 *   Last Commit by:      $Author:: bbates                                     $
 *
 *
 **********************************************************************  */
package ca.bc.gov.educ.isd.ecommerce.sales;

import ca.bc.gov.educ.isd.reports.ReportFormat;
import java.util.Date;

/**
 *
 * @author Ministry of Education, BC
 */
public interface PurchasedDocumentAdmin extends PurchasedDocument {

    void setData(byte[] data);

    void setFileName(String fileName);

    void setApprovedOn(Date approvedOn);

    void setSentOn(Date sentOn);

    void setGeneratedOn(Date generatedOn);

    void setMediaType(ReportFormat mediaType);

    void setName(String name);

}
