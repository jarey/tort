package org.jtalks.tort.model;

import java.util.List;

/**
 * @author Mirian Dzhachvadze
 */
public class TestCase extends Result {
    private long start;
    private long end;
    
    private Failure failure;

    private List<Message> messages;
}
