package nl.multitime.multiPower.powers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FirePower implements Power {

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.RED;
    }

    @Override
    public String getDescription() {
        return "Beheers het element vuur. Schiet vuurballen, creëer vuur en word immuun voor vuurschade.";
    }

    @Override
    public void onRightClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            player.launchProjectile(Fireball.class);
            player.sendMessage(ChatColor.RED + "Je schiet een vuurbal!");
        }
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Location target = player.getTargetBlock(null, 10).getLocation().add(0, 1, 0);
            target.getBlock().setType(Material.FIRE);
            player.sendMessage(ChatColor.RED + "Je creëert vuur!");
        }
    }

    @Override
    public void onShift(Player player) {
        Location playerLoc = player.getLocation();
        for (int i = 0; i < 360; i += 30) {
            double angle = Math.toRadians(i);
            double x = Math.cos(angle) * 3;
            double z = Math.sin(angle) * 3;
            Location loc = playerLoc.clone().add(x, 0, z);
            loc.getBlock().setType(Material.FIRE);
        }
        player.sendMessage(ChatColor.RED + "Je creëert een cirkel van vuur!");
    }
}
