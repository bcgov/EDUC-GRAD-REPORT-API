/*
 * *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *
 *  Revision Control Information
 *  File:                BCcampusConnectionFactory.java
 *  Date of Last Commit: $Date:: 2016-JUL-10                                   $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author:: cagomez                                     $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.bcmailplus;

import java.io.Serializable;
import javax.resource.Referenceable;
import javax.resource.ResourceException;
import javax.resource.cci.Connection;
import javax.resource.cci.ConnectionFactory;
import javax.resource.spi.ManagedConnectionFactory;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCMailPlusConnectionFactory extends Serializable, Referenceable, ConnectionFactory {

    /**
     * Get connection from factory.
     * <p>
     * @return BCMailPlus instance
     * <p>
     * @exception ResourceException Thrown if a connection can't be obtained
     */
    @Override
    Connection getConnection() throws ResourceException;

    ManagedConnectionFactory getManagedConnectionFactory();
}
