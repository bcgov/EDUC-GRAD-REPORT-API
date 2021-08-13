
/**
 * ***********************************************************************
 * Copyright (c) 2016, Ministry of Education, BC.
 *
 * All rights reserved. This information contained herein may not be used in
 * whole or in part without the express written consent of the Government of
 * British Columbia, Canada.
 *
 *
 * Revision Control Information File: PaymentReceiptAdmin.java Date of Last
 * Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016) $ Revision
 * Number: $Rev:: 31#$ Last Commit by: $Author:: cprince $
 * 
**************************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.payment;

import java.util.Date;
import java.util.List;

/** Administration form for recording the receipt of payments.
 * 
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface PaymentReceiptAdmin extends PaymentReceipt {

    void setSerialNo(String serialNo);

    void setTransactionDate(Date xactDate);

    void setAmount(Double amount);

    void setPaymentStatus(List<PaymentStatus> paymentStatus);

}
