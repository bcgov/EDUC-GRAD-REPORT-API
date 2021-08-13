/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        EISException.java 
 *  Date of Last Commit: $Date:: 2016-09-27 16:17:37 -0700 (Tue, 27 Sep 2016)  $  
 *  Revision Number:     $Rev:: 3856                                           $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.eis;

/** An application exception root for EIS components.
 *
 * @author CGI Information Management Consultants Inc.
 */
public class EISException extends Exception {

    private static final long serialVersionUID = -4585114885695130714L;

    
    /**
     * Creates a new instance of <code>EISException</code> without detail
     * message.
     */
    public EISException() {
    }

    /**
     * Constructs an instance of <code>EISException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EISException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>EISException</code> with the specified
     * detail message and throwable.
     *
     * @param msg the detail message.
     * @param throwable throwable related to the exception
     */
    public EISException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
