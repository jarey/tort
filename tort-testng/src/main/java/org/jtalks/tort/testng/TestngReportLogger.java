package org.jtalks.tort.testng;

import com.google.common.collect.ImmutableList;
import org.jtalks.tort.AbstractReportLogger;
import org.testng.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Mirian Dzhachvadze
 */
public class TestngReportLogger extends AbstractReportLogger {

    private final static List<Class<? extends Annotation>> TEST_NG_ANNOTATIONS =
            ImmutableList.of(Test.class, BeforeMethod.class, AfterMethod.class,
                    BeforeClass.class, AfterClass.class, BeforeSuite.class, AfterSuite.class);

    @Override
    protected boolean isMethodEligible(Method method) {
        return targetAnnotation(method);
    }

    private boolean targetAnnotation(Method method) {
        for (Class<? extends Annotation> testNgAnnotation : TEST_NG_ANNOTATIONS) {
            if (method.getAnnotation(testNgAnnotation) != null) {
                return true;
            }
        }
        return false;
    }

}
