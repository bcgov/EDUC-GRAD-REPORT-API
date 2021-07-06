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

import java.io.Serializable;
import javax.resource.Referenceable;
import javax.resource.cci.ConnectionFactory;
import javax.resource.spi.ManagedConnectionFactory;

/**
 * BCcampus Connection Factory encapsulates a set of connection configuration
 * parameters.
 */
public interface BCcampusConnectionFactory extends Serializable, Referenceable, ConnectionFactory {

    ManagedConnectionFactory getManagedConnectionFactory();
}
