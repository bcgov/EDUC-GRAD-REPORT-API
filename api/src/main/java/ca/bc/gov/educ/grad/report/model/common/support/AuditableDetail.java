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
 *  File:                NewClass.java
 *  Date of Last Commit: $Date::                                               $ 
 *  Revision Number:     $Rev::                                                $ 
 *  Last Commit by:      $Author::                                             $
 *  
 * ***********************************************************************
 */

package ca.bc.gov.educ.grad.report.model.common.support;

/**
 * Marks a class as a detail in a master-detail relationship that can then be 
 * listened to by the <code>AuditEntityListener</code>.
 *
 * @author CGI Information Mnagement Consultans Inc.
 */
public interface AuditableDetail {

    /**
     * Returns the parent object so that the <code>AuditEntityListener</code>
     * can reuse the values of the audit fields.
     *
     * @return
     */
    public AbstractDomainEntity getParent();
}
