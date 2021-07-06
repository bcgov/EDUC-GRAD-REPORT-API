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
 *  File:                PescConstants.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.bccampus;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class PescConstants {

    public static final String FUNCTIONAL_ACK_XML_NAMESPACE = "FunctionalAcknowledgment";
    public static final String TRANSCRIPT_ACK_XML_NAMESPACE = "TranscriptAcknowledgment";

    public static final String BC_AUTH_EXPIRED = "BC: Authorization is Expired";
    public static final String BC_NOT_AUTH = "BC: Not Authorized";
    public static final String BC_INVALID_PEN = "BC: Invalid Student Identifier";
    public static final String BC_MIS_DOB = "BC: Mismatched Birthdate for Student Identifier";

    public static final String MINISTRY_ADDRESS = "620 Superior Street";
    public static final String MINISTRY_CITY = "Victoria";
    public static final String MINISTRY_PROV = "BC";
    public static final String MINISTRY_POSTAL = "V8V 1V2";

    public static final String INBOUND_DOC_CAUSE = "An error occurred when processing inbound documents from the hub.";

    public static final String ERROR_REPORTING_EMAIL = "java:/ISD-BCcampusAdaptor-EIS-rar/errorReportingEmail";

    public static final String CALLER_ID = "java:/ISD-BCcampusAdaptor-EIS-rar/callerId";

    public static final String BCCAMPUS_SERVICE_JNDI = "java:/eis/BCcampusConnectionFactory";

    public static final String ACADEMICRECORDBATCH = "AcademicRecordBatch";
    
    public static final String ACKNOWLEDGEMENT = "Acknowledgment";
    
    public static final String ACKNOWLEDGEMENT_DATA = "AcknowledgmentData";
    public static final String DOCUMENT_ID = "DocumentID";
    public static final String ACKNOWLEDGEMENT_CODE = "AcknowledgmentCode";

    public static final String TRANSMISSION_DATA = "TransmissionData";
    public static final String REQUEST_TRACK_ID = "RequestTrackingID";
    public static final String BIRTHDATE = "BirthDate";
    public static final String MIDDLENAME = "MiddleName";
    public static final String FIRSTNAME = "FirstName";
    public static final String LASTNAME = "LastName";
    public static final String PERSON = "Person";
    public static final String BIRTH = "Birth";
    public static final String NAME = "Name";

    public static final String TRANSCRIPT_REQUEST = "TranscriptRequest";
    public static final String SOURCE = "Source";
    public static final String ORGANIZATION = "Organization";
    public static final String PSIS = "PSIS";
    public static final String REQUEST = "Request";
    public static final String REQUESTED_STUDENT = "RequestedStudent";
    public static final String ATTENDANCE = "Attendance";
    public static final String AGENCY_IDENTIFIER = "AgencyIdentifier";
    public static final String AGENCY_ID = "AgencyAssignedID";
    public static final String SCHOOL = "School";
    public static final String ORGANIZATION_NAME = "OrganizationName";
}