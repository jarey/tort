package org.jtalks.tort.model;

import com.google.common.collect.Lists;

import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * @author Mirian Dzhachvadze
 */
public class TestClass implements Aggregated {
    private Deque<TestCase> testCases = Lists.newLinkedList();

    private String name;

    public TestClass(String name) {
        this.name = name;
    }

    public TestCase addTestCase(String methodName, long startTime) {
        TestCase testCase = new TestCase(methodName, startTime);
        testCases.add(testCase);

        return testCase;
    }

    public Deque<TestCase> getTestCases() {
        return testCases;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getSuccessCount() {
        return 0;
    }

    @Override
    public int getFailedCount() {
        return 0;
    }

    public TestCase getLastCase() {
        TestCase last;
        try {
            last = testCases.getLast();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("There is no test cases", e);
        }
        return last;
    }
}
