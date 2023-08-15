package ca.bc.gov.educ.grad.report.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    private DateUtils(){}

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date toDate(LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static String formatProgramCompletionDate(String programCompletionDate) {
        if(StringUtils.isBlank(programCompletionDate)) return "";
        if(StringUtils.contains(programCompletionDate, "/")) {
            programCompletionDate = StringUtils.substring(programCompletionDate, 0, 7);
        }
        if(StringUtils.contains(programCompletionDate, "-")) {
            programCompletionDate = StringUtils.substring(StringUtils.replace(programCompletionDate, "-", "/"), 0, 7);
        }
        return programCompletionDate;
    }

}
