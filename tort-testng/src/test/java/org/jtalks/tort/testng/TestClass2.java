package org.jtalks.tort.testng;

import org.jtalks.tort.Tort;
import org.junit.Ignore;
import org.testng.annotations.Test;

/**
 * @author Mirian Dzhachvadze
 */
@Ignore
public class TestClass2 {

    private final static Tort LIFECYCLE = Tort.getInstance();
    
    @Test
    public void testMethod2() throws Exception {
        LIFECYCLE.info("TestClass2.testMethod2");
    }
}
