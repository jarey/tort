package org.jtalks.tort.testng;

import org.testng.*;

/**
 * @author Mirian Dzhachvadze
 */
public class TortTestngListener implements ITestListener, IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        throw new UnsupportedOperationException();
    }
}
