package ca.bc.gov.educ.grad.report.model.reports;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FiscalDates {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yy");

    final private Date dateFrom;
    final private Date dateTo;

    private FiscalDates() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int advance = (cal.get(Calendar.MONTH) >= 9) ? 0 : -1;
        int yearFrom = cal.get(Calendar.YEAR) + advance;
        advance = (cal.get(Calendar.MONTH) < 9) ? 0 : +1;
        int yearTo = cal.get(Calendar.YEAR) + advance;
        Calendar calFrom = Calendar.getInstance();
        calFrom.set(yearFrom, Calendar.OCTOBER, 01);
        dateFrom = calFrom.getTime();
        Calendar calTo = Calendar.getInstance();
        calTo.set(yearTo, Calendar.SEPTEMBER, 30);
        dateTo = calTo.getTime();
    }

    public static FiscalDates getInstance() {
        return new FiscalDates();
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public String getFiscalDatesRange() {
        return dateFormat.format(getDateFrom()) + " to " + dateFormat.format(getDateTo());
    }

    public static void main(String[] args) {
        System.out.println(FiscalDates.getInstance().getFiscalDatesRange());
    }

}
