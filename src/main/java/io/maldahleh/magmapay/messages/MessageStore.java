package io.maldahleh.magmapay.messages;

import java.util.HashMap;

public class MessageStore {
    private HashMap<Enum, String> messageMap;

    public MessageStore() { messageMap = new HashMap<>(); }

    public void registerMessage(Enum key, String message) { messageMap.put(key, message); }

    public void getMessage(Enum message) { messageMap.getOrDefault(message, null); }
}