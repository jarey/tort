package org.jtalks.tort;

import org.jtalks.tort.model.Status;
import org.jtalks.tort.model.TestCase;
import org.jtalks.tort.model.TestClass;
import org.jtalks.tort.model.TestSuite;

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
}
