package org.jtalks.tort.model;

import com.google.common.collect.Lists;

import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * @author Mirian Dzhachvadze
 */
public class TestClass implements Aggregated {
    private Deque<TestCase> testCases = Lists.newLinkedList();

    private String className;

    public TestClass(String className) {
        this.className = className;
    }

    public TestCase addTestCase(String methodName, long startTime) {
        TestCase testCase = new TestCase(methodName, startTime);
        testCases.add(testCase);

        return testCase;
    }

    public Deque<TestCase> getTestCases() {
        return testCases;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public int getSuccessCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getFailedCount() {
        throw new UnsupportedOperationException();
    }

    public TestCase getLastCase() {
        TestCase last = null;
        try {
            last = testCases.getLast();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("There is no test cases", e);
        }
        return last;
    }
}
