package org.jtalks.tort.generator;

public class ReportGeneratorException extends RuntimeException {
    public ReportGeneratorException(Throwable cause) {
        super(cause);
    }

    public ReportGeneratorException(String message) {
        super(message);
    }

    public ReportGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
