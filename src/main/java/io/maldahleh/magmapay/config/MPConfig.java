package io.maldahleh.magmapay.config;

import io.maldahleh.magmapay.MagmaPay;
import io.maldahleh.magmapay.gateways.AvailableGateway;
import io.maldahleh.magmapay.gateways.implementations.StripeGateway;

import org.bukkit.configuration.file.FileConfiguration;

public class MPConfig {
    private MagmaPay magmaPay;

    public MPConfig(MagmaPay magmaPay) {
        this.magmaPay = magmaPay;

        magmaPay.saveDefaultConfig();
        loadConfiguration();
    }

    private void loadConfiguration() {
        FileConfiguration config = magmaPay.getConfig();

        for (String key : config.getConfigurationSection("gateways").getKeys(false)) {
            if (key.equals("stripe") && config.getBoolean("gateways." + key + ".enabled")) {
                magmaPay.getGatewayManager().registerGateway(AvailableGateway.STRIPE,
                        new StripeGateway(config.getString("gateways." + key + ".access-data")));
            }
        }
    }
}