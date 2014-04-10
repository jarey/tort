package org.jtalks.tort.generator.html;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import org.jtalks.tort.model.Level;
import org.jtalks.tort.model.Message;
import org.jtalks.tort.model.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestCaseMarshaller {

    public static String marshal(TestCase testCase) {
        MessageJson prevTopLevelJson = null;

        List<MessageJson> json = new ArrayList<MessageJson>();
        for (Message message : testCase.getMessages()) {
            MessageJson messageJson = marshalMessage(message);

            if (message.getIndent() != 0) {
                addMessage(0, prevTopLevelJson, message, messageJson);
            }

            if (message.getIndent() == 0) {
                prevTopLevelJson = messageJson;

                json.add(messageJson);
            }
        }

        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(json);
    }

    private static void addMessage(int currentLevel, MessageJson topLevelJson, Message child, MessageJson childJson) {
        if (child.getIndent() - currentLevel == 1) {
            topLevelJson.addChild(childJson);
        } else {
            addMessage(++currentLevel, topLevelJson.getLastChild(), child, childJson);
        }
    }

    private static MessageJson marshalMessage(Message message) {
        MessageJson messageJson = new MessageJson();
        messageJson.text = message.getValue();
        messageJson.type = convertLevel(message.getLevel());

        return messageJson;
    }

    private static String convertLevel(Level level) {
        if (Level.ERROR == level) {
            return "failed";
        }

        return "passed";
    }

    static class MessageJson {
        @Expose
        String text;

        @Expose
        String type;

        @Expose
        List<MessageJson> children;

        int indent;

        void addChild(MessageJson messageJson) {
            if (children == null) {
                children = new ArrayList<MessageJson>();
            }
            children.add(messageJson);
        }

        public MessageJson getLastChild() {
            return children.get(children.size() - 1);
        }
    }

}
