package org.jtalks.tort.junit;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TortRunListener extends RunListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TortRunListener.class);

    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println("TortRunListener.testRunStarted");

        List<Description> children = description.getChildren();
        String testClassName = children.get(0).getDisplayName();
        if (children.size() > 1) {
            throw new RuntimeException("Just check when it's possible: " + toString(children));
        }
//        Tort.addTestClass(testClassName);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void testStarted(Description description) throws Exception {
        String methodName = stripClassName(description.getDisplayName());
        String className = description.getTestClass().getName();

//        Tort.addTestCase(className, methodName, System.nanoTime());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private String stripClassName(String displayName) {
        // source string: anotherTest(org.jtalks.junit.SimpleTest)
        return displayName.substring(0, displayName.indexOf("("));
    }

    private String toString(List<Description> children) {
        String s = "";
        for (Description child : children) {
            s += child.getDisplayName();
        }
        return s;
    }
}
