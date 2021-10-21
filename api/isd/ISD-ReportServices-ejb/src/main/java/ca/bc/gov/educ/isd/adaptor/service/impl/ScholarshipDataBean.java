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
package ca.bc.gov.educ.isd.adaptor.service.impl;

import ca.bc.gov.educ.isd.adaptor.impl.ScholarshipImpl;
import ca.bc.gov.educ.isd.adaptor.impl.ScholarshipStudentImpl;
import ca.bc.gov.educ.isd.adaptor.service.ScholarshipData;
import ca.bc.gov.educ.isd.eis.trax.db.Scholarship;
import ca.bc.gov.educ.isd.eis.trax.db.ScholarshipStudent;
import org.springframework.stereotype.Repository;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import static ca.bc.gov.educ.isd.eis.roles.Roles.TRAX_READ;

/**
 * This is an intermediate layer between the database entities and the unmanaged
 * data objects which are returned to the requesting services. It is a stateless
 * bean providing access to the container managed persistence. These methods
 * provide scholarship information for the student scholarship service.
 * <p>
 * The data value objects are created using a JPQL constructor expression where
 * the class constructor is called with the supplied query values. These
 * projected attributes come from 1 or more JPA entities. The outlying
 * attributes which are not directly populated in this manner are individually
 * set by calling additional methods. These attributes may require conditional
 * logic or multiple entity searches to determine what the attribute value
 * is.</p>
 *
 * @author CGI Information Management Consultants Inc.
 */
@Repository
@DeclareRoles({TRAX_READ})
public class ScholarshipDataBean implements ScholarshipData, Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CLASSNAME = ScholarshipDataBean.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    private static final String QUERY_STUD_BY_PEN
            = "SELECT DISTINCT new " + ScholarshipStudentImpl.class.getCanonicalName() + "(s, c) "
            + "FROM StudentMasterEntity s, ScholarshipEntity a, TabSchoolEntity c "
            + "WHERE a.primaryKey.studNo = ?1"
            + " AND a.primaryKey.studNo = s.studNo"
            + " AND s.mincode = c.mincode";

    private static final String QUERY_SCHOLARSHIPS_BY_PEN
            = "SELECT new " + ScholarshipImpl.class.getCanonicalName()
            + "(a.primaryKey.studNo, a.primaryKey.awardCode, a.awardYear,"
            + " a.awardCashDate, a.awardAmt) "
            + "FROM ScholarshipEntity a "
            + "WHERE a.primaryKey.studNo = ?1";

    @Override
    @RolesAllowed(TRAX_READ)
    public List<ScholarshipStudent> findStudentByPEN(final String pen) {
        final String _m = "findStudentByPEN(String)";
        LOG.entering(CLASSNAME, _m);

        final List<ScholarshipStudent> scholarships = null;

        LOG.exiting(CLASSNAME, _m);
        return scholarships;
    }

    @Override
    @RolesAllowed(TRAX_READ)
    public List<Scholarship> findScholarshipsByPEN(final String pen) {
        final String _m = "findScholarshipsByPEN(String)";
        LOG.entering(CLASSNAME, _m);

        final List<Scholarship> scholarships = null;

        LOG.exiting(CLASSNAME, _m);
        return scholarships;
    }
}
