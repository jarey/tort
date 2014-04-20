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
public class TestSuite implements Aggregated {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestSuite.class);
    private final static TestClass DEFAULT_CLASS = new TestClass("Default");

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

    public void addTestClass(String name) {
        addTestClass(new TestClass(name));
    }

    public void addTestClassIfAbsent(String name) {
        TestClass template = new TestClass(name);
        if (!classes.contains(template)) {
            addTestClass(template);
        }
    }

    private void addTestClass(TestClass testClass) {
        classes.add(testClass);

        LOGGER.debug("Test class was creates [{}]", testClass);
    }

    public TestClass getLastClass() {
        try {
            return classes.getLast();
        } catch (NoSuchElementException e) {
            LOGGER.warn("There is no test classes. Default test class is used");
            classes.add(DEFAULT_CLASS);
            return DEFAULT_CLASS;
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
        final TestSuite other = (TestSuite) obj;
        return Objects.equal(this.name, other.name);
    }

    @Override
    public String toString() {
        return "TestSuite [name=" + name + "]";
    }
}
