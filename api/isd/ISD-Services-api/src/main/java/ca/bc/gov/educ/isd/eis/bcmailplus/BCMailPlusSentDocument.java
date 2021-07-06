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

/**
 * Represents the a document for BC Mail Plus.
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface BCMailPlusSentDocument extends Serializable {

    // ------------------ GETTER(S) AND SETTER(S)
    /**
     * Gets the content of the file.
     *
     * @return content of the file as a byte array.
     */
    byte[] getContent();

    /**
     * Gets the size of the file in bytes.
     *
     * @return size of the file in bytes.
     */
    long getNumBytes();

    /**
     * Gets the folder were the document is going to be sent.
     *
     * @return folder were the document is going to be sent.
     */
    String getDestinationFolder();

    /**
     * Gets the folder were the document to be read is stored.
     *
     * @return folder were the document to be read is stored.
     */
    String getSourceFolder();

    /**
     * Gets the name that is going to be used to rename the file. By default the
     * same name than the original file.
     *
     * @return name that is going to be used to rename the file.
     */
    String getNewFileName();

    /**
     * Gets the id of the document.
     *
     * @return id of the document.
     */
    String getDocumentId();

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
     * Gets the confirmation of the transferring process.
     *
     * @return confirmation of the transferring process.
     */
    String getTransferConfirmation();

}
