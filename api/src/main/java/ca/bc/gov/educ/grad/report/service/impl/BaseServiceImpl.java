package ca.bc.gov.educ.grad.report.service.impl;

import ca.bc.gov.educ.grad.report.api.client.GradSearchStudent;
import ca.bc.gov.educ.grad.report.api.client.GraduationStudentRecord;
import ca.bc.gov.educ.grad.report.api.util.ReportApiConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
@Slf4j
public class BaseServiceImpl {

    private static final String CLASSNAME = BaseServiceImpl.class.getName();
    private static final Logger LOG = Logger.getLogger(CLASSNAME);

    @Autowired
    WebClient webClient;

    @Autowired
    ReportApiConstants reportApiConstants;

    protected GraduationStudentRecord getGradStatusFromGradStudentApi(String studentID, String accessToken) {
        final String methodName = "getGradStatusFromGradStudentApi(String studentID, String accessToken)";
        log.trace("Entering {}", methodName);
        try
        {
            return webClient.get().uri(String.format(reportApiConstants.getReadGradStudentRecord(),studentID)).headers(h -> h.setBearerAuth(accessToken)).retrieve().bodyToMono(GraduationStudentRecord.class).block();
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

    protected GraduationStudentRecord getGraduationStudentRecordFromGradStudentApi(String pen, String accessToken) {
        final String methodName = "getGraduationStudentRecordFromGradStudentApi(String pen, String accessToken)";
        log.trace("Entering {}", methodName);
        try
        {
            return webClient.get().uri(String.format(reportApiConstants.getReadGradStudentRecordPen(),pen)).headers(h -> h.setBearerAuth(accessToken)).retrieve().bodyToMono(GraduationStudentRecord.class).block();
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

    protected GradSearchStudent getStudentByPenFromStudentApi(String pen, String accessToken) {
        final String methodName = "getStudentByPenFromStudentApi(String pen, String accessToken)";
        log.trace("Entering {}", methodName);
        try {
            List<GradSearchStudent> stuDataList = webClient.get().uri(String.format(reportApiConstants.getPenStudentApiByPenUrl(),pen)).headers(h -> h.setBearerAuth(accessToken)).retrieve().bodyToMono(new ParameterizedTypeReference<List<GradSearchStudent>>() {}).block();
            if(stuDataList != null && !stuDataList.isEmpty()) {
                return stuDataList.get(0);
            }
        } catch (Exception e) {
            LOG.throwing(CLASSNAME, methodName, e);
        }
        return null;
    }

}
