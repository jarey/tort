package org.jtalks.tort;

import org.jtalks.tort.model.Status;
import org.jtalks.tort.model.TestCase;
import org.jtalks.tort.model.TestClass;
import org.jtalks.tort.model.TestSuite;

import java.util.Deque;

public interface ReportService {

    TestSuite createTestSuite(String name);

    TestClass createTestClass(String name);

    TestCase createTestCase(String name);

    void info(int indent, String message);

    void info(String message);

    void error(int indent, String message);

    void error(String message);

    void indentRight(int indent);

    void indentRight();

    void indentLeft(int indent);

    void indentLeft();

    void finishTestCase(Status status, String name);

    Deque<TestSuite> getTestSuites();

    void finishTestSuite(String name);
}
