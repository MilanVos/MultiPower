package nl.multitime.multiPower;

import nl.multitime.multiPower.commands.PowerCommand;
import nl.multitime.multiPower.listeners.GUIListener;
import nl.multitime.multiPower.listeners.PlayerJoinListener;
import nl.multitime.multiPower.listeners.PowerAbilityListener;
import nl.multitime.multiPower.managers.PowerManager;
import nl.multitime.multiPower.powers.FirePower;
import nl.multitime.multiPower.powers.WaterPower;
import nl.multitime.multiPower.powers.EarthPower;
import nl.multitime.multiPower.powers.AirPower;
import org.bukkit.plugin.java.JavaPlugin;

public final class MultiPower extends JavaPlugin {

    private PowerManager powerManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        powerManager = new PowerManager(this);

        registerPowers();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PowerAbilityListener(this), this);
        getServer().getPluginManager().registerEvents(new GUIListener(this), this);

        getCommand("power").setExecutor(new PowerCommand(this));

        getLogger().info("MultiPower is enabled!");
    }

    @Override
    public void onDisable() {
        powerManager.saveAllPlayerData();
        getLogger().info("MultiPower is disabled!");
    }

    private void registerPowers() {
        powerManager.registerPower(new FirePower());
        powerManager.registerPower(new WaterPower());
        powerManager.registerPower(new EarthPower());
        powerManager.registerPower(new AirPower());
    }

    public PowerManager getPowerManager() {
        return powerManager;
    }
}
