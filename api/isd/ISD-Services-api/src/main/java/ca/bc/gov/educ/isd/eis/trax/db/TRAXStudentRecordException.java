/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.bc.gov.educ.isd.eis.trax.db;

import ca.bc.gov.educ.isd.eis.EISException;

/**
 * An EIS Exception specific to anomalies in TRAX student records.
 *
 * This exception indicated that unexpected student record structures were found
 * such as a 
 * @author CGI Information Management Consultants Inc.
 */
public class TRAXStudentRecordException extends EISException {

    private static final long serialVersionUID = 8521970064768113443L;

    String PEN;

    public TRAXStudentRecordException(String PEN) {
        this.PEN = PEN;
    }

    public TRAXStudentRecordException(String PEN, String msg) {
        super(msg);
        this.PEN = PEN;
    }

    public TRAXStudentRecordException(String PEN, String msg, Throwable throwable) {
        super(msg, throwable);
        this.PEN = PEN;
    }

    public String getPEN() {
        return PEN;
    }

    public void setPEN(String PEN) {
        this.PEN = PEN;
    }

}
