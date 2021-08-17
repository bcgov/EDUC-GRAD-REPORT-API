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
package ca.bc.gov.educ.isd.reports.admin.impl;

import ca.bc.gov.educ.isd.reports.admin.data.NonPENUserOrder;

import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public class NonPENUserOrderImpl implements NonPENUserOrder {

    private static final long serialVersionUID = 5L;

    private static final String CLASSNAME = NonPENUserOrderImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private Date ordered;
    private String delivery;
    private Integer orders;
    private Double cost;

    @Override
    public void setOrdered(final Date ordered) {
        this.ordered = ordered;
    }

    @Override
    public Date getOrdered() {
        return this.ordered;
    }

    @Override
    public void setDelivery(final String delivery) {
        this.delivery = delivery;
    }

    @Override
    public String getDelivery() {
        return this.delivery;
    }

    @Override
    public void setOrders(final Integer orders) {
        this.orders = orders;
    }

    @Override
    public Integer getOrders() {
        return this.orders;
    }

    @Override
    public void setCost(final Double cost) {
        this.cost = cost;
    }

    @Override
    public Double getCost() {
        return this.cost;
    }
}
