package org.jtalks.tort.generator.html;

import com.google.gson.Gson;
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

        return new Gson().toJson(json);
    }

    private static void addMessage(int currentLevel, MessageJson prevTopLevelJson, Message child, MessageJson childJson) {
        if (child.getIndent() - currentLevel == 1) {
            prevTopLevelJson.addChild(childJson);
        } else {
            addMessage(++currentLevel, prevTopLevelJson.getLastChild(), child, childJson);
        }
    }

    private static MessageJson marshalMessage(Message message) {
        MessageJson messageJson = new MessageJson();
        messageJson.text = message.getValue();

        //todo change to message level
        messageJson.type = "passed";

        return messageJson;
    }

    static class MessageJson {
        String text;
        String type;
        List<MessageJson> children;

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
