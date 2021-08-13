/* *********************************************************************
 *  Copyright (c) 2016, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        Catalogue.java 
 *  Date of Last Commit: $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016)  $  
 *  Revision Number:     $Rev:: 3100                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.ecommerce.catalogue;

/**
 * ***********************************************************************
 * Copyright (c) 2016, Ministry of Education, BC.
 *
 * All rights reserved. This information contained herein may not be used in
 * whole or in part without the express written consent of the Government of
 * British Columbia, Canada.
 *
 *
 * Revision Control Information File: Catalogue.java Date of Last Commit:
 * $Date:: 2016-08-25 10:55:55 -0700 (Thu, 25 Aug 2016) $ Revision Number:
 * $Rev:: 31#$ Last Commit by: $Author:: cprince $
 * 
**************************************************************************
 */
import ca.bc.gov.educ.isd.common.DataException;
import ca.bc.gov.educ.isd.common.DescribedEntity;
import ca.bc.gov.educ.isd.common.DomainServiceException;
import java.util.List;

/**
 * A Catalogue of items which may be purchased.
 *
 * The Cateloque is the contains all of the items which can be ordered such as
 * electronic and print transcripts.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface Catalogue extends DescribedEntity {

    List<CatalogueCategory> getCategories() throws DomainServiceException, DataException;

}
