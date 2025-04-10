package nl.multitime.multiPower.managers;

import nl.multitime.multiPower.MultiPower;
import nl.multitime.multiPower.powers.Power;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PowerManager {

    private final MultiPower plugin;
    private final Map<String, Power> availablePowers = new HashMap<>();
    private final Map<UUID, String> playerPowers = new HashMap<>();
    private final File playerDataFile;
    private final FileConfiguration playerData;

    public PowerManager(MultiPower plugin) {
        this.plugin = plugin;
        this.playerDataFile = new File(plugin.getDataFolder(), "playerdata.yml");

        if (!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create playerdata.yml!");
                e.printStackTrace();
            }
        }

        this.playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        loadPlayerData();
    }

    public void registerPower(Power power) {
        availablePowers.put(power.getName().toLowerCase(), power);
    }

    public Power getPower(String name) {
        return availablePowers.get(name.toLowerCase());
    }

    public Map<String, Power> getAvailablePowers() {
        return availablePowers;
    }

    public boolean hasPower(Player player) {
        return playerPowers.containsKey(player.getUniqueId());
    }

    public Power getPlayerPower(Player player) {
        String powerName = playerPowers.get(player.getUniqueId());
        if (powerName == null) return null;
        return availablePowers.get(powerName.toLowerCase());
    }

    public void setPlayerPower(Player player, String powerName) {
        if (powerName == null) {
            playerPowers.remove(player.getUniqueId());
            savePlayerData();
            return;
        }

        if (availablePowers.containsKey(powerName.toLowerCase())) {
            playerPowers.put(player.getUniqueId(), powerName.toLowerCase());
            savePlayerData();
        }
    }

    private void loadPlayerData() {
        if (playerData.contains("players")) {
            for (String uuidString : playerData.getConfigurationSection("players").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidString);
                String powerName = playerData.getString("players." + uuidString);
                playerPowers.put(uuid, powerName);
            }
        }
    }

    public void savePlayerData() {
        playerData.set("players", null);

        for (Map.Entry<UUID, String> entry : playerPowers.entrySet()) {
            playerData.set("players." + entry.getKey().toString(), entry.getValue());
        }

        try {
            playerData.save(playerDataFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save playerdata.yml!");
            e.printStackTrace();
        }
    }

    public void saveAllPlayerData() {
        savePlayerData();
    }
}
