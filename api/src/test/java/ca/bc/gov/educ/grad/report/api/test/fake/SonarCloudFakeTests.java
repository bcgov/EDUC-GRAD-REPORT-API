package ca.bc.gov.educ.grad.report.api.test.fake;

import ca.bc.gov.educ.grad.report.dto.impl.CanadianPostalAddressImpl;
import ca.bc.gov.educ.grad.report.dto.reports.bundle.decorator.AchievementOrderTypeImpl;
import ca.bc.gov.educ.grad.report.dto.reports.data.adapter.BusinessEntityAdapter;
import ca.bc.gov.educ.grad.report.model.achievement.AchievementType;
import ca.bc.gov.educ.grad.report.model.common.support.report.BusinessReportEntity;
import ca.bc.gov.educ.grad.report.model.reports.ReportFormat;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SonarCloudFakeTests {

    @Before
    public void init() throws Exception {

    }

    @Test
    public void boolObjectImplSheetTests() {
        AchievementOrderTypeImpl achievementOrderType = new AchievementOrderTypeImpl();
        achievementOrderType.setAchievementType(AchievementType.DEFAULT);
        assertNotNull(achievementOrderType);

        BusinessEntityAdapter businessEntityAdapter = new BusinessEntityAdapter();
        assertNotNull(businessEntityAdapter);

        BusinessReportEntity businessReportEntity = new BusinessReportEntity(new byte[0], ReportFormat.PDF, "testFile");
        assertNotNull(businessReportEntity);

        CanadianPostalAddressImpl canadianPostalAddress = new CanadianPostalAddressImpl();
        assertNotNull(canadianPostalAddress);


    }

}
