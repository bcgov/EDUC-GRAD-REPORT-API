package ca.bc.gov.educ.grad.report.api.client;

import java.io.Serializable;
import java.util.Objects;


public class Pen implements Comparable<Pen>, Serializable {

    private String pen = "";
    private String entityID = "";

    public String getPen() {
        return pen;
    }

    public void setPen(String value) {
        this.pen = value;
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String value) {
        this.entityID = value;
    }

    @Override
    public int compareTo(Pen o) {
        return pen.compareTo(o.pen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pen pen1 = (Pen) o;
        return Objects.equals(pen, pen1.pen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pen);
    }
}
