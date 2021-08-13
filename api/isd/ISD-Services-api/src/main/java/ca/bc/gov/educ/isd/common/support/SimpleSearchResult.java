/**
 * ********************************************************************
 * Copyright (c) 2016, Ministry of Education, BC.
 *
 * All rights reserved. This information contained herein may not be used in
 * whole or in part without the express written consent of the Government of
 * British Columbia, Canada.
 *
 * Revision Control Information File: SimpleSearchResult.java Date of Last
 * Commit: $Date:: $ Revision Number: $Rev:: $ Last Commit by: $Author:: $
 *
 * **********************************************************************
 */
package ca.bc.gov.educ.isd.common.support;

import ca.bc.gov.educ.isd.common.SearchResult;

/**
 * A simple search result for those views that do not need any additional
 * fields.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class SimpleSearchResult implements SearchResult {

    private Long id;
    private String entityId;
    private String friendlyName;

    public SimpleSearchResult() {
    }

    public SimpleSearchResult(Long id, String entityId, String friendlyName) {
        this.id = id;
        this.entityId = entityId;
        this.friendlyName = friendlyName;
    }

    public String getEntityId() {
        return this.entityId;
    }

    @Override
    public String getFriendlyName() {
        return this.friendlyName;
    }

    @Override
    public Long getId() {
        return this.id;
    }

}
