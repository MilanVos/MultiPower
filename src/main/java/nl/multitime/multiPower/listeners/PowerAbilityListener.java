package nl.multitime.multiPower.listeners;

import nl.multitime.multiPower.MultiPower;
import nl.multitime.multiPower.powers.Power;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PowerAbilityListener implements Listener {

    private final MultiPower plugin;

    public PowerAbilityListener(MultiPower plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Power power = plugin.getPowerManager().getPlayerPower(player);

        if (power != null) {
            switch (event.getAction()) {
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK:
                    power.onRightClick(event, player);
                    break;
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:
                    power.onLeftClick(event, player);
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Power power = plugin.getPowerManager().getPlayerPower(player);

        if (power != null && event.isSneaking()) {
            power.onShift(player);
        }
    }
}
