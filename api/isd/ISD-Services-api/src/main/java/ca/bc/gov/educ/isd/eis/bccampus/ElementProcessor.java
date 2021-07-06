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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.bccampus;

import java.util.Map;

/**
 * The element processor contains all the data fetched from a document fragment.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface ElementProcessor {

    /**
     * This method returns True when the processor has handled the targeted XML
     * element's closing tag, and the caller must consumes the collected data of
     * this processor right the way before any further XML parsing operation.
     *
     * @return True if XML data ready to be consumed
     */
    public boolean isDataCollected();

    /**
     * Process takes the current path and checks if the processor is interested
     * in the path. If the current path matches a value in the element map then
     * the current value will be copied to the internal data structure.
     *
     * @param elementPath
     * @param elementValue
     * @param elementNamespace
     */
    public void process(String elementPath, String elementValue, String elementNamespace);

    /**
     * This map contains all the fetched values from the document fragment.
     *
     * @return the fetched values from parsing.
     */
    Map<String, String> getElementDataMap();

    /**
     * Return True to tell the controller to reset this processor. Usually
     * happens while a processor just complete processing the element it
     * interested.
     *
     * @return True to tell the controller to replace this instance with a new
     * processor.
     */
    boolean getCanResetProcessor();

    /**
     * Reset the collected XML values. And keeps the element key map untouched.
     */
    void resetCollectedValues();
}
