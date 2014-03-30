package org.jtalks.tort.model;

import com.google.common.collect.Lists;

import java.util.Queue;

/**
 * @author Mirian Dzhachvadze
 */
public class TestCase {
    private String name;

    private Status status;
    private Failure failure;

    private Queue<Message> messages = Lists.newLinkedList();

    private long start;
    private long end;

    public TestCase(String methodName, long startTime) {
        this.name = methodName;
        this.start = startTime;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Failure getFailure() {
        return failure;
    }

    public Queue<Message> getMessages() {
        return messages;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public void addMessage(int indent, String message, Level info) {
        messages.add(new Message(indent, message, info));
    }

    public void finish(Status status, long time) {
        this.status = status;
        this.end = time;
    }

    public String getDuration() {
        long millis = (long) ((end - start) / 1e6);
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;

        String timeString = "";
        if (hour > 0) {
            timeString += hour + " h ";
        }
        if (minute > 0) {
            timeString += minute + " min ";
        }
        if (second > 0) {
            timeString += second + " sec";
        }
        return timeString.length() > 0 ? timeString : "0 sec";
    }

    public boolean isFailed() {
        return status == Status.FAILED;
    }

    public boolean isSuccess() {
        return status == Status.COMPLETED;
    }

    @Override
    public String toString() {
        return "TestCase [name=" + name + "]";
    }
}
