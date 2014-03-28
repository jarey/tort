package org.jtalks.tort.testng;

import org.jtalks.tort.ReportService;
import org.jtalks.tort.SimpleReportService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Mirian Dzhachvadze
 */
public class TestClass2 {

    private final static ReportService reportService = SimpleReportService.INSTANCE;
    
    @Test
    public void testMethod2() throws Exception {
        reportService.info("TestClass2.testMethod2");
    }
}
