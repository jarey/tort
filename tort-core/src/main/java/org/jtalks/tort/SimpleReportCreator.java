package org.jtalks.tort;

import com.google.common.collect.Lists;
import org.jtalks.tort.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.NoSuchElementException;

//@NotThreadSafe
public class SimpleReportCreator implements ReportService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleReportCreator.class);

    public static final SimpleReportCreator INSTANCE = new SimpleReportCreator();

    private int currentIndent;

    private static final TestSuite DEFAULT_SUITE = new TestSuite("default");

    private Deque<TestSuite> testSuites = Lists.newLinkedList();

    @Override
    public TestSuite createTestSuite(String name) {
        TestSuite testSuite = new TestSuite(name);
        testSuites.add(testSuite);

        return testSuite;
    }

    @Override
    public TestClass createTestClass(String name) {
        return getLastSuite().addTestClass(name);
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

    @Override
    public TestCase createTestCase(String name) {
        return getLastSuite().getLastClass().addTestCase(name, System.nanoTime());
    }

    @Override
    public void info(int indent, String message) {
        getLastCase().addMessage(indent, message, Level.INFO);
    }

    @Override
    public void info(String message) {
        info(currentIndent, message);
    }

    @Override
    public void indentRight(int indent) {
        currentIndent += indent;
    }

    @Override
    public void indentRight() {
        currentIndent++;
    }

    @Override
    public void indentLeft(int indent) {
        currentIndent -= indent;
    }

    @Override
    public void indentLeft() {
        currentIndent--;
    }

    @Override
    public void error(int indent, String message) {
        getLastCase().addMessage(indent, message, Level.ERROR);
    }

    private TestCase getLastCase() {
        return getLastSuite().getLastClass().getLastCase();
    }

    @Override
    public void error(String message) {
        error(currentIndent, message);
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
