package ca.bc.gov.educ.grad.report.dto.impl;

import ca.bc.gov.educ.grad.report.model.common.party.address.PostalAddress;
import ca.bc.gov.educ.grad.report.model.common.support.AbstractDomainEntity;
import ca.bc.gov.educ.grad.report.model.district.District;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DistrictImpl extends AbstractDomainEntity implements District {

	private String distno;
    private String name;
    private PostalAddress address = new PostalAddressImpl();

    public String getDistno() {
        return distno;
    }

    public void setDistno(String distno) {
        this.distno = distno;
    }

    public String getDistrictNumber() {
        return getDistno();
    }

    public void setDistrictNumber(String districtNumber) {
        setDistno(districtNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrictName() {
        return getName();
    }

    public void setDistrictName(String name) {
        setName(name);
    }

    @Override
    @JsonDeserialize(as = PostalAddressImpl.class)
    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
        this.address = address;
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
