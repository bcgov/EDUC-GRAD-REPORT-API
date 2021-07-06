/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 *
 *  All rights reserved.
 *    This information contained herein may not be used in whole
 *    or in part without the express written consent of the
 *    Government of British Columbia, Canada.
 *
 *  Revision Control Information
 *  File:                        BCcampusService.java
 *  Date of Last Commit: $Date:: 2016-JUL-10                                   $
 *  Revision Number:     $Rev::                                                $
 *  Last Commit by:      $Author:: cagomez                                     $
 *
 * ********************************************************************** */
package ca.bc.gov.educ.isd.eis.bcmailplus;

import ca.bc.gov.educ.isd.eis.common.Constants.DocumentType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Represents the a document for BC Mail Plus.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCMailPlusResponseDocument extends Serializable {

    // ------------------ GETTER(S) AND SETTER(S)
    /**
     * Gets the file list from LIN file
     *
     * @return
     */
    List<String> getFileList();

    /**
     * Gets the folder were the status document will be moved.
     *
     * @return folder were the status document will be moved.
     */
    String getResponsesFolder();

    /**
     * Gets the folder were the document to be read is stored.
     *
     * @return folder were the document to be read is stored.
     */
    String getSourceFolder();

    /**
     * Gets the file name.
     *
     * @return file name.
     */
    String getFileName();

    /**
     * Gets the type of the document (e.g., Certificate, or transcript).
     *
     * @return type of the document.
     */
    DocumentType getDocumentType();

    /**
     *
     * @return
     */
    Date getResponseFileDate();
}
