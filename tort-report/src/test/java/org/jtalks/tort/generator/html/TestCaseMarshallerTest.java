package org.jtalks.tort.generator.html;

import org.jtalks.tort.model.Level;
import org.jtalks.tort.model.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestCaseMarshallerTest {
    @Test
    public void testJson() throws Exception {
        TestCase testCase = new TestCase("method1", System.nanoTime());
        testCase.addMessage(0, "1", Level.INFO);
        testCase.addMessage(0, "2", Level.INFO);
        testCase.addMessage(1, "2-1", Level.INFO);
        testCase.addMessage(2, "2-2(1)", Level.INFO);
        testCase.addMessage(2, "2-2(2)", Level.INFO);
        testCase.addMessage(3, "2-2-1", Level.INFO);
        testCase.addMessage(0, "3", Level.INFO);
        testCase.addMessage(1, "3-1(1)", Level.INFO);
        testCase.addMessage(1, "3-1(2)", Level.INFO);
        testCase.addMessage(2, "3-2-1", Level.INFO);
        testCase.addMessage(3, "3-3-1", Level.INFO);
        testCase.addMessage(0, "4", Level.INFO);

        String json = TestCaseMarshaller.marshal(testCase);

        Assert.assertEquals(json,
                "[{\"text\":\"1\",\"type\":\"passed\"},"+
                "{\"text\":\"2\",\"type\":\"passed\",\"children\":["+
                "{\"text\":\"2-1\",\"type\":\"passed\",\"children\":["+
                "{\"text\":\"2-2(1)\",\"type\":\"passed\"},"+
                "{\"text\":\"2-2(2)\",\"type\":\"passed\",\"children\":["+
                "{\"text\":\"2-2-1\",\"type\":\"passed\"}"+
                "]}"+
                "]}"+
                "]},"+
                "{\"text\":\"3\",\"type\":\"passed\",\"children\":["+
                "{\"text\":\"3-1(1)\",\"type\":\"passed\"},"+
                "{\"text\":\"3-1(2)\",\"type\":\"passed\",\"children\":["+
                "{\"text\":\"3-2-1\",\"type\":\"passed\",\"children\":["+
                "{\"text\":\"3-3-1\",\"type\":\"passed\"}"+
                "]}"+
                "]}"+
                "]},"+
                "{\"text\":\"4\",\"type\":\"passed\"}]"
        );
    }
}
