package org.jtalks.tort.testng;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.jtalks.tort.ReportService;
import org.jtalks.tort.SimpleReportService;
import org.testng.*;

/**
 * @author Mirian Dzhachvadze
 */
public class TortTestngListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

    private final static ReportService reportService = SimpleReportService.INSTANCE;

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("TortTestngListener.beforeInvocation");
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        System.out.println("TortTestngListener.afterInvocation");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("TortTestngListener.onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("TortTestngListener.onTestSuccess");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("TortTestngListener.onTestFailure");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("TortTestngListener.onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("TortTestngListener.onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("TortTestngListener.onStart");
        //reportService.createTestClass(iTestContext.getCurrentXmlTest().getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("TortTestngListener.onFinish");
    }

    @Override
    public void onStart(final ISuite suite) {
        System.out.println("TortTestngListener.onStart SUITE");
        reportService.createTestSuite(suite.getName());
    }

    @Override
    public void onFinish(final ISuite suite) {
        System.out.println("TortTestngListener.onFinish");
        //reportService.finishTestSuite(suite.getName());
    }
}
