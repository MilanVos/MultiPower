package nl.multitime.multiPower.listeners;

import nl.multitime.multiPower.MultiPower;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    private final MultiPower plugin;

    public GUIListener(MultiPower plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.DARK_PURPLE + "Kies je Kracht")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null) return;

            Player player = (Player) event.getWhoClicked();

            switch (event.getSlot()) {
                case 10:
                    plugin.getPowerManager().setPlayerPower(player, "fire");
                    player.sendMessage(ChatColor.RED + "Je hebt de Vuur Kracht gekozen!");
                    break;
                case 12:
                    plugin.getPowerManager().setPlayerPower(player, "water");
                    player.sendMessage(ChatColor.BLUE + "Je hebt de Water Kracht gekozen!");
                    break;
                case 14:
                    plugin.getPowerManager().setPlayerPower(player, "earth");
                    player.sendMessage(ChatColor.DARK_GREEN + "Je hebt de Aarde Kracht gekozen!");
                    break;
                case 16:
                    plugin.getPowerManager().setPlayerPower(player, "air");
                    player.sendMessage(ChatColor.WHITE + "Je hebt de Lucht Kracht gekozen!");
                    break;
            }

            player.closeInventory();
        }
    }
}
