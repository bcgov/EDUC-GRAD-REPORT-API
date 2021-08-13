/**
 * ***********************************************************************
 * Copyright (c) 2016, Ministry of Education, BC.
 *
 * All rights reserved. This information contained herein may not be used in
 * whole or in part without the express written consent of the Government of
 * British Columbia, Canada.
 *
 *
 * Revision Control Information File: GEDOrderAdmin.java Date of Last Commit:
 * $Date:: 2016-11-24 19:50:28 -0800 (Thu, 24 Nov 2016) $ Revision Number:
 * $Rev:: 53#$ Last Commit by: $Author:: bbates  $
 * 
**************************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.sales.ged;

import ca.bc.gov.educ.isd.ecommerce.sales.SalesOrderStatusEnum;
import java.math.BigDecimal;

/**
 * an administrative version of the GED Order from.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface GEDOrderAdmin extends GEDOrder {

    void setStatus(SalesOrderStatusEnum status);

    void setTotalAmount(BigDecimal totalAmount);
}
