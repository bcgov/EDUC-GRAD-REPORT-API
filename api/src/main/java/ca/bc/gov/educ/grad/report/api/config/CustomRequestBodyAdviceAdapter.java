package ca.bc.gov.educ.grad.report.api.config;

import ca.bc.gov.educ.grad.report.api.client.Pen;
import ca.bc.gov.educ.grad.report.api.client.ReportRequest;
import ca.bc.gov.educ.grad.report.api.client.Student;
import ca.bc.gov.educ.grad.report.api.service.utils.JsonTransformer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.Optional;

@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    final JsonTransformer jsonTransformer;

    HttpServletRequest httpServletRequest;

    @Autowired
    public CustomRequestBodyAdviceAdapter(JsonTransformer jsonTransformer) {
        this.jsonTransformer = jsonTransformer;
    }

    @Autowired
    public void setHttpServletRequest(final HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public boolean supports(final MethodParameter methodParameter, final Type type, final Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    @SneakyThrows
    public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage, final MethodParameter parameter, final Type targetType,
                                final Class<? extends HttpMessageConverter<?>> converterType) {
        String appLogLevel = Optional.ofNullable(System.getenv("APP_LOG_LEVEL")).orElse("INFO");
        boolean isDebugMode = "DEBUG".equalsIgnoreCase(appLogLevel);
        if(isDebugMode) {
            Object bodyRequest;
            if (body instanceof ReportRequest) {
                ReportRequest cloneRequest = SerializationUtils.clone((ReportRequest) body);
                Student st = cloneRequest.getData().getStudent();
                hideStudentDataForLogging(st);
                if (cloneRequest.getData().getSchool() != null) {
                    for (Student s : cloneRequest.getData().getSchool().getStudents()) {
                        hideStudentDataForLogging(s);
                    }
                }
                bodyRequest = cloneRequest;
            } else {
                bodyRequest = body;
            }
            this.httpServletRequest.setAttribute("payload", bodyRequest);
        }
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    private void hideStudentDataForLogging(Student st) {
        if (st != null) {
            Pen pen = ObjectUtils.defaultIfNull(st.getPen(), new Pen());
            pen.setPen(null);
            st.setPen(pen);
            st.setFirstName("John");
            st.setMiddleName("");
            st.setLastName("Doe");
        }
    }
}
