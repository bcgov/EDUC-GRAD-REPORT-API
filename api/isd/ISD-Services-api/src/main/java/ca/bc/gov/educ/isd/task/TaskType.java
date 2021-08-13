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
 *  File:                $Id:: TaskType.java 8986 2017-12-09 00:52:46Z cfunk   $
 *  Date of Last Commit: $Date:: 2017-12-08 16:52:46 -0800 (Fri, 08 Dec 2017)  $
 *  Revision Number:     $Rev:: 8986                                           $
 *  Last Commit by:      $Author:: cfunk                                       $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.task;

/**
 * Represents various types of tasks. CC means Credit Card, NCC means non-Credit
 * Card orders, and TRANS_CERT means transcript or certificate.
 *
 * @author CGI Information Management Consultants Inc.
 */
public enum TaskType {

    GED_ORDER_CC("GED\u00AE Order (Online Payment)"),
    GED_ORDER_NCC("GED\u00AE Order (Offline Payment)"),
    NON_PEN_TRANS_CERT_CC("Non-PEN Transcript/Certificate (Online Payment)"),
    NON_PEN_TRANS_CERT_NCC("Non-PEN Transcript/Certificate (Offline Payment)"),
    PEN_TRANS_CERT_NCC("PEN Transcript/Certificate (Offline Payment)"),
    INCOMPLETE_REGISTRATION("Incomplete Registration"),
    RESTRICTED_ACCESS("Restricted Access"),
    // [ST-1428: Remove creation of PSI Grade Restriction task]
    // This task type is no longer useful but needed for displaying the tasks created before
    SENDING_PSI_GRADE_RESTRICTION("Sending to PSI Grade Restriction"),
	CLEAR_REGISTRATION("Incomplete Registration: Status M or D");

	final static public TaskType[] CREDIT_CARD = {GED_ORDER_CC, NON_PEN_TRANS_CERT_CC};
	final static public TaskType[] NON_CREDIT_CARD = {GED_ORDER_NCC, NON_PEN_TRANS_CERT_NCC, PEN_TRANS_CERT_NCC};

    private final String description;

    /**
     * Constructs a new enumeration with a human-readable string.
     *
     * @param description Human-readable text description for the enumeration.
     */
    TaskType(final String description) {
        this.description = description;
    }

    /**
     * Returns the human-readable text for this task type.
     *
     * @return A non-null string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Answers whether the given description matches this value's description.
     * This performs a case-sensitive comparison.
     *
     * @param description The description to compare against.
     * @return true The descriptions match equally.
     */
    public boolean isDescription(final String description) {
        return this.description.equals(description);
    }

    /**
     * Converts a string description to an enum instance.
     *
     * @param v The description to convert to an enum instance.
     * @return The given description converted to an enum instance.
     * @throws IllegalArgumentException The description couldn't be found.
     *
     * @deprecated Use the enum directly without converting to string.
     */
    public static TaskType fromValue(final String v) {
        for (final TaskType c : TaskType.values()) {
            if (c.isDescription(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }

    /**
     * Answers whether this is a GED related task.
     *
     * @return true - the task type is for GED.
     */
    public boolean isGED() {
        return this == GED_ORDER_CC || this == GED_ORDER_NCC;
    }

    /**
     * Answers whether this is a non-PEN related task.
     *
     * @return true - the task type is for non-PEN.
     */
    public boolean isNonPEN() {
        return this == NON_PEN_TRANS_CERT_CC || this == NON_PEN_TRANS_CERT_NCC;
    }

    /**
     * Returns true iff this is a credit card order.
     *
     * TODO: Is this online/offline payment, rather than credit card
     * determinate? (To ponder.)
     *
     * @return false This is not a credit card order.
     */
	public boolean isCreditCard() {
		boolean retValue = false;
		for (TaskType CREDIT_CARD1 : CREDIT_CARD) {
			if (this == CREDIT_CARD1) {
				retValue = true;
				break;
			}
		}
		return retValue;
    }

    /**
     * Returns true iff this is a non-credit card order.
     *
     * @return false This is not a non-credit card order.
     */
	public boolean isNonCreditCard() {
		boolean retValue = false;
		for (TaskType NON_CREDIT_CARD1 : NON_CREDIT_CARD) {
			if (this == NON_CREDIT_CARD1) {
				retValue = true;
				break;
			}
		}
		return retValue;
    }
}
