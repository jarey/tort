package org.jtalks.tort.model;

import java.util.List;

/**
 * @author Mirian Dzhachvadze
 */
public class TestSuite {
    
    private Status status;
    
    private List<TestCases> classes;

    public int getSuccessTestsCount() {
        throw new UnsupportedOperationException();
    }

    public int getFailedTestsCount() {
        throw new UnsupportedOperationException();
    }
}
