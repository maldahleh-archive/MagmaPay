package io.maldahleh.magmapay.gateways.implementations;

import com.stripe.Stripe;

import io.maldahleh.magmapay.gateways.Gateway;

public class StripeGateway extends Gateway {

    public StripeGateway(String apiKey) {
        Stripe.apiKey = apiKey;
    }

}