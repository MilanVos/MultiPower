package nl.multitime.multiPower.powers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class AirPower implements Power {

    @Override
    public String getName() {
        return "Air";
    }

    @Override
    public ChatColor getColor() {
        return ChatColor.WHITE;
    }

    @Override
    public String getDescription() {
        return "Beheers het element lucht. Vlieg, stoot vijanden weg en val langzamer.";
    }

    @Override
    public void onRightClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            player.getNearbyEntities(5, 5, 5).forEach(entity -> {
                if (entity instanceof Player) return;

                Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector());
                direction.normalize().multiply(2);
                direction.setY(0.5);

                entity.setVelocity(direction);
            });

            player.sendMessage(ChatColor.WHITE + "Je stoot vijanden weg met een windvlaag!");
        }
    }

    @Override
    public void onLeftClick(PlayerInteractEvent event, Player player) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Vector direction = player.getLocation().getDirection();
            direction.multiply(1.5).setY(0.8);

            player.setVelocity(direction);
            player.sendMessage(ChatColor.WHITE + "Je schiet vooruit met de wind!");
        }
    }

    @Override
    public void onShift(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 200, 0));

        player.getServer().getScheduler().runTaskLater(
                player.getServer().getPluginManager().getPlugin("MultiPower"),
                () -> {
                    player.setFlying(false);
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.WHITE + "Je kunt niet meer vliegen.");
                },
                200L);

        player.sendMessage(ChatColor.WHITE + "Je kunt nu 10 seconden vliegen!");
    }
}
