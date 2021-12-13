/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        package-info.java
 *  Date of Last Commit: $Date::                                               $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author::                                             $
 *
 * ********************************************************************** */
/**
 * <h2>Package Description</h2>
 * <p>
 * Provides a generic implementation for reports. The packages below this level
 * provide concrete implementations (e.g., for JasperReports). This separation
 * allows different report engines to be used in the future without having to
 * rewrite the reporting service.
 * </p>
 *
 * <h2>Deployment Dependencies</h2>
 * <p>
 * This component requires that BeanUtils and JasperReports are deployed to the
 * JBoss application server. See the project.properties file's
 * manifest.dependencies property for details.
 * </p>
 */
package ca.bc.gov.educ.grad.report.dto.reports.impl;
