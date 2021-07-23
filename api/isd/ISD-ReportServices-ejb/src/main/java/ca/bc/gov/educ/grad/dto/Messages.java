package ca.bc.gov.educ.grad.dto;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private static final long serialVersionUID = 1L;

    private List<Message> errors;

    public Messages() {
        this.errors = new ArrayList<Message>();
    }

    public Messages(String message) {
        this.errors = new ArrayList<Message>();
        this.errors.add(new Message(message));
    }

    public Messages(List<Message> errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<Message> getErrors() {
        return errors;
    }
}