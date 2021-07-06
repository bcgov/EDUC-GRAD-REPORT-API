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
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.eis.roles;

/**
 * Non-instantiable utility class containing the Roles being used in the EIS API
 * implementation. Note that these should directly align with the Roles defined
 * at the Services API level that call out to EIS services. This duplication is
 * required to enforce the separation between the layers.
 */
public class Roles {

    private Roles() {
    }

    public static final String TRAX_READ = "TRAX_READ";
    public static final String USER = "USER";
    public static final String FULFILLMENT_SERVICES_USER = "FULFILLMENT_SERVICES_USER";
    public static final String PUBLIC_USER = "PUBLIC_USER";
}
