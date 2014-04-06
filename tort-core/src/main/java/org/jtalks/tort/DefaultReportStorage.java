package org.jtalks.tort;

import com.google.common.collect.Lists;
import org.jtalks.tort.model.Status;
import org.jtalks.tort.model.TestCase;
import org.jtalks.tort.model.TestClass;
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
    public TestClass addTestClass(String name) {
        TestClass testClass = getLastSuite().addTestClass(name);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Test class was creates: " + testClass);
        }

        return testClass;
    }

    @Override
    public TestClass addTestClassIfAbsent(String name) {
        return getLastSuite().addTestClassIfAbsent(name);
    }

    private TestSuite getLastSuite() {
        TestSuite last = null;
        try {
            last = testSuites.getLast();
        } catch (NoSuchElementException e) {
            LOGGER.info("There is no test suites. Default suite is returned");
            testSuites.add(DEFAULT_SUITE);
            return DEFAULT_SUITE;
        }
        return last;
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
    public TestCase addTestCase(String name) {
        TestCase testCase = getLastSuite().getLastClass().addTestCase(name, System.nanoTime());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Test case was created: " + testCase);
        }

        return testCase;
    }

    @Override
    public TestCase getLastCase() {
        return getLastSuite().getLastClass().getLastCase();
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
