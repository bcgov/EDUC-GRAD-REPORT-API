/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: SelfServeProcess.java 7404 2017-06-06 23:39:04Z#$
 *  Date of Last Commit: $Date:: 2017-06-06 16:39:04 -0700 (Tue, 06 Jun 2017)  $
 *  Revision Number:     $Rev:: 7404                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common;

/**
 * A Workflow object for a end-user performing self-service. This work flow
 * object's initialization methods do not expect a user or student identity. The
 * identity of the workflow's primary participant (user) is must be determined
 * at runtime based on the currently logged-in user identity.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SelfServeProcess extends BusinessProcess {

    /**
     * Initialize the self-serve business process using the currently
     * authenticated user.
     *
     * The process may be partially completed in a previous user session, or may
     * have never been attempted. This method is used to check for an in-process
     * saved state, or initialize a new process instance.
     *
     * @throws ca.bc.gov.educ.isd.common.DomainServiceException
     */
    void initialize() throws DomainServiceException;

}
