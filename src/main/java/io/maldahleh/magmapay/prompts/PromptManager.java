package io.maldahleh.magmapay.prompts;

import io.maldahleh.magmapay.MagmaPay;
import io.maldahleh.magmapay.prompts.gateways.stripe.StripePromptManager;

public class PromptManager {
    private MagmaPay magmaPay;

    private StripePromptManager stripePromptManager;

    public PromptManager(MagmaPay magmaPay) {
        this.magmaPay = magmaPay;

        this.stripePromptManager = new StripePromptManager(magmaPay);
    }

    public StripePromptManager getStripePrompts() { return stripePromptManager; }
}