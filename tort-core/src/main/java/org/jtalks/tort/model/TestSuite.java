package org.jtalks.tort.model;

import com.google.common.base.Objects;
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

    public TestClass addTestClassIfAbsent(String name) {
        if (!classes.contains(new TestClass(name))) {
            return addTestClass(name);
        }

        return getTestClass(name);
    }

    public TestClass getTestClass(String testClassName) {
        for (TestClass testClass : classes) {
            if (testClassName.equals(testClass.getName())) {
                return testClass;
            }
        }

        return null;
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
