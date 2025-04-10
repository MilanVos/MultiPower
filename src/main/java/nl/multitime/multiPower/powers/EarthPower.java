package nl.multitime.multiPower.powers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EarthPower implements Power {

    @Override
    public String getName() {
        return "Earth";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.DARK_GREEN;
    }

    @Override
    public String getDescription() {
        return "Beheers het element aarde. Gooi met stenen, creëer muren en word sterker.";
    }

    @Override
    public void onRightClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location loc = player.getEyeLocation();
            Vector direction = loc.getDirection();

            FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(
                    loc.add(direction),
                    Material.STONE.createBlockData());
            fallingBlock.setVelocity(direction.multiply(2));
            fallingBlock.setDropItem(false);

            player.sendMessage(ChatColor.DARK_GREEN + "Je gooit een steen!");
        }
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            Block aboveBlock = clickedBlock.getRelative(0, 1, 0);

            for (int y = 0; y < 3; y++) {
                for (int x = -1; x <= 1; x++) {
                    Block block = aboveBlock.getRelative(x, y, 0);
                    if (block.getType() == Material.AIR) {
                        block.setType(Material.STONE);
                    }
                }
            }

            player.sendMessage(ChatColor.DARK_GREEN + "Je creëert een stenen muur!");
        }
    }

    @Override
    public void onShift(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1));
        player.sendMessage(ChatColor.DARK_GREEN + "Je voelt je sterker worden!");
    }
}
