package io.maldahleh.magmapay.gateways.requests;

import org.bukkit.entity.Player;

public class ChargeRequestBuilder {
    private ChargeRequest chargeRequest;

    public ChargeRequestBuilder(Player player, double amountToCharge, String isoCurrency) {
        chargeRequest = new ChargeRequest(player, amountToCharge, isoCurrency);
    }

    public ChargeRequestBuilder setChargeImmediately(boolean chargeImmediately) {
        chargeRequest.setChargeImmediately(chargeImmediately);
        return this;
    }

    public ChargeRequestBuilder setChargeDescription(String chargeDescription) {
        chargeRequest.setChargeDescription(chargeDescription);
        return this;
    }

    public ChargeRequestBuilder setStatementDescriptor(String statementDescriptor) {
        chargeRequest.setStatementDescriptor(statementDescriptor);
        return this;
    }
}
