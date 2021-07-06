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
 *  File:                $Id:: SearchResult.java 8289 2017-09-26 23:04:07Z CGO#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common;

/**
 * Search Result provides support for a generic which handles non-entity /
 * constructor queries.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface SearchResult {

    /**
     * Unique Identifier which can be used to lookup the underlying master
     * entity.
     *
     * @return Long Identifier for the real world object represented by this
     * search result.
     */
    Long getId();

    /**
     * A user friendly, displayable name for the search result.
     *
     * @return
     */
    String getFriendlyName();
}
