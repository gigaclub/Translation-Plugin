package net.gigaclub.translation.config;

import net.gigaclub.translation.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private static File configFile;
    private static FileConfiguration config;

    public static FileConfiguration getConfig() {
        return Config.config;
    }

    public static void createConfig() {
        Config.configFile = new File("plugins//" + Main.getPlugin().getDescription().getName(), "config.yml");
        Config.config = YamlConfiguration.loadConfiguration(configFile);
        Config.config.options().copyDefaults(true);
        Config.save();
    }

    public static void save() {
        try {
            Config.config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
