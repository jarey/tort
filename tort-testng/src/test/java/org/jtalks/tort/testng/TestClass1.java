package org.jtalks.tort.testng;


import org.jtalks.tort.Tort;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass1 {

    private final static Tort LIFECYCLE = Tort.getInstance();

    @Test(enabled = false)
    public void successMethod() throws Exception {
        LIFECYCLE.info("TestClass1.successMethod");
        String noise = new NoiseMaker().doIt();
        Assert.assertNotNull(noise);
    }

    class NoiseMaker {
        String doIt() {
            LIFECYCLE.info("Make things here");
            return doSomethingSpecial();
        }

        private String doSomethingSpecial() {
            LIFECYCLE.info("Third level");
            LIFECYCLE.error("Something goes wrong");

            return "blabla";
        }
    }
}
