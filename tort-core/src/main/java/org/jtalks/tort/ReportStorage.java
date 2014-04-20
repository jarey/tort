package org.jtalks.tort;

import org.jtalks.tort.model.Level;
import org.jtalks.tort.model.Status;
import org.jtalks.tort.model.TestCase;
import org.jtalks.tort.model.TestSuite;

import java.util.Deque;

/**
 * @author Mirian Dzhachvadze
 */
public interface ReportStorage {

    TestSuite addTestSuite(String name);
    TestSuite addTestSuiteIfAbsent(String name);

    void addTestClass(String name);
    void addTestClassIfAbsent(String name);

    void addTestCase(String name);
    void addTestCaseIfAbsent(String methodName);
    void finishTestCase(Status status, String name);

    Deque<TestSuite> getTestSuites();

    TestCase getLastCase();

    void addMessage(int indent, String message, Level error);


}
