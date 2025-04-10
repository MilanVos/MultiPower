package nl.multitime.multiPower.powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public interface Power {

    String getName();

    ChatColor getColor();

    String getDescription();

    void onRightClick(PlayerInteractEvent event, Player player);

    void onLeftClick(PlayerInteractEvent event, Player player);

    void onShift(Player player);
}
