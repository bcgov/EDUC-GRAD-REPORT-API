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
 *  File:                $Id:: AbstractPredicate.java 6655 2017-03-27 23:12:43#$
 *  Date of Last Commit: $Date:: 2017-03-27 16:12:43 -0700 (Mon, 27 Mar 2017)  $
 *  Revision Number:     $Rev:: 6655                                           $
 *  Last Commit by:      $Author:: DAJARVIS                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.common.support.filter;

import ca.bc.gov.educ.isd.common.Predicate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Extended by all Predicate subclasses.
 *
 * @author CGI Information Management Consultants Inc.
 * @param <C> The type of collection to filter.
 */
public abstract class AbstractPredicate<C> implements Predicate<C>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Filters the given collection without modification.
     *
     * @param target The collection to filter.
     * @return The given collection after the filter has been applied.
     */
    @Override
    public Collection<C> filter(final Collection<C> target) {
        final Collection<C> result = createCollection(target.size());

        for (final C element : target) {
            if (evaluate(element)) {
                result.add(element);
            }
        }

        return result;
    }

    /**
     * Creates the collection wherein the filtered items are placed with a
     * default capacity of 16 elements.
     *
     * @return An empty array list by default.
     */
    protected Collection<C> createCollection() {
        return createCollection(16);
    }

    /**
     * Creates the collection wherein the filtered items are placed.
     *
     * @param size The initial capacity.
     * @return An empty array list by default.
     */
    protected Collection<C> createCollection(final int size) {
        return new ArrayList<>(size);
    }
}
