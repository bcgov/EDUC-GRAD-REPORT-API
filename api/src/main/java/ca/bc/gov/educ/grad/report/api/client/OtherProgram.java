package ca.bc.gov.educ.grad.report.api.client;

import java.io.Serializable;


public class OtherProgram implements Serializable {

    private String programCode = "";
    private String programName = "";

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
