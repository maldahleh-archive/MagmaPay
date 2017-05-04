package io.maldahleh.magmapay.exceptions;

public class GatewayMethodUnsupportedException extends Exception {
    public GatewayMethodUnsupportedException() { super("Gateway does not support method."); }
}