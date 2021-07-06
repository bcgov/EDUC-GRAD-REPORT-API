/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date:: 2016-09-19 14:41:55 -0700 (Mon, 19 Sep 2016)  $
 *  Revision Number:     $Rev:: 3663                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.task;

import static ca.bc.gov.educ.isd.common.Constants.DATE_UNAMBIGUOUS_YMD;

/**
 * Task and task management functions for general users.
 *
 * <p>
 * Lists all the attributes that TASKS are expecting for Sales Order issue. This
 * includes but is not exclusive to orders for physical transcripts and issues
 * with students PENs.
 * <p>
 * The task service manages the creation, reading, writing, searching, and
 * removal of tasks, where required these tasks are filtered by those assigned
 * to the current user.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TaskAttribute {

    //TODO: confirm date format
    String DATE_FORMAT = DATE_UNAMBIGUOUS_YMD;

	// defining field names for when used by search
	// would be possible to manage via reflection, but then get a performance
	// hit via reflection
	// do not expect field names to change.
	final String ID_FIELD = "id";
	final String TASK_TYPE_FIELD = "issueType";
	final String TASK_STATUS_FIELD = "taskStatus";
	final String CREATED_ON_FIELD = "createdOn";
	final String LAST_UPDATED_ON_FIELD = "lastUpdatedOn";
	final String LAST_UPDATED_BY_FIELD = "lastUpdatedBy";
	final String TASK_ATTRIBUTES_FIELD = "taskAttributes";

	// secondary attribute fields
	final String CREDIT_CARD = "creditCard";
	final String FIRST_NAME = "firstName";
	final String LAST_NAME = "lastName";

    interface NON_PEN_SALES_ORDER {

        String NUMBER_OF_TRANSCRIPTS = "numberOfTranscripts";
        String NUMBER_OF_CERTIFICATES = "numberOfCertificates";

        //The following is shared with All Sales Orders
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String EMAIL_ADDRESS = "email";
        String ORDER_ID = "orderId";
        String PAYMENT_METHOD = "paymentMethod";
        String ONLINE_TRANSACTION_ID = "onlineTransactionId";

        //The following is shared with GED Sales Order
        String MIDDLE_NAME = "middleName";
        String LAST_THREE_SIN = "lastThreeSIN";
        String FORMER_NAME = "formerName";
        String ADDRESS = "address";
        String CITY = "city";
        String COUNTRY = "country";
        String POSTAL_CODE = "postalCode";
        String BIRTH_DATE = "birthdate";
        String PHONE_NUMBER = "phoneNumber";

        String SECONDARY_SCHOOL = "secondarySchool";
        String FINAL_YEAR = "finalYear";
        String FINAL_GRADE = "finalGrade";
    }

    interface GED_SALES_ORDER {

        String SESSION_YEAR_GRADUATED = "sessionYearGed";
        String TEST_CENTER_NAME_GED = "testCentreNameGed";

        //The following is shared wtih All Sales Order
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String EMAIL_ADDRESS = "email";
        String ORDER_ID = "orderId";
        String PAYMENT_METHOD = "paymentMethod";
        String ONLINE_TRANSACTION_ID = "onlineTransactionId";

        //The following is shared with Non PEN Sales Order
        String MIDDLE_NAME = "middleName";
        String LAST_THREE_SIN = "lastThreeSIN";
        String FORMER_NAME = "formerName";
        String ADDRESS = "address";
        String CITY = "city";
        String COUNTRY = "country";
        String POSTAL_CODE = "postalCode";
        String BIRTH_DATE = "birthdate";
        String PHONE_NUMBER = "phoneNumber";
    }

    interface PEN_SALES_ORDER {

        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String EMAIL_ADDRESS = "email";
        String ORDERID = "orderId";
        String PAYMENT_METHOD = "paymentMethod";
    }

    interface INCOMPLETE_REGISTRATION {

        String FIRST_NAME = "firstName";
        String MIDDLE_NAME = "middleName";
        String LAST_NAME = "lastName";
        String EMAIL_ADDRESS = "email";
        String BIRTH_DATE = "birthdate";
        String PEN = "pen";
        String PHONE_NUMBER = "phoneNumber";
        String SEC_SCHOOL_NAME = "secondarySchoolName";
        String SEC_SCHOOL_YEAR = "secondarySchoolYear";
        String SEC_SCHOOL_GRADE = "secondarySchoolGrade";
        String PROFILE_ID = "profileEid";
    }

    interface REMOTE_ACCESS_GRADE_REG {

        String FIRST_NAME = "firstName";
        String MIDDLE_NAME = "middleName";
        String LAST_NAME = "lastName";
        String EMAIL_ADDRESS = "email";
        String PEN = "pen";
    }
}
