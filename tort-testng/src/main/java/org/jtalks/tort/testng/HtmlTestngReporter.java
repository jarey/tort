package org.jtalks.tort.testng;

import org.jtalks.tort.Tort;
import org.jtalks.tort.generator.html.HtmlReportGenerator;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * @author Mirian Dzhachvadze
 */
public class HtmlTestngReporter implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        new HtmlReportGenerator(outputDirectory).
                generate(Tort.getInstance().getTestSuites());
    }
}
