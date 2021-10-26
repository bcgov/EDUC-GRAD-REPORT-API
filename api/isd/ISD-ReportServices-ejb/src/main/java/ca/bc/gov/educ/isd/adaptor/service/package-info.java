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
 *  File:                package-info.java
 *  Date of Last Commit: $Date::                   $
 *  Revision Number:     $Rev::                    $
 *  Last Commit by:      $Author::                 $
 *
 * ***********************************************************************
 */
/**
 * <h1> Package Description </h1>
 * This package contains the Java Entities mapped to the TRAX database as well
 * as the EJB's which access the entities. The EJB's contained in this package
 * are accessed from the {@code TRAXAdapterBean} and produce the unmanaged data
 * value objects for the {@code TRAXAdapterBean}. Each data bean handles
 * requests for a type of service ie the {@code ExamDataBean} handles requests
 * for the ExamStudent and the ExamResults requests. The data beans primarily
 * use a JPQL constructor expression to create the unmanaged data value objects.
 * For cases where the attributes require conditional logic outside what is
 * accessible from the constructor or additional data queries from tables which
 * would require an outer join, additional processing calls are made to obtain
 * that data and a 'setter' method is used add the data to the object. In some
 * cases a named query is used to find the entity. These named queries are
 * defined in the Entity classes themselves. In the JPQL all Entities are
 * treated as root level entities. Some Entities required an embedded class to
 * create a primary key as there was no single column which would suffice. The
 * EJBs all adhere to the interface contracts defined in the
 * ISD-Services-EIS-api {@code ca.bc.gov.educ.isd.eis.trax.db} package.
 * <p>
 * Within the system architecture the TRAX adaptor is provided as a component of
 * the resource adaptor layer connecting business services to the external TRAX
 * system.
 * </p>
 * <h2> Dependencies </h2>
 * This package requires a J2EE compliant container. This package also requires
 * the following projects are also deployed to the runtime container:
 * <ul>
 * <li>ISD-Services-EIS-api</li>
 * <li>ISD-CommonSupport</li>
 * </ul>
 * This package requires a database with database links to TRAX and SPM
 * databases. The package requires the following views exist in the database
 * (see the Entity documentation for the view SQL statement):
 * <ul>
 * <li>PROV_EXAM_VW</li>
 * <li>SCHOOL_MASTER_VW</li>
 * <li>STUD_XCRSE_VW</li>
 * <li>STUDENT_MASTER_VW</li>
 * <li>STUDENT_SCHOLARSHIP_VW</li>
 * <li>TAB_SCHOOL_VW</li>
 * <li>TSW_MAILER_DEMOG_VW</li>
 * <li>TSW_MAILER_EXAM_VW</li>
 * <li>TSW_TRAN_COURSE_VW</li>
 * <li>TSW_TRAN_DEMOG_VW</li>
 * </ul>
 * <h2> What the Package Contains </h2>
 * <ul>
 * <li>{@code CertificateDataBean} - Graduation Certificate data services
 * <li>{@code ExamDataBean} - Exam data services
 * <li>{@code ScholarshipDataBean} - Scholarship data services
 * <li>{@code StudentDataBean} - Student Demographic services
 * <li>{@code TranscriptDataBean} - Transcript services
 * <li>{@code ProvExamEntity}
 * <li>{@code ScholarshipEntity}
 * <li>{@code SchoolMasterEntity}
 * <li>{@code StudXcrseEntity}
 * <li>{@code StudentMasterEntity}
 * <li>{@code TabSchoolEntity}
 * <li>{@code TswMailerDemogEntity}
 * <li>{@code TswMailerExamEntity}
 * <li>{@code TswTranCourseEntity}
 * <li>{@code TswTranDemogEntity}
 * <li>{@code CourseId} - Embeddable primary key
 * <li>{@code ScholarshipId} - Embeddable primary key
 * </ul>
 * <h> Related Documentation </h>
 * <ul>
 * <li><a href="{@docRoot}/test/index.html" target="_top">Test Case
 * documentation</a></li>
 * </ul>
 */
package ca.bc.gov.educ.isd.adaptor.service;
