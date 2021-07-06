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
package ca.bc.gov.educ.isd.eis.bccampus.pesc;

import java.util.List;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface HighSchoolTranscript {

    /**
     * Gets the value of the noteMessage property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the noteMessage property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNoteMessage().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     *
     *
     * @return
     */
    List<String> getNoteMessage();

    /**
     * Gets the value of the student property.
     *
     * @return possible object is {@link K12StudentType }
     *
     */
    Object getStudent();

    /**
     * Gets the value of the transmissionData property.
     *
     * @return possible object is {@link TransmissionDataType }
     *
     */
    Object getTransmissionData();

    /**
     * Gets the value of the userDefinedExtensions property.
     *
     * @return possible object is {@link UserDefinedExtensionsType }
     *
     */
    Object getUserDefinedExtensions();

    /**
     * Sets the value of the student property.
     *
     * @param value allowed object is {@link K12StudentType }
     *
     */
    void setStudent(Object value);

    /**
     * Sets the value of the transmissionData property.
     *
     * @param value allowed object is {@link TransmissionDataType }
     *
     */
    void setTransmissionData(Object value);

    /**
     * Sets the value of the userDefinedExtensions property.
     *
     * @param value allowed object is {@link UserDefinedExtensionsType }
     *
     */
    void setUserDefinedExtensions(Object value);
}
