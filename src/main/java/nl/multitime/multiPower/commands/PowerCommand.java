package nl.multitime.multiPower.commands;

import nl.multitime.multiPower.MultiPower;
import nl.multitime.multiPower.gui.PowerSelectionGUI;
import nl.multitime.multiPower.powers.Power;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PowerCommand implements CommandExecutor {

    private final MultiPower plugin;

    public PowerCommand(MultiPower plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Dit commando kan alleen door spelers worden gebruikt!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            Power power = plugin.getPowerManager().getPlayerPower(player);

            if (power == null) {
                player.sendMessage(ChatColor.RED + "Je hebt nog geen kracht gekozen!");
                new PowerSelectionGUI(plugin).openMenu(player);
            } else {
                player.sendMessage(ChatColor.GOLD + "=== Jouw Kracht ===");
                player.sendMessage(power.getColor() + "Kracht: " + power.getName());
                player.sendMessage(ChatColor.GRAY + "Beschrijving: " + power.getDescription());
                player.sendMessage(ChatColor.GOLD + "==================");
            }

            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("menu")) {
                new PowerSelectionGUI(plugin).openMenu(player);
                return true;
            }

            if (args[0].equalsIgnoreCase("reset") && player.hasPermission("multipower.reset")) {
                plugin.getPowerManager().setPlayerPower(player, null);
                player.sendMessage(ChatColor.GREEN + "Je kracht is gereset. Gebruik /power menu om een nieuwe te kiezen.");
                return true;
            }
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("set") && player.hasPermission("multipower.admin")) {
            String powerName = args[1].toLowerCase();
            Power power = plugin.getPowerManager().getPower(powerName);

            if (power == null) {
                player.sendMessage(ChatColor.RED + "Ongeldige kracht: " + powerName);
                return true;
            }

            plugin.getPowerManager().setPlayerPower(player, powerName);
            player.sendMessage(ChatColor.GREEN + "Je kracht is ingesteld op: " + power.getColor() + power.getName());
            return true;
        }

        player.sendMessage(ChatColor.GOLD + "=== MultiPower Commando's ===");
        player.sendMessage(ChatColor.YELLOW + "/power - Toon je huidige kracht");
        player.sendMessage(ChatColor.YELLOW + "/power menu - Open het kracht selectie menu");

        if (player.hasPermission("multipower.reset")) {
            player.sendMessage(ChatColor.YELLOW + "/power reset - Reset je kracht");
        }

        if (player.hasPermission("multipower.admin")) {
            player.sendMessage(ChatColor.YELLOW + "/power set <kracht> - Stel direct een kracht in");
        }

        return true;
    }
}
