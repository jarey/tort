package org.jtalks.tort.model;

import com.google.common.collect.Lists;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

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
        long durationNanos = end - start;
        long durationMillis = TimeUnit.MILLISECONDS.convert(durationNanos, TimeUnit.NANOSECONDS);

//        if (durationMillis == 0) {
//            return ""
//        }

        return String.valueOf(durationNanos);
    }

    public boolean isFailed() {
        return status == Status.FAILED;
    }

    public boolean isSuccess() {
        return status == Status.COMPLETED;
    }
}
