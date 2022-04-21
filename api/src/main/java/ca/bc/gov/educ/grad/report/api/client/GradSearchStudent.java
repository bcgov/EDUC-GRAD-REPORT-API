package ca.bc.gov.educ.grad.report.api.client;

import lombok.Data;

import java.io.Serializable;

@Data

public class GradSearchStudent implements Serializable {

    private String studentID;
    private String pen;

}
