package org.jtalks.junit;

import org.jtalks.tort.ReportService;
import org.jtalks.tort.SimpleReportService;
import org.jtalks.tort.model.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SimpleTest {

    private static final ReportService REPORT_SERVICE = SimpleReportService.INSTANCE;

    @BeforeClass
    public static void onlyOnce() {
        REPORT_SERVICE.createTestClass(SimpleTest.class.getName());
    }

    @Test
    public void doSomeNoise() throws Exception {
        REPORT_SERVICE.createTestCase("doSomeNoise");
        REPORT_SERVICE.info("I am gonna make some noise");

        String noise = new NoiseMaker().doIt();
        assertNotNull(noise);

        REPORT_SERVICE.finishTestCase(Status.COMPLETED, "doSomeNoise");
    }

    @Test
    public void anotherTest() throws Exception {
        REPORT_SERVICE.createTestCase("anotherTest");
        REPORT_SERVICE.info("Gonna divide to zero");
        try {
            int s = 42 / 0;
        } catch (ArithmeticException e) {
            REPORT_SERVICE.error("Chit happens when try to divide to zero");
            REPORT_SERVICE.finishTestCase(Status.FAILED, "anotherTest");
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        for (TestSuite testSuite : REPORT_SERVICE.getTestSuites()) {
            System.out.println("[" + testSuite.getStatus() + "]" + testSuite.getName());
            for (TestClass testClass : testSuite.getClasses()) {
                System.out.println("Class: " + testClass.getClassName());
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

    class NoiseMaker {
        String doIt() {
            REPORT_SERVICE.info(1, "Before making noise");
            String noise = doSomethingSpecial();
            REPORT_SERVICE.info(1, "Successfully made noise");

            return noise;
        }

        private String doSomethingSpecial() {
            REPORT_SERVICE.info(2, "Magic here");
            return "4324FDSFZ@#$#@";
        }
    }
}
