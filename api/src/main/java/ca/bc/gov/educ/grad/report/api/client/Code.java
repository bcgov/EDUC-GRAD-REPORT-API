package ca.bc.gov.educ.grad.report.api.client;

public class Code {
    private String code;
    private String description;
    private int credits;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int value) {
        this.credits = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }
}