/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                $Id:: Identifier.java 8289 2017-09-26 23:04:07Z CGOME#$
 *  Date of Last Commit: $Date:: 2017-09-26 16:04:07 -0700 (Tue, 26 Sep 2017)  $
 *  Revision Number:     $Rev:: 8289                                           $
 *  Last Commit by:      $Author:: CGOMEZTE                                    $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.common.party;

import ca.bc.gov.educ.isd.common.DomainEntity;

/**
 * Known <b>Entity Identifier</b> of the Party in this or other systems
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Identifier extends DomainEntity {

    /**
     * The identifier type for a single sign-on identifier
     */
    String SSO_GUID_IDENTIFIER_TYPE = "SSO_GUI";

    /**
     * Gets the EntityId of the organization that issued the identity.
     *
     * @return Organization which issues this type of identity.
     */
    String getIssuedByOrganizationEntityId();

    /**
     * Sets the EntityId of the organization that issued the identity.
     *
     * @param issuedByOrganizationEntityId Organization which issues this type
     * of identity.
     */
    void setIssuedByOrganizationEntityId(final String issuedByOrganizationEntityId);

    /**
     * Gets the name code.
     *
     * @return
     */
    String getNameCode();

    /**
     * Sets the name code.
     *
     * @param nameCode Name of this identity
     */
    void setNameCode(final String nameCode);

    /**
     * Returns the identifier value.
     *
     * @return
     */
    String getValue();

    /**
     * Gets the type code.
     *
     * @return
     */
    String getTypeCode();

    /**
     * Sets the type code.
     *
     * @param typeCode Type code of this identity
     */
    void setTypeCode(final String typeCode);

    /**
     * Indicates whether or not this identifier is currently active
     *
     * @return
     */
    boolean isActive();

}
