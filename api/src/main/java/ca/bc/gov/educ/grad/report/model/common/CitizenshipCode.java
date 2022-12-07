package ca.bc.gov.educ.grad.report.model.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.xml.bind.annotation.XmlEnumValue;
import java.io.Serializable;

public enum CitizenshipCode implements Serializable {

    @XmlEnumValue("C")
    @JsonProperty("C")
    C("C", "Cdn or Perm Res"),

    @XmlEnumValue("O")
    @JsonProperty("O")
    O("O", "Other"),

    @XmlEnumValue("U")
    @JsonProperty("U")
    U("U", "Unknown");

    private String code;
    private String description;

    CitizenshipCode(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @JsonCreator
    public static CitizenshipCode forValue(@JsonProperty("code") final String code, @JsonProperty("description") final String description) {
        for (CitizenshipCode citizenshipCode : CitizenshipCode.values()) {
            if (citizenshipCode.code.equals(code)) {
                citizenshipCode.description = description;
                return citizenshipCode;
            }
        }
        return U;
    }

    public static CitizenshipCode valueFrom(final String code) {
        for (final CitizenshipCode gpc : values()) {
            if (gpc.isCode(code)) {
                return gpc;
            }
        }
        return U;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * Answers whether this citizenship code and the given citizenship code are
     * identical (ignoring case).
     *
     * @param code The code to compare against.
     * @return true The codes are identical.
     */
    public boolean isCode(final String code) {
        return toString().equalsIgnoreCase(code);
    }

    /**
     * Returns the human-readable text for this citizenship code.
     *
     * @return A textual description of the code, never null, never empty.
     */
    @JsonValue
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of this citizenship code.
     *
     * @return The citizenship code (without the description).
     */
    @Override
    public String toString() {
        return this.code;
    }
}
