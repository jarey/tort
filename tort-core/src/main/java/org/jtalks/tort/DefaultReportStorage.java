package org.jtalks.tort;

import com.google.common.collect.Lists;
import org.jtalks.tort.model.Level;
import org.jtalks.tort.model.Status;
import org.jtalks.tort.model.TestCase;
import org.jtalks.tort.model.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * @author Mirian Dzhachvadze
 */
public class DefaultReportStorage implements ReportStorage {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultReportStorage.class);
    private final static TestSuite DEFAULT_SUITE = new TestSuite("default");

    private Deque<TestSuite> testSuites = Lists.newLinkedList();

    @Override
    public TestSuite addTestSuite(String name) {
        TestSuite testSuite = new TestSuite(name);
        testSuites.add(testSuite);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Test suite was created: " + testSuite);
        }

        return testSuite;
    }

    @Override
    public TestSuite addTestSuiteIfAbsent(String name) {
        if (!testSuites.contains(new TestSuite(name))) {
            return addTestSuite(name);
        }

        return getTestSuite(name);
    }

    @Override
    public void addTestClass(String name) {
        getLastSuite().addTestClass(name);
    }

    @Override
    public void addTestClassIfAbsent(String name) {
        getLastSuite().addTestClassIfAbsent(name);
    }

    private TestSuite getLastSuite() {
        try {
            return testSuites.getLast();
        } catch (NoSuchElementException e) {
            LOGGER.info("There is no test suites. Default suite is used");
            testSuites.add(DEFAULT_SUITE);
            return DEFAULT_SUITE;
        }
    }

    private TestSuite getTestSuite(String testSuiteName) {
        for (TestSuite testSuite : testSuites) {
            if (testSuiteName.equals(testSuite.getName())) {
                return testSuite;
            }
        }

        return null;
    }

    @Override
    public void addTestCase(String name) {
        getLastSuite().getLastClass().addTestCase(name, System.nanoTime());
    }

    @Override
    public void addTestCaseIfAbsent(String methodName) {
        getLastSuite().getLastClass().addTestCaseIfAbsent(methodName, System.nanoTime());
    }

    @Override
    public TestCase getLastCase() {
        return getLastSuite().getLastClass().getLastCase();
    }

    @Override
    public void addMessage(int indent, String message, Level error) {
        getLastCase().addMessage(indent, message, error);
    }

    @Override
    public void finishTestCase(Status status, String name) {
        getLastCase().finish(status, System.nanoTime());
    }

    @Override
    public Deque<TestSuite> getTestSuites() {
        return testSuites;
    }

}
