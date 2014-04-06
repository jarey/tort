package org.jtalks.junit;

import org.jtalks.tort.Tort;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SimpleTest {

    private static Tort REPORT_SERVICE;

    @BeforeClass
    public static void onlyOnce() {
        REPORT_SERVICE = Tort.getInstance();

        REPORT_SERVICE.addTestClass(SimpleTest.class.getSimpleName());
    }

    @Test
    public void doSomeNoise() throws Exception {
        REPORT_SERVICE.addTestCase("doSomeNoise");
        REPORT_SERVICE.info("I am gonna make some noise");

        String noise = new NoiseMaker().doIt();
        assertNotNull(noise);

        REPORT_SERVICE.finishTestCase(Status.COMPLETED, "doSomeNoise");
    }

    @Test
    public void anotherTest() throws Exception {
        REPORT_SERVICE.addTestCase("anotherTest");
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
        new HtmlReportGenerator("tort-report/target/").generate(REPORT_SERVICE.getTestSuites());
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
