package com.gs.telegram.message.request;

public abstract class AbstractChatMessage implements ChatMessage {

    private ChatMetaData chatMetaData;

    public AbstractChatMessage(ChatMetaData chatMetaData) {
        this.chatMetaData = chatMetaData;
    }

    @Override
    public ChatMetaData getChatMetaData() {
        return chatMetaData;
    }
}
