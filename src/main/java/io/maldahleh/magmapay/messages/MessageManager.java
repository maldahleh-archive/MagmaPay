package io.maldahleh.magmapay.messages;

import io.maldahleh.magmapay.MagmaPay;
import io.maldahleh.magmapay.gateways.AvailableGateway;

import java.util.HashMap;

public class MessageManager {
    private MagmaPay magmaPay;
    private HashMap<AvailableGateway, MessageStore> messageStoreMap;

    public MessageManager(MagmaPay magmaPay) {
        this.magmaPay = magmaPay;
        this.messageStoreMap = new HashMap<>();
    }

    /**
     * Creates an empty message store for the specified gateway.
     *
     * @param gateway the gateway the message store will be created for
     */
    public void createMessageStore(AvailableGateway gateway) {
        createMessageStore(gateway, new MessageStore());
    }

    /**
     * Creates a message store for the specified gateway.
     *
     * @param gateway the gateway the message store will be created for
     * @param messageStore the message store to be assigned to the gateway
     */
    public void createMessageStore(AvailableGateway gateway, MessageStore messageStore) {
        messageStoreMap.put(gateway, messageStore);
    }

    /**
     * Get the message store of the specified gateway.
     *
     * @param gateway the gateway of the message store to be retrieved
     * @return the message store, null if not found
     */
    public MessageStore getMessageStore(AvailableGateway gateway) {
        return messageStoreMap.getOrDefault(gateway, null);
    }
}