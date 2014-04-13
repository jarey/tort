package org.jtalks.tort.model;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * @author Mirian Dzhachvadze
 */
public class TestClass implements Aggregated {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestClass.class);
    private static final TestCase DEFAULT_CASE = new TestCase("default", 0);

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
        int sum = 0;
        for (TestCase testCase: testCases) {
            sum += testCase.isSuccess() ? 1 : 0;
        }

        return sum;
    }

    @Override
    public int getFailedCount() {
        int sum = 0;
        for (TestCase testCase: testCases) {
            sum += testCase.isFailed() ? 1 : 0;
        }

        return sum;
    }

    public TestCase getLastCase() {
        try {
            return testCases.getLast();
        } catch (NoSuchElementException e) {
            testCases.add(DEFAULT_CASE);
            LOGGER.warn("There is no test cases. Default test case is used");
            return DEFAULT_CASE;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TestClass other = (TestClass) obj;
        return Objects.equal(this.name, other.name);
    }

    @Override
    public String toString() {
        return "TestClass [name=" + name + "]";
    }
}
