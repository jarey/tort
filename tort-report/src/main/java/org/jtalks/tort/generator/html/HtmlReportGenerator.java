package org.jtalks.tort.generator.html;

import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.jtalks.tort.generator.ReportGenerator;
import org.jtalks.tort.generator.ReportGeneratorException;
import org.jtalks.tort.model.TestCase;
import org.jtalks.tort.model.TestClass;
import org.jtalks.tort.model.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Collection;

public class HtmlReportGenerator implements ReportGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlReportGenerator.class);

    private static final String INDEX_TEMPLATE = "org/jtalks/tort/report/html/template/index.vm";
    private static final String SUITE_TEMPLATE = "org/jtalks/tort/report/html/template/suite.vm";
    private static final String TEST_CASE_TEMPLATE = "org/jtalks/tort/report/html/template/testcase.vm";

    private String reportOutput;

    private VelocityEngine velocityEngine;

    public HtmlReportGenerator(String outputDirectory) {
        Preconditions.checkNotNull(outputDirectory, "[outputDirectory] should not be null");
        this.reportOutput = outputDirectory + File.separator;
        new File(reportOutput).mkdirs();

        LOGGER.info("Report output dir is " + reportOutput);

        initVelocityEngine();
    }

    @Override
    public void generate(Collection<TestSuite> testSuites) {
        if (CollectionUtils.isEmpty(testSuites)) {
            return;
        }

        copyResources(reportOutput);
        generateIndexPage(testSuites);

        for (TestSuite testSuite : testSuites) {
            mergeSuiteTemplate(testSuite);

            generateTestCasesPages(testSuite);
        }
    }

    private void generateTestCasesPages(TestSuite testSuite) {
        for (TestClass testClass : testSuite.getClasses()) {
            for (TestCase testCase : testClass.getTestCases()) {
                mergeTestCaseTemplate(testSuite, testClass, testCase);
            }
        }
    }

    private void mergeTestCaseTemplate(TestSuite testSuite, TestClass testClass, TestCase testCase) {
        Writer writer = null;
        try {
            writer = createWriter(testCase.getName() + ".html");

            VelocityContext context = new VelocityContext();
            context.put("suite", testSuite);
            context.put("class", testClass);
            context.put("messages", TestCaseMarshaller.marshal(testCase));

            velocityEngine.mergeTemplate(TEST_CASE_TEMPLATE, "UTF-8", context, writer);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    private void generateIndexPage(Collection<TestSuite> testSuites) {
        Writer writer = null;
        try {
            writer = createWriter("index.html");

            VelocityContext context = new VelocityContext();
            context.put("suites", testSuites);

            velocityEngine.mergeTemplate(INDEX_TEMPLATE, "UTF-8", context, writer);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    private void mergeSuiteTemplate(TestSuite testSuite) {
        Writer writer = null;
        try {
            writer = createWriter(testSuite.getName() + ".html");

            VelocityContext context = new VelocityContext();
            context.put("testClasses", testSuite.getClasses());

            velocityEngine.mergeTemplate(SUITE_TEMPLATE, "UTF-8", context, writer);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    private Writer createWriter(String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(reportOutput + fileName);
            return new OutputStreamWriter(fileOutputStream, "UTF-8");
        } catch (IOException e) {
            throw new ReportGeneratorException(e);
        }
    }

    private void initVelocityEngine() {
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
        velocityEngine.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogSystem");
        velocityEngine.init();

        LOGGER.debug("Velocity engine initialized");
    }

    private void copyResources(String reportOutput) {
        copyDir("org/jtalks/tort/report/html/app", reportOutput + "app");
        copyDir("org/jtalks/tort/report/html/libs", reportOutput + "libs");
    }

    private void copyDir(String srcDir, String destDir) {
        URL appRes = this.getClass().getClassLoader().getResource(srcDir);
        File appDir = FileUtils.toFile(appRes);
        try {
            FileUtils.copyDirectory(appDir, new File(destDir));
        } catch (IOException e) {
            throw new ReportGeneratorException(e);
        }
    }

}
