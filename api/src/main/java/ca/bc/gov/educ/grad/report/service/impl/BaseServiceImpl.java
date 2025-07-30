package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.GradSearchStudent;
import ca.bc.gov.educ.grad.report.api.client.GraduationStudentRecord;
import ca.bc.gov.educ.grad.report.api.service.RESTService;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
public class BaseServiceImpl {

    private static final String CLASSNAME = BaseServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    RESTService restService;

    @Autowired
    @Qualifier("reportApiClient")
    WebClient webClient;

    @Autowired
    ReportApiConstants reportApiConstants;

    protected GraduationStudentRecord getGradStatusFromGradStudentApi(String studentID) {
        final String methodName = "getGradStatusFromGradStudentApi(String studentIDn)";
        LOG.entering(CLASSNAME, methodName);
        try
        {
            return restService.get(String.format(reportApiConstants.getReadGradStudentRecord(),studentID), GraduationStudentRecord.class, webClient);
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

    protected GraduationStudentRecord getGraduationStudentRecordFromGradStudentApi(String pen) {
        final String methodName = "getGraduationStudentRecordFromGradStudentApi(String pen)";
        LOG.entering(CLASSNAME, methodName);
        try
        {
            return restService.get(String.format(reportApiConstants.getReadGradStudentRecordPen(),pen), GraduationStudentRecord.class, webClient);
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

    protected GradSearchStudent getStudentByPenFromStudentApi(String pen) {
        final String methodName = "getStudentByPenFromStudentApi(String pen)";
        LOG.entering(CLASSNAME, methodName);
        try {
            List<GradSearchStudent> stuDataList = restService.get(String.format(reportApiConstants.getPenStudentApiByPenUrl(),pen),
                    new ParameterizedTypeReference<List<GradSearchStudent>>() {}, webClient);
            if(stuDataList != null && !stuDataList.isEmpty()) {
                return stuDataList.get(0);
            }
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

}
