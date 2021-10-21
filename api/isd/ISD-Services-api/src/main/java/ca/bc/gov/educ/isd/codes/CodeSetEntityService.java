/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        CodeSetService.java
 *  Date of Last Commit: $Date:: 2018-05-18 08:40:07 -0700 (Fri, 18 May 2018)  $
 *  Revision Number:     $Rev:: 10208                                          $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.codes;

import ca.bc.gov.educ.isd.common.CommonEntityService;
import ca.bc.gov.educ.isd.common.DomainServiceException;

import java.util.List;

/**
 * A SIF-compatible Code Set Service.
 *
 * @see https://en.wikipedia.org/wiki/Schools_Interoperability_Framework
 * @author CGI Information Management Consultants Inc.
 * @version 0.1
 * @since 0.1
 */
public interface CodeSetEntityService extends CommonEntityService<CodeSet, CodeSetSearchResult> {

    /**
     * Retrieves a list of {@code CodeSet} as well as their active codes for the
     * names, namespace and version given in the input code set list.
     *
     * @param codesets A list of {@code CodeSet} providing the name, namespace
     * and version of the code set being interested.
     * @return The list of {@code CodeSet} results.
     * @throws DomainServiceException If any error encountered during retrieving
     * the data.
     */
    List<CodeSet> getCodeSets(
            final List<CodeSet> codesets) throws DomainServiceException;

    /**
     * Finds the code set associated with a particular code set key.
     *
     * @param codeSetKey Code set key name to query for a particular list of
     * code sets.
     * @return The code set for a given code set key.
     * @throws DomainServiceException Could not establish a database connection.
     */
    CodeSet find(final CodeSetEnum codeSetKey) throws DomainServiceException;

    /**
     * Finds the value associated with a code set entity item.
     *
     * @param codeSetKey The set of codes to query for a particular code value.
     * @param codeEntityKey The code value key.
     * @return The code text value for the given codeSet and entity, otherwise
     * the defaultValue if not found.
     * @throws DomainServiceException Could not establish a database connection.
     */
    String find(
            final CodeSetEnum codeSetKey,
            final CodeEntityEnum codeEntityKey) throws DomainServiceException;
}
