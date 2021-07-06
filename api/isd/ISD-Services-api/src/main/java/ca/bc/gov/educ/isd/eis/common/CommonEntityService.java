/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        CommonEntityService.java 
 *  Date of Last Commit: $Date:: 2017-05-11 14:17:35 -0700 (Thu, 11 May 2017)  $  
 *  Revision Number:     $Rev:: 7094                                           $  
 *  Last Commit by:      $Author:: CGOMEZTE                                    $ 
 *  
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.common;

import java.util.List;

/**
 * A root interface of all services which manipulate domain entities (CRUD).
 *
 * <p>
 * This interface serves primarily to ensure that data access is accomplished in
 * a unified manner. This allows for implementers to more easily create
 * re-usable code. As well as ensuring the loose coupling of domain entities
 * between the service providers and service consumers.
 *
 * <p>
 * Note the <b>create()</b> method. In this model new instances of a domain
 * object can only be created by the Entity service. Since domain entities are
 * interfaces the consumer will have no knowledge of how the domain entity is
 * implemented. This allows the service provider to use anything from a simple
 * class to a dynamic and soft coded data structure.</p>
 * <p>
 * This also provides support for Data Version Transparency as described in
 * Normalized System Theory.</p>
 *
 *
 * @author CGI Information Management Consultants Inc.
 * @param <E> A domain entity that will be managed by this C.R.U.D. Service
 * @param <R> A container for search results which can be used to list entities.
 *
 * @version 0.2
 * @since 0.1
 *
 */
public interface CommonEntityService<E extends DomainEntity, R extends SearchResult> extends BusinessService {

    /**
     * Creates a empty and unsaved instance of the entity.
     *
     * The underlying concrete entity instance may include other information or
     * realize multiple versions of domain entity interface. However since this
     * is
     *
     * @return A new, empty, and unsaved entity.
     * @throws DataException if the entity instance cannot be created.
     */
    E create() throws DomainServiceException, DataException;

    /**
     * Remove, typically logically, the entity.
     *
     * @param entity the entity to be deleted.
     * @throws DataException if the entity cannot be deleted.
     */
    void remove(E entity) throws DomainServiceException, DataException;

    /**
     *
     * @param id Primary Key Identifier of the entity to be retrieved.
     * @return An instance of the unique non-null entity with the entity
     * identifier.
     * @throws DataException If there is an error accessing the data or if the
     * entity does not exist.
     */
    E read(Long id) throws DomainServiceException, DataException;

    /**
     *
     * @param entity The entity to be stored / or updated in the database.
     * @return The refreshed instance of the entity containing any changes cause
     * by the write. i.e. Primary Key or Trigger changes.
     * @throws DataException If the entity cannot be written or the database
     * cannot be accessed.
     */
    E write(E entity) throws DomainServiceException, DataException;

    /**
     * Search for an entity based on a List of SearchObjects.
     *
     * These fields and values will be used to form criteria for the underlying
     * search.
     *
     * @param criteria List of SearchObjects.
     * @return List of entities found to match the criteria.
     * @throws DataException If the database cannot be access or the criteria
     * map is in error.
     */
    List<E> search(List<SearchObject> criteria) throws DomainServiceException, DataException;

    /**
     * Paged search for an entity based on a set of fields and values.
     *
     * The paging is accomplished through a page number and page size parameters
     * as follows
     *
     * @see #search(java.util.Map)
     * @param criteria List of SearchObjects
     * @param pageSize The number of records on each page.
     * @param pageNum The page number to retrieve (determined by the page size)
     * @return
     * @throws DataException
     */
    List<R> search(List<SearchObject> criteria, Integer pageSize, Integer pageNum) throws DomainServiceException, DataException;

    /**
     * Count of entities based on a list of SearchObjects.
     *
     * @see #count(java.util.Map)
     * @param criteria Map of the field name search criteria pairs
     * @return
     * @throws DataException
     */
    Long count(List<SearchObject> criteria) throws DomainServiceException, DataException;

}
