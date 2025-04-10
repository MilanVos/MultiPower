package nl.multitime.multiPower.powers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterPower implements Power {

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.BLUE;
    }

    @Override
    public String getDescription() {
        return "Beheers het element water. Creëer water, zwem sneller en adem onder water.";
    }

    @Override
    public void onRightClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block targetBlock = player.getTargetBlock(null, 10);
            if (targetBlock.getType() != Material.AIR) {
                Location waterLoc = targetBlock.getRelative(event.getBlockFace()).getLocation();
                waterLoc.getBlock().setType(Material.WATER);
                player.sendMessage(ChatColor.BLUE + "Je creëert water!");
            }
        }
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 200, 1));
            player.sendMessage(ChatColor.BLUE + "Je beweegt sneller in water!");
        }
    }

    @Override
    public void onShift(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1200, 0));
        player.sendMessage(ChatColor.BLUE + "Je kunt nu onder water ademen!");
    }
}
