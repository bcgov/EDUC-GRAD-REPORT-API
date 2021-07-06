/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 * 
 *  Revision Control Information
 *  File:                TRAXConnectionFactory.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.trax;

import java.io.Serializable;
import javax.resource.Referenceable;
import javax.resource.ResourceException;
import javax.resource.cci.ConnectionFactory;
import javax.resource.spi.ManagedConnectionFactory;

/**
 * TRAX Connection Factory.
 *
 */
public interface TRAXConnectionFactory extends Serializable, Referenceable, ConnectionFactory {

    /**
     * Get connection from factory.
     *
     * @return TRAXConnection instance
     * @exception ResourceException Thrown if a connection can't be obtained
     */
    @Override
    TRAXConnection getConnection() throws ResourceException;

    ManagedConnectionFactory getManagedConnectionFactory();

}
