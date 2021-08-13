/* *********************************************************************
 *  Copyright (c) 2015, Ministry of Education, BC.
 * 
 *  All rights reserved.
 *    This information contained herein may not be used in whole 
 *    or in part without the express written consent of the 
 *    Government of British Columbia, Canada. 
 * 
 *  Revision Control Information 
 *  File:                        FileTransferService.java 
 *  Date of Last Commit: $Date:: 2016-02-19 14:44:42 -0800 (Fri, 19 Feb 2016)  $  
 *  Revision Number:     $Rev:: 908                                            $  
 *  Last Commit by:      $Author:: cprince                                     $ 
 *  
 * ********************************************************************** */

package ca.bc.gov.educ.isd.eis.trax;

import ca.bc.gov.educ.isd.eis.EISException;
import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface FileTransferService {
    
    /** Submit a Transcript  File.
     * 
     * Submit Transcript is a wrapper around the submit file method.  It 
     * sets the document type code for transcripts on the callers behalf.
     * 
     * @param psiEid    The unique ISD system identifier for the PSI
     * @param documentID    Unique identifier for the document and content
     * @param genDate   Date of the document
     * @param isUnofficial  True if this is an unofficial or interim document
     * @param psiXref   (optional) PSI supplied cross reference identifier.
     * @param studentSurname Student's Surname
     * @param studentFirstName Student's First Name
     * @param mimeType  MIME type of the file (used to determine the correct file name extension)
     * @param fileBytes The data content of the file.
     */
    void submitTranscript(
            String psiEid, 
            String documentID, 
            String genDate,
            Boolean isUnofficial,
            String psiXref,
            String studentSurname, 
            String studentFirstName, 
            String mimeType,
            byte [] fileBytes ) throws EISException;
    
    /** Make a file available for download.
     * 
     * <p>Make file data available to a PSI for transfer.  This method will
     * construct the standardized file name from the parameters provided.
     * 
     * @param psiEid    The unique ISD system identifier for the PSI
     * @param documentID    Unique identifier for the document and content
     * @param genDate   Date of the document
     * @param docType   The PESC document type code for this file
     * @param isUnofficial  True if this is an unofficial or interim document
     * @param psiXref   (optional) PSI supplied cross reference identifier.
     * @param studentSurname Student's Surname
     * @param studentFirstName Student's First Name
     * @param mimeType  MIME type of the file (used to determine the correct file name extension)
     * @param fileBytes The data content of the file.
     */
    void submitFile(
            String psiEid, 
            String documentID, 
            String genDate,
            String docType,
            Boolean isUnofficial,
            String psiXref,
            String studentSurname, 
            String studentFirstName, 
            String mimeType,
            byte [] fileBytes ) throws EISException;
    
    /** Construct a standardized file name for a document.
     *
     * <p>The file name returned is the same file name which will be used if
     * the file is submitted to the file transfer site.  This method is 
     * intended to provide convenience in testing and providing admin features.
     * 
     * @param documentID    Unique identifier for the document and content
     * @param genDate   Date of the document
     * @param docType   The PESC document type code for this file
     * @param isUnofficial  True if this is an unofficial or interim document
     * @param psiXref   (optional) PSI supplied cross reference identifier.
     * @param studentSurname Student's Surname
     * @param studentFirstName Student's First Name
     * @param mimeType  MIME type of the file (used to determine the correct file name extension)
     * @throws EISException 
     */
    void generateFileName (
            String documentID, 
            String genDate,
            String docType,
            Boolean isUnofficial,
            String psiXref,
            String studentSurname, 
            String studentFirstName, 
            String mimeType) throws EISException;
    
    /** List the files which are currently available to a given PSI.
     * 
     * <p>This method may be used to determine what student files an PSI
     * currently has access to.
     * 
     * @param psiEID    Unique identifier for the PSI
     * @return  List of file names available to the PSI.
     * @throws EISException 
     */
    List<String> listFiles(String psiEID) throws EISException;
    
    /** List the post-secondary institutions that have access to a particular document.
     * 
     * <p>scans the file transfer site for the document id and returns a list
     * of entity identifiers for the post-secondary institutions which 
     * @param documentID
     * @return
     * @throws EISException 
     */
    List<String> listPSI(String documentID) throws EISException;
    
    /** Removes a file from the file transfer site.
     * 
     * <p>When an access window for a document has expired this method can be 
     * used to remove the file from the file transfer site.
     * 
     * @param psiEid    Unique identifier for the post-secondary institution.
     * @param documentID    Unique identifier for the document to be removed.
     * @throws EISException 
     */
    void purgeFile( String psiEid, String documentID ) throws EISException;
    
    /** Removed all files for a student from an PSI transfer site.
     * 
     * <p>When an access window for a document has expired this method can be 
     * used to remove all files for a specific student from the file transfer site.
     * 
     * @param psiEid Unique identifier for the post-secondary institution
     * @param studentSurname Student's Surname
     * @param studentFirstName Student's First Name
     * @throws EISException 
     */
    void purgeFile( String psiEid, String studentSurname, String studentFirstName ) throws EISException;
    
}
