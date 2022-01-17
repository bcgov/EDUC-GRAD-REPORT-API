package ca.bc.gov.educ.grad.report.model.graduation;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public interface GradRequirement {

    JRBeanCollectionDataSource getCourseDetailsdataSource();

    String getCode();

    String getDescription();

    java.util.List<ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse> getCourseDetails();

    void setCode(String code);

    void setDescription(String description);

    void setCourseDetails(java.util.List<ca.bc.gov.educ.grad.report.model.achievement.AchievementCourse> courseDetails);

    void setCourseDetailsdataSource(JRBeanCollectionDataSource courseDetailsdataSource);
}
