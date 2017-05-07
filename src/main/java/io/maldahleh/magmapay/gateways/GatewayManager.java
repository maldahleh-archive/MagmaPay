package io.maldahleh.magmapay.gateways;

import io.maldahleh.magmapay.MagmaPay;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GatewayManager {
    private MagmaPay magmaPay;
    private ExecutorService executorService;
    private HashMap<AvailableGateway, Gateway> gateways;

    public GatewayManager(MagmaPay magmaPay) {
        this.magmaPay = magmaPay;
        this.executorService = Executors.newCachedThreadPool();
        this.gateways = new HashMap<>();
    }

    /**
     * Register a gateway for use.
     *
     * @param availableGateway the enum of the gateway for lookup
     * @param gateway the implementation of the gateway
     */
    public void registerGateway(AvailableGateway availableGateway, Gateway gateway) {
        gateways.put(availableGateway, gateway);
    }

    /**
     * Retrieve a gateway's implementation for use.
     *
     * @param gateway the enum of the gateway which needs to be retrieved
     * @return the implementation of the gateway, null if not registered
     */
    public Gateway getGatewayImplementation(AvailableGateway gateway) {
        return gateways.getOrDefault(gateway, null);
    }
}