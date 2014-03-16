package org.jtalks.tort.model;

import com.google.common.collect.Lists;

import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * @author Mirian Dzhachvadze
 */
public class TestSuite implements Aggregated {

    private String name;

    private Status status;
    private Deque<TestClass> classes = Lists.newLinkedList();

    public TestSuite(String name) {
        this.name = name;
    }

    @Override
    public int getSuccessCount() {
        int sum = 0;
        for (TestClass testClass : classes) {
            sum += testClass.getSuccessCount();
        }

        return sum;
    }

    @Override
    public int getFailedCount() {
        int sum = 0;
        for (TestClass testClass : classes) {
            sum += testClass.getFailedCount();
        }

        return sum;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Deque<TestClass> getClasses() {
        return classes;
    }

    public TestClass addTestClass(String name) {
        TestClass testClass = new TestClass(name);
        classes.add(testClass);

        return testClass;
    }

    public TestClass getLastClass() {
        TestClass last = null;
        try {
            last = classes.getLast();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("There is no test classes", e);
        }
        return last;
    }
}
