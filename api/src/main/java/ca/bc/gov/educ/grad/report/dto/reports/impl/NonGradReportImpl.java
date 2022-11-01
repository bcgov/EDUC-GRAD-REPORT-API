package ca.bc.gov.educ.grad.report.dto.reports.impl;

import ca.bc.gov.educ.grad.report.dto.reports.jasper.impl.ReportImpl;
import ca.bc.gov.educ.grad.report.model.reports.GraduationReport;
import ca.bc.gov.educ.grad.report.model.school.School;
import ca.bc.gov.educ.grad.report.model.student.Student;

import java.util.Date;
import java.util.List;

public class NonGradReportImpl extends ReportImpl implements GraduationReport {

    private List<Student> students;
    private School school;

    public NonGradReportImpl() {
        this("StudentNonGrad");
    }

    /**
     * Constructs a report implementation based with a report template name.
     *
     * @param name Report template name to run.
     */
    public NonGradReportImpl(String name) {
        super(name);
    }

    @Override
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public List<Student> getStudents() {
        return this.students;
    }

    @Override
    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public void setSchool(School school, String logoCode) {

    }

    @Override
    public void setReportDate(Date date) {

    }

    /**
     * Returns the main object used to fill the report. All the reports use a
     * single student or school instance populated with necessary data to fulfill the
     * report's data requirements.
     *
     * @return this.student
     */
    @Override
    public Object getDataSource() {
        return this.school;
    }
}
