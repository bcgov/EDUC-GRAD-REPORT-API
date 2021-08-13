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
 *  File:                $Id::                                                 $
 *  Date of Last Commit: $Date:: 2015-11-02 09:27:52 -0800 (Mon, 02 Nov 2015)  $
 *  Revision Number:     $Rev:: 36                                             $
 *  Last Commit by:      $Author:: bbates                                      $
 *
 * ***********************************************************************
 */
package ca.bc.gov.educ.isd.ecommerce.delivery;

import ca.bc.gov.educ.isd.psi.TransmissionMode;
import java.util.Date;

/**
 * @author Ministry of Education, BC
 */
public interface PSIDeliveryInfo extends DeliveryInfo {

    /**
     * Indicates if the PSI should be delivered interim and final marks, or if
     * the transcript should be sent immediately.
     *
     * @return True, if the transcript should be delivered when interim and
     * final marks are available, rather than immediately.
     */
    Boolean getInterim();

    void setInterim(Boolean interim);

    /**
     * Returns the name of the post-secondary institution.
     *
     * @return The PSI name, never null.
     */
    String getPsiName();

    /**
     * Sets the name of the post-secondary institution.
     *
     * @param psiName The PSI name.
     */
    void setPsiName(String psiName);

    String getPsiCode();

    void setPsiCode(String psiCode);

    public Date getAuthUntilDate();

    /**
     * This is only called for XML transcript orders. Allows a PSI to request
     * updates until a defined date as specified by the student during the
     * ordering process.
     *
     * @param authDate A PSI may request updated XML transcripts up until the
     * given date.
     */
    void setAuthUntilDate(Date authDate);

    /**
     * Retrieve how this delivery was actually transmitted.
     *
     * @return
     */
    TransmissionMode getTransmissionMode();

    /**
     * Indicate how this delivery was actually transmitted.
     *
     * @param transmissionMode how this delivery was transmitted:w
     *
     */
    void setTransmissionMode(TransmissionMode transmissionMode);
}
