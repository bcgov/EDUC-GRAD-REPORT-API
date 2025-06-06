package ca.bc.gov.educ.grad.report.api.client;

import lombok.Data;

@Data
public class TraxSchool {

    private String minCode;
    private String schoolId;
    private String schoolName;
    private String districtId;
    private String districtName;
    private String transcriptEligibility;
    private String certificateEligibility;
    private String address1;
    private String address2;
    private String city;
    private String provCode;
    private String countryCode;
    private String postal;
    private String openFlag;
    private String schoolCategoryLegacyCode;
    private String schoolCategoryCode;

    public String getSchoolName() {
        return schoolName != null ? schoolName.trim() : null;
    }

    public String getDistrictName() {
        return districtName != null ? districtName.trim() : null;
    }

    public String getAddress1() {
        return address1 != null ? address1.trim() : null;
    }

    public String getAddress2() {
        return address2 != null ? address2.trim() : null;
    }

    public String getCity() {
        return city != null ? city.trim() : null;
    }

    public String getPostal() {
        return postal != null ? postal.trim() : null;
    }

    @Override
    public String toString() {
        return "School [minCode=" + minCode + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", schoolCategoryCode=" + schoolCategoryCode + ", schoolCategoryLegacyCode=" + schoolCategoryLegacyCode
                + ", districtId=" + districtId + ", districtName=" + districtName + ", transcriptEligibility=" + transcriptEligibility + ", certificateEligibility=" + certificateEligibility
                + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", provCode=" + provCode + ", countryCode=" + countryCode + ", postal=" + postal + ", openFlag=" + openFlag
                + "]";
    }
}