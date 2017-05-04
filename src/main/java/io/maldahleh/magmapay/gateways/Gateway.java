package io.maldahleh.magmapay.gateways;

import io.maldahleh.magmapay.charges.requests.ChargeRequest;
import io.maldahleh.magmapay.charges.response.ChargeResponse;
import io.maldahleh.magmapay.exceptions.GatewayMethodUnsupportedException;

public abstract class Gateway {

    public ChargeResponse createCharge(ChargeRequest request) throws GatewayMethodUnsupportedException {
        throw new GatewayMethodUnsupportedException();
    }
}