package nl.multitime.multiPower.gui;

import nl.multitime.multiPower.MultiPower;
import nl.multitime.multiPower.powers.Power;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PowerSelectionGUI {

    private final MultiPower plugin;
    private final Inventory gui;

    public PowerSelectionGUI(MultiPower plugin) {
        this.plugin = plugin;
        this.gui = Bukkit.createInventory(null, 27, ChatColor.DARK_PURPLE + "Kies je Kracht");

        setupGUI();
    }

    private void setupGUI() {
        Map<String, Power> powers = plugin.getPowerManager().getAvailablePowers();

        if (powers.containsKey("fire")) {
            ItemStack fireItem = new ItemStack(Material.BLAZE_POWDER);
            ItemMeta fireMeta = fireItem.getItemMeta();
            fireMeta.setDisplayName(ChatColor.RED + "Vuur Kracht");
            List<String> fireLore = new ArrayList<>();
            fireLore.add(ChatColor.GRAY + powers.get("fire").getDescription());
            fireMeta.setLore(fireLore);
            fireItem.setItemMeta(fireMeta);
            gui.setItem(10, fireItem);
        }

        if (powers.containsKey("water")) {
            ItemStack waterItem = new ItemStack(Material.WATER_BUCKET);
            ItemMeta waterMeta = waterItem.getItemMeta();
            waterMeta.setDisplayName(ChatColor.BLUE + "Water Kracht");
            List<String> waterLore = new ArrayList<>();
            waterLore.add(ChatColor.GRAY + powers.get("water").getDescription());
            waterMeta.setLore(waterLore);
            waterItem.setItemMeta(waterMeta);
            gui.setItem(12, waterItem);
        }

        if (powers.containsKey("earth")) {
            ItemStack earthItem = new ItemStack(Material.GRASS_BLOCK);
            ItemMeta earthMeta = earthItem.getItemMeta();
            earthMeta.setDisplayName(ChatColor.DARK_GREEN + "Aarde Kracht");
            List<String> earthLore = new ArrayList<>();
            earthLore.add(ChatColor.GRAY + powers.get("earth").getDescription());
            earthMeta.setLore(earthLore);
            earthItem.setItemMeta(earthMeta);
            gui.setItem(14, earthItem);
        }

        if (powers.containsKey("air")) {
            ItemStack airItem = new ItemStack(Material.FEATHER);
            ItemMeta airMeta = airItem.getItemMeta();
            airMeta.setDisplayName(ChatColor.WHITE + "Lucht Kracht");
            List<String> airLore = new ArrayList<>();
            airLore.add(ChatColor.GRAY + powers.get("air").getDescription());
            airMeta.setLore(airLore);
            airItem.setItemMeta(airMeta);
            gui.setItem(16, airItem);
        }
    }

    public void openMenu(Player player) {
        player.openInventory(gui);
    }
}
