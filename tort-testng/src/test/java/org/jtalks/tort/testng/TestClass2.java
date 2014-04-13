package org.jtalks.tort.testng;

import org.jtalks.tort.Tort;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * @author Mirian Dzhachvadze
 */
@Listeners({TortTestngListener.class, HtmlTestngReporter.class})
public class TestClass2 {

    private final static Tort LIFECYCLE = Tort.getInstance();

    @Test
    public void testMethod2() throws Exception {
        LIFECYCLE.info("TestClass2.testMethod2");
    }
}
