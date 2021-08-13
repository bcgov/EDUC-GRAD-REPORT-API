/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 * 
 *  Revision Control Information
 *  File:                TRAXConnection.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.trax;

import javax.resource.cci.Connection;

/**
 * Methods to interact with the TRAXConnection once it is established.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TRAXConnection extends Connection, TRAXAdaptor {

    /**
     *
     * Send the command and return the response.
     *
     * @param command command to execute
     *
     * @return command results
     */
    String sendCommand(String command);

    /**
     *
     * Close the connection.
     */
    @Override
    void close();

}
