package org.jtalks.tort.generator;

import org.jtalks.tort.model.TestSuite;

import java.util.Collection;

public interface ReportGenerator {
    void generate(Collection<TestSuite> testSuites) throws ReportGeneratorException;
}
