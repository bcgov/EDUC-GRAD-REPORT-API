/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.bc.gov.educ.isd.eis.trax.db;

import java.util.Date;

/**
 *
 * @author CGI Information Management Consultants Inc.
 */
public interface TSWStud extends TRAXData {

    /**
     * Gets the student number.
     *
     * @return the student number
     */
    String getStudNo();

    /**
     * Gets the PSI code.
     *
     * @return the PSI code.
     */
    String getPsiCode();

    /**
     * Gets the Year when the Choice was made.
     *
     * @return the year when the Choice was made.
     */
    String getPsiYear();

    /**
     * Gets the status of the PSI Choice object.
     *
     * @return the status of the PSI Choice object.
     */
    Character getPsiStatus();

    /**
     * Sets the status of the PSI Choice object
     *
     * @param psiStatus the new status of the PSI Choice object.
     */
    void setPsiStatus(Character psiStatus);

    /**
     * Gets the creation/update Date of the object
     *
     * @return the creation date of the object.
     */
    Date getDateEntered();
}
