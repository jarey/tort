package org.jtalks.tort.testng;

import org.jtalks.tort.ReportService;
import org.jtalks.tort.SimpleReportService;
import org.junit.Ignore;
import org.testng.annotations.Test;

/**
 * @author Mirian Dzhachvadze
 */
@Ignore
public class TestClass2 {

    private final static ReportService reportService = SimpleReportService.INSTANCE;
    
    @Test
    public void testMethod2() throws Exception {
        reportService.info("TestClass2.testMethod2");
    }
}
