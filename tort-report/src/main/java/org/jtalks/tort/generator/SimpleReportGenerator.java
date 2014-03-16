package org.jtalks.tort.generator;

import org.jtalks.tort.model.Message;
import org.jtalks.tort.model.TestCase;
import org.jtalks.tort.model.TestClass;
import org.jtalks.tort.model.TestSuite;

import java.util.Collection;

public class SimpleReportGenerator implements ReportGenerator {
    @Override
    public void generate(Collection<TestSuite> testSuites) {
        for (TestSuite testSuite : testSuites) {
            System.out.println("[" + testSuite.getStatus() + "]" + testSuite.getName());
            for (TestClass testClass : testSuite.getClasses()) {
                System.out.println("Class: " + testClass.getName());
                for (TestCase testCase : testClass.getTestCases()) {
                    System.out.println("Case: " + testCase.getName());
                    for (Message message : testCase.getMessages()) {
                        System.out.println(getIndentString(message.getIndent()) + "[" + message.getLevel() + "] " + message.getValue());
                    }
                }
            }
        }
    }

    private static String getIndentString(int indent) {
        String s = "";
        for (int i = 0; i < indent; i++) {
            s += "-";
        }
        return s;
    }

}
