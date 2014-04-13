package org.jtalks.tort;

import org.jtalks.tort.model.*;

import java.util.Deque;

/**
 * @author Mirian Dzhachvadze
 */
public interface ReportStorage {

    TestSuite addTestSuite(String name);
    TestSuite addTestSuiteIfAbsent(String name);

    TestClass addTestClass(String name);
    TestClass addTestClassIfAbsent(String name);

    TestCase addTestCase(String name);
    void finishTestCase(Status status, String name);

    Deque<TestSuite> getTestSuites();

    TestCase getLastCase();

    void addMessage(int indent, String message, Level error);
}
