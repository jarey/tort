package org.jtalks.junit;

import org.jtalks.tort.Tort;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SimpleTest {

    @Test
    public void doSomeNoise() throws Exception {
        Tort.info("I ma gonna make some noise");

        String noise = new NoiseMaker().doIt();
        assertNotNull(noise);
    }

    @Test
    public void anotherTest() throws Exception {
        Tort.info("Gonna divide to zero");
        try {
            int s = 42/0;
        } catch (ArithmeticException e) {
            Tort.error("Chit happens when try to divide to zero");
        }

    }

    class NoiseMaker {
        String doIt() {
            Tort.info("Before making noise");
            String noise = "4324FDSFZ@#$#@";
            Tort.info("Successfully made noise");

            return noise;
        }
    }
}
