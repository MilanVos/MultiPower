package nl.multitime.multiPower.listeners;

import nl.multitime.multiPower.MultiPower;
import nl.multitime.multiPower.gui.PowerSelectionGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final MultiPower plugin;

    public PlayerJoinListener(MultiPower plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!plugin.getPowerManager().hasPower(player)) {
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                new PowerSelectionGUI(plugin).openMenu(player);
            }, 20L);
        }
    }
}
