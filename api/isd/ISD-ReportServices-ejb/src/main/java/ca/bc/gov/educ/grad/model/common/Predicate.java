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
 *  File:                $Id:: Predicate.java 5861 2017-01-18 21:22:27Z matalb#$
 *  Date of Last Commit: $Date:: 2017-01-18 13:22:27 -0800 (Wed, 18 Jan 2017)  $
 *  Revision Number:     $Rev:: 5861                                           $
 *  Last Commit by:      $Author:: matalbot                                    $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.grad.model.common;

import java.util.Collection;

/**
 * Defines a method that can evaluate whether a given parameter passes a test.
 * The test is defined in classes that implement this interface.
 *
 * @author CGI Information Management Consultants Inc.
 * @param <C> The type of collection to filter.
 */
public interface Predicate<C> {

    /**
     * Use the specified parameter to perform a test that returns true or false.
     *
     * @param c The collection element to evaluate, should not be changed.
     * @return true The object has passed the test.
     */
    public boolean evaluate(C c);

    /**
     * Filters the given collection without modification.
     *
     * @param target The collection to filter.
     * @return The given collection after the filter has been applied.
     */
    public Collection<C> filter(Collection<C> target);
}
