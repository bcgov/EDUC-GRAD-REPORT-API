/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: BCcampusService.java 11141 2018-08-20 20:02:51Z#$
 *  Date of Last Commit: $Date:: 2018-08-20 13:02:51 -0700 (Mon, 20 Aug 2018)  $
 *  Revision Number:     $Rev:: 11141                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.bccampus;

import ca.bc.gov.educ.isd.eis.EISException;
import java.io.IOException;
import java.io.Serializable;

/**
 * BCcampusService services distribute transcripts to participating post
 * secondary institutions.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCcampusService extends Serializable {

    /**
     * POST a transcript to BCcampus
     *
     * @param content byte[] of the PESC XML transcript.
     * @return ID string of acknowledgment
     * @throws java.io.IOException
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    String send(byte[] content) throws IOException, EISException;

    /**
     * GET a batch of requests and/or acknowledgments from BCcampus.
     *
     * @param batchUUID Unique identifier used to fetch a batch of requests.
     * @return Batch of requests from BCcampus if available.
     * @throws java.io.IOException
     */
    DocumentFragmentParser getRequestBatch(String batchUUID) throws Exception;

    /**
     * GET the status of a previously sent transcript.
     *
     * @param documentID Unique identifier of the transcript previously sent.
     * @return command results
     * @throws java.io.IOException
     */
    String getTranscriptStatus(String documentID) throws Exception;

    /**
     * Verify whether BCcampus is accepting incoming connections.
     *
     * @return Boolean if the connection is receiving.
     * @throws ca.bc.gov.educ.isd.eis.EISException
     */
    boolean verifyConnection() throws EISException;

}
