package org.jtalks.tort.testng;

import org.jtalks.tort.Tort;
import org.jtalks.tort.generator.html.HtmlReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * @author Mirian Dzhachvadze
 */
public class HtmlTestngReporter implements IReporter {

    private final static Logger LOGGER = LoggerFactory.getLogger(HtmlTestngReporter.class);

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        LOGGER.info("Generate HTML report to " + outputDirectory);

        new HtmlReportGenerator(outputDirectory).
                generate(Tort.getInstance().getTestSuites());
    }
}
