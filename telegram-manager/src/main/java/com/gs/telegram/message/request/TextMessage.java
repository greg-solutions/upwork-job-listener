package com.gs.telegram.message.request;

public class TextMessage extends AbstractChatMessage {

    private String text;

    public TextMessage(ChatMetaData chatMetaData, String text) {
        super(chatMetaData);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
