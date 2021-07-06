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
 *  File:                $Id:: TranscriptEntityService.java 7831 2017-08-03 17#$
 *  Date of Last Commit: $Date:: 2017-08-03 10:54:07 -0700 (Thu, 03 Aug 2017)  $
 *  Revision Number:     $Rev:: 7831                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.transcript;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.SearchResult;
import java.io.IOException;
import java.util.Map;
import javax.xml.bind.JAXBException;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TranscriptEntityService extends CommonEntityService<Transcript, SearchResult> {

    Map sendTranscript(String transcript, String psisCode) throws IOException, JAXBException;

    String getXMLTranscript(Object highSchoolTranscript) throws JAXBException;

    String checkSentTranscript(String documentId);

    String getBatchRequest();

}
