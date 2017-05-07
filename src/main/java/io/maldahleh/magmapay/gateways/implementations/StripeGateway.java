package io.maldahleh.magmapay.gateways.implementations;

import com.stripe.Stripe;

import io.maldahleh.magmapay.charges.requests.ChargeRequest;
import io.maldahleh.magmapay.charges.response.ChargeFailStatus;
import io.maldahleh.magmapay.charges.response.ChargeResponse;
import io.maldahleh.magmapay.gateways.Gateway;
import io.maldahleh.magmapay.gateways.GatewayManager;

public class StripeGateway extends Gateway {
    private GatewayManager gatewayManager;

    public StripeGateway(String apiKey, GatewayManager gatewayManager) {
        Stripe.apiKey = apiKey;
        this.gatewayManager = gatewayManager;
    }

    @Override
    public ChargeResponse createCharge(ChargeRequest request) {
        if (!request.getPlayer().isOnline() || request.getPlayer() == null) {
            return new ChargeResponse(ChargeFailStatus.PLAYER_OFFLINE);
        }


    }
}