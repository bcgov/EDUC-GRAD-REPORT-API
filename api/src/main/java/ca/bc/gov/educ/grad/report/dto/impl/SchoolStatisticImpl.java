package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.school.SchoolStatistic;
import lombok.Data;

@Data
public class SchoolStatisticImpl implements SchoolStatistic {

    private int transcriptCount;
    private int dogwoodCount;
    private int adultDogwoodCount;
    private int frenchImmersionCount;
    private int programFrancophoneCount;
    private int evergreenCount;
    private int totalCertificateCount;

}
