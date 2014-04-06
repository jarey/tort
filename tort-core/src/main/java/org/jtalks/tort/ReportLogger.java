package org.jtalks.tort;

public interface ReportLogger {

    void info(int indent, String message);
    void info(String message);

    void error(int indent, String message);
    void error(String message);

    void setReportStorage(ReportStorage reportStorage);
}
