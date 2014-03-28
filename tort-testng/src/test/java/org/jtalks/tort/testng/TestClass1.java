package org.jtalks.tort.testng;


import org.jtalks.tort.ReportService;
import org.jtalks.tort.SimpleReportService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass1 {

    private final static ReportService reportService = SimpleReportService.INSTANCE;

    @Test
    public void doSomeNoise() throws Exception {
        reportService.info("TestClass1.doSomeNoise");
        String noise = new NoiseMaker().doIt();
        Assert.assertNotNull(noise);
    }

    //@Test
    public void anotherTest() throws Exception {
        try {
            int s = 42 / 0;
        }
        catch (ArithmeticException e) {
            Assert.fail("Division by zero");
        }
    }

    class NoiseMaker {
        String doIt() {
            return doSomethingSpecial();
        }

        private String doSomethingSpecial() {
            return "4324FDSFZ@#$#@";
        }
    }
}
