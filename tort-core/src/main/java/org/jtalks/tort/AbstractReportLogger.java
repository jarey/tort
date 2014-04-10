package org.jtalks.tort;

import org.jtalks.tort.model.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author Mirian Dzhachvadze
 */
public abstract class AbstractReportLogger implements ReportLogger {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractReportLogger.class);

    protected ReportStorage reportStorage;

    @Override
    public void info(int indent, String message) {
        reportStorage.getLastCase().addMessage(indent, message, Level.INFO);
    }

    @Override
    public void info(String message) {
        info(stackTraceDepth(message), message);
    }

    @Override
    public void error(int indent, String message) {
        reportStorage.getLastCase().addMessage(indent, message, Level.ERROR);
    }

    @Override
    public void error(String message) {
        error(stackTraceDepth(message), message);
    }

    @Override
    public void setReportStorage(ReportStorage reportStorage) {
        this.reportStorage = reportStorage;
    }

    protected abstract boolean isMethodEligible(Method method);

    protected int stackTraceDepth(String message) {
        int depth = doStackTraceDepth();
        if (depth == 0) {
            LOGGER.warn("Failed to determine indent by stack trace for message [{}]", message);
        }

        // TODO Get rid of magic number!
        return depth - 5;
    }

    private int doStackTraceDepth() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 2; i < stackTrace.length; i++) {//skip current method and getStackTrace() method
            StackTraceElement traceElement = stackTrace[i];
            try {
                Class<?> aClass = Class.forName(traceElement.getClassName());
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    if (method.getName().equals(traceElement.getMethodName())) {
                        if (isMethodEligible(method)) {
                            return i;
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        return 0;
    }
}
