package org.jtalks.tort.testng;

import org.jtalks.tort.ReportService;
import org.jtalks.tort.SimpleReportService;
import org.jtalks.tort.model.Status;
import org.testng.*;

/**
 * @author Mirian Dzhachvadze
 */
public class TortTestngListener implements ISuiteListener, IInvokedMethodListener {

    private final static ReportService reportService = SimpleReportService.INSTANCE;

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        reportService.addTestClassIfAbsent(iInvokedMethod.getTestMethod().getTestClass().getName());
        reportService.addTestCase(iInvokedMethod.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        reportService.finishTestCase(mapStatus(iTestResult.getStatus()),
                iInvokedMethod.getTestMethod().getMethodName());
    }

    private Status mapStatus(int iTestResult) {
        //iTestResult - see org.testng.ITestResult
        switch (iTestResult) {
            case 1: // SUCCESS
                return Status.COMPLETED;
            case 2: // FAILURE
                return Status.FAILED;
            case 3: // SKIP
                return Status.PASSED;
            default:
                return Status.BROKEN;
        }
    }

    @Override
    public void onStart(final ISuite suite) {
        reportService.addTestSuiteIfAbsent(suite.getName());
    }

    @Override
    public void onFinish(final ISuite suite) {
        System.out.println("TortTestngListener.onFinish");
        //reportService.finishTestSuite(suite.getName());
    }
}
