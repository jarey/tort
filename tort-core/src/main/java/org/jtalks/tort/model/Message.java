package org.jtalks.tort.model;

public class Message {
    private String value;
    private Level level;

    private int indent;

    public Message(int indent, String message, Level info) {
        this.indent = indent;
        this.value = message;
        this.level = info;
    }

    public String getValue() {
        return value;
    }

    public Level getLevel() {
        return level;
    }

    public int getIndent() {
        return indent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "value='" + value + '\'' +
                ", level=" + level +
                ", indent=" + indent +
                '}';
    }
}
