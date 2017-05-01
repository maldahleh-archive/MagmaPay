package io.maldahleh.magmapay;

import org.bukkit.plugin.java.JavaPlugin;

public class MagmaPay extends JavaPlugin {
    private static MagmaPay magmaPay;

    @Override
    public void onEnable() {
        getLogger().info("MagmaPay - Enabling...");
        magmaPay = this;
        getLogger().info("MagmaPay - Enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("MagmaPay - Disabled...");
        magmaPay = null;
        getLogger().info("MagmaPay - Disabled.");
    }

    public static MagmaPay getInstance() { return magmaPay; }
}
