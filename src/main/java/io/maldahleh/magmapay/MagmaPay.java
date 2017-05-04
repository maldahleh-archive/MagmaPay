package io.maldahleh.magmapay;

import io.maldahleh.magmapay.config.MPConfig;
import io.maldahleh.magmapay.gateways.GatewayManager;

import org.bukkit.plugin.java.JavaPlugin;

public class MagmaPay extends JavaPlugin {
    private static MagmaPay magmaPay;

    private MPConfig mpConfig;
    private GatewayManager gatewayManager;

    @Override
    public void onEnable() {
        getLogger().info("MagmaPay - Enabling...");
        magmaPay = this;

        gatewayManager = new GatewayManager(this);
        mpConfig = new MPConfig(this);
        getLogger().info("MagmaPay - Enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("MagmaPay - Disabled...");
        magmaPay = null;
        getLogger().info("MagmaPay - Disabled.");
    }

    public MPConfig getMpConfig() { return mpConfig; }

    public GatewayManager getGatewayManager() { return gatewayManager; }

    public static MagmaPay getInstance() { return magmaPay; }
}