package org.jtalks.junit;

import org.jtalks.tort.junit.TortRunListener;
import org.junit.runner.JUnitCore;

public class JUnitStandaloneRunner {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        runner.addListener(new TortRunListener());
        runner.run(SimpleTest.class);
    }
}
