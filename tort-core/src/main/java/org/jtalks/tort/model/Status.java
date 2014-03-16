package org.jtalks.tort.model;

/**
 * @author Mirian Dzhachvadze
 */
public enum Status {
    COMPLETED("completed"),
    PASSED("passed"),
    BROKEN("broken"),
    FAILED("failed");

    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
