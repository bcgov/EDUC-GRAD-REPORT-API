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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.adaptor.dao.impl;

import java.io.Serializable;

/**
 * An auto generated entity for the prov_exam_view which is a view created in
 * the ISD database and linked to the TRAX database prov_exam table using the
 * database link. An embedded composite primary key has been added since this
 * data does not have a unique primary key column. The named query
 * ProvExamEntity.findByKey has been added to allow a jpql query to perform a
 * lookup using the composite primary key. NOTE: using EntityManager.find
 * returns a null entity so jpql is required. The view definition is as follows:
 *
 * <code>
 * CREATE OR REPLACE FORCE VIEW "ISD"."PROV_EXAM_VW" ("STUD_NO", "CRSE_CODE",
 * "CRSE_LEVEL", "CRSE_SESSION", "USED_FOR_GRAD") AS (select stud_no, crse_code,
 * crse_level, crse_session, used_for_grad from prov_exam@traxlink.world) with
 * read only;</code>
 *
 * JPA access is on attributes directly. The attributes in this entity are used
 * to fulfill data requests for the Transcript, XML Transcript, student
 * demographic services.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class ProvExamEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private CourseId primaryKey;
    private Character usedForGrad;

    public ProvExamEntity() {
    }

    /**
     * get the entity primary key. It is a compound key of type CourseId.
     *
     * @return entity primary key object
     */
    public CourseId getPrimaryKey() {
        return primaryKey;
    }

    public Character getUsedForGrad() {
        return usedForGrad;
    }
}
