package io.maldahleh.magmapay.gateways;

import io.maldahleh.magmapay.MagmaPay;

import java.util.HashMap;

public class GatewayManager {
    private MagmaPay magmaPay;
    private HashMap<AvailableGateway, Gateway> gateways;

    public GatewayManager(MagmaPay magmaPay) {
        this.magmaPay = magmaPay;
        this.gateways = new HashMap<>();
    }

    public void registerGateway(AvailableGateway availableGateway, Gateway gateway) {
        gateways.put(availableGateway, gateway);
    }

    public Gateway getGatewayImplementation(AvailableGateway gateway) {
        return gateways.getOrDefault(gateway, null);
    }
}