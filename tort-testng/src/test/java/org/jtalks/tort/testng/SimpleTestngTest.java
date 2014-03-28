package org.jtalks.tort.testng;


import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTestngTest {

    @Test
    public void doSomeNoise() throws Exception {
        String noise = new NoiseMaker().doIt();
        Assert.assertNotNull(noise);
    }

    @Test
    public void anotherTest() throws Exception {
        try {
            int s = 42 / 0;
        } catch (ArithmeticException e) {
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
