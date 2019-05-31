package com.gs.telegram.message.request;

public class CallbackChatMessage extends AbstractChatMessage {

    private String callbackData;
    private String callbackId;

    public CallbackChatMessage(ChatMetaData chatMetaData, String callbackData, String callbackId) {
        super(chatMetaData);
        this.callbackData = callbackData;
        this.callbackId = callbackId;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public String getCallbackData() {
        return callbackData;
    }
}
