package io.maldahleh.magmapay.charges.response;

public class ChargeResponse {
    private boolean earlyFail;
    private ChargeFailStatus chargeFailStatus;

    /**
     * Used when failure occurs prior to attempted communication with gateway.
     *
     * @param chargeFailStatus status of the failed charge
     */
    public ChargeResponse(ChargeFailStatus chargeFailStatus) {
        this.earlyFail = true;
        this.chargeFailStatus = chargeFailStatus;
    }

    public boolean isEarlyFail() { return earlyFail; }

    public ChargeFailStatus getFailStatus() { return chargeFailStatus; }
}