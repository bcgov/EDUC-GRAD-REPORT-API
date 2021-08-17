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
public interface TSWTxPSI extends TRAXData {

    /**
     * Gest the TX id.
     *
     * @return the TX id
     */
    String getTxId();

    /**
     * Gest the student number.
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
     * Gets the status of the TX PSI object.
     *
     * @return the status of the PSI Choice object.
     */
    Character getPsiStatus();

    /**
     * Sets the status of the TX PSI object
     *
     * @param psiStatus the new status of the TX PSI object.
     */
    void setPsiStatus(Character psiStatus);

    /**
     * Gets the creation/update Date of the object
     *
     * @return the creation date of the object.
     */
    Date getProcessDate();
}
