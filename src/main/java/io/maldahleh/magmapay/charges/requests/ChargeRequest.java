package io.maldahleh.magmapay.charges.requests;

import io.maldahleh.magmapay.gateways.Gateway;

import org.bukkit.entity.Player;

public class ChargeRequest {
    private Player player;
    private Gateway gateway;

    private double amountToCharge;
    private String isoCurrency;

    private boolean chargeImmediately;
    private String chargeDescription;
    private String statementDescriptor;

    private String providedPin;

    ChargeRequest(Player player, Gateway gateway, double amountToCharge, String isoCurrency) {
        this.player = player;
        this.gateway = gateway;
        this.amountToCharge = amountToCharge;
        this.isoCurrency = isoCurrency;

        this.chargeDescription = "NONE";
        this.statementDescriptor = "NONE";

        this.chargeImmediately = true;
        this.providedPin = null;
    }

    public Player getPlayer() { return player; }

    public double getAmountToCharge() { return amountToCharge; }

    public String getIsoCurrency() {
        return isoCurrency;
    }

    public boolean getChargeImmediately() {
        return chargeImmediately;
    }

    void setChargeImmediately(boolean chargeImmediately) { this.chargeImmediately = chargeImmediately; }

    public String getChargeDescription() {
        return chargeDescription;
    }

    void setChargeDescription(String chargeDescription) { this.chargeDescription = chargeDescription; }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    void setStatementDescriptor(String statementDescriptor) { this.statementDescriptor = statementDescriptor; }

    public String getProvidedPin() { return providedPin; }

    void setProvidedPin(String providedPin) { this.providedPin = providedPin; }
}