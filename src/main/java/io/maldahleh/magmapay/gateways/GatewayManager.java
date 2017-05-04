package io.maldahleh.magmapay.gateways;

import io.maldahleh.magmapay.MagmaPay;

import java.util.HashSet;

public class GatewayManager {
    private MagmaPay magmaPay;
    private HashSet<Gateway> gateways;

    public GatewayManager(MagmaPay magmaPay) {
        this.magmaPay = magmaPay;
        this.gateways = new HashSet<>();
    }

    public void registerGateway(Gateway gateway) {
        gateways.add(gateway);
    }

    public Gateway getGatewayImplementation(Gateway gateway) {
        for (Gateway g : gateways) {
            if (g.getClass().getName().equals(gateway.getClass().getName())) {
                return g;
            }
        }

        return null;
    }
}
