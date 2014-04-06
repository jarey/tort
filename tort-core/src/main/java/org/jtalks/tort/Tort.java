package org.jtalks.tort;

import com.beust.jcommander.internal.Lists;
import org.jtalks.tort.model.Status;
import org.jtalks.tort.model.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author Mirian Dzhachvadze
 */
public class Tort {

    private static final Logger LOGGER = LoggerFactory.getLogger(Tort.class);
    private static final String REPORT_SERVICES_FILE = "META-INF/services/org.jtalks.tort.report.Services";
    private static Tort INSTANCE;

    private List<ReportLogger> reportLoggers;

    // todo use default storage for now
    private static ReportStorage reportStorage = new DefaultReportStorage();

    private Tort(List<ReportLogger> reportLoggers) {
        this.reportLoggers = reportLoggers;
    }

    public synchronized static Tort getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        List<ReportLogger> reportLoggers = initReportLoggers(reportStorage);
        if (reportLoggers != null) {
            INSTANCE = new Tort(reportLoggers);
        }

        LOGGER.info("Tort initialized with loggers [{}]", loggersToString(reportLoggers));

        return INSTANCE;
    }

    private static String loggersToString(List<ReportLogger> reportLoggers) {
        String log = "";
        for (Iterator<ReportLogger> iterator = reportLoggers.iterator(); iterator.hasNext(); ) {
            log += iterator.next().getClass().getSimpleName();

            if (iterator.hasNext()) {
                log += ", ";
            }
        }

        return log;
    }


    private static List<ReportLogger> initReportLoggers(ReportStorage reportStorage) {
        List<ReportLogger> loggers = Lists.newArrayList();
        try {
            Enumeration<URL> reportServices = Tort.class.getClassLoader().getResources(REPORT_SERVICES_FILE);
            while (reportServices.hasMoreElements()) {
                instantiateLoggers(reportServices.nextElement(), loggers, reportStorage);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to read report services: {}", e.getMessage());
        }

        return loggers;
    }

    private static void instantiateLoggers(URL url, List<ReportLogger> loggers, ReportStorage reportStorage) throws Exception {
        String file = url.getFile();
        LOGGER.debug("Read report loggers from file [{}]", file);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            LOGGER.trace("Trying to instantiate [{}] class", line);

            Object loggerObject = Class.forName(line).newInstance();
            if (!(loggerObject instanceof ReportLogger)) {
                throw new RuntimeException("Invalid class name [" + line + "]. Only ReportLogger class is supported");
            }

            ReportLogger reportLogger = ReportLogger.class.cast(loggerObject);
            reportLogger.setReportStorage(reportStorage);
            loggers.add(reportLogger);
        }
    }

    public void addTestClassIfAbsent(String name) {
        reportStorage.addTestClassIfAbsent(name);
    }

    public void addTestCase(String methodName) {
        reportStorage.addTestCase(methodName);
    }

    public void finishTestCase(Status status, String methodName) {
        reportStorage.finishTestCase(status, methodName);
    }

    public void addTestSuiteIfAbsent(String name) {
        reportStorage.addTestSuiteIfAbsent(name);
    }

    public void info(int indent, String message) {
        for (ReportLogger reportLogger : reportLoggers) {
            reportLogger.info(indent, message);
        }
    }

    public void info(String message) {
        for (ReportLogger reportLogger : reportLoggers) {
            reportLogger.info(message);
        }
    }

    public Collection<TestSuite> getTestSuites() {
        return reportStorage.getTestSuites();
    }
}
