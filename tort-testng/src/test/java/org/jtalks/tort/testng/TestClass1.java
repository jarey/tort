package org.jtalks.tort.testng;


import org.jtalks.tort.ReportService;
import org.jtalks.tort.SimpleReportService;
import org.junit.Ignore;
import org.testng.Assert;
import org.testng.annotations.Test;

@Ignore
public class TestClass1 {

    private final static ReportService reportService = SimpleReportService.INSTANCE;

    @Test
    public void successMethod() throws Exception {
        reportService.info("TestClass1.successMethod");
        String noise = new NoiseMaker().doIt();
        Assert.assertNotNull(noise);
    }

//    @Test
//    public void failedMethod() throws Exception {
//        try {
//            int s = 42 / 0;
//        }
//        catch (ArithmeticException e) {
//            Assert.fail("Division by zero");
//        }
//    }

//    @Test
//    public void brokenMethod() throws Exception {
//        Assert.assertEquals(1, 1);
//        Assert.assertEquals(1, 1.0);
//    }

    class NoiseMaker {
        String doIt() {
            return doSomethingSpecial();
        }

        private String doSomethingSpecial() {
            return "4324FDSFZ@#$#@";
        }
    }
}
