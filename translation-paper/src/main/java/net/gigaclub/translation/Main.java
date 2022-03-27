package net.gigaclub.translation;

import net.gigaclub.translation.commands.LanguageCommand;

import net.gigaclub.translation.data.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public final class Main extends JavaPlugin {

    private static Main plugin;
    private static Translation translation;
    private static Data data;
    final public static String PREFIX = "[GC]: ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        setPlugin(this);

        
        File file = new File("plugins//" + "Odoo", "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        setTranslation(new Translation(
            config.getString("Odoo.Host"),
            config.getString("Odoo.Database"),
            config.getString("Odoo.Username"),
            config.getString("Odoo.Password")
        ));
        translation.setCategory("translation");
        setData(new Data(
            config.getString("Odoo.Host"),
            config.getString("Odoo.Database"),
            config.getString("Odoo.Username"),
            config.getString("Odoo.Password")
        ));
        registerCommands();
        registerTranslations();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static void setPlugin(Main plugin) {
        Main.plugin = plugin;
    }

    public static Translation getTranslation() {
        return translation;
    }

    public static void setTranslation(Translation translation) {
        Main.translation = translation;
    }

    public static Data getData() {
        return data;
    }

    public static void setData(Data data) {
        Main.data = data;
    }

    public void registerCommands() {
        Objects.requireNonNull(getCommand("language")).setExecutor(new LanguageCommand());
    }

    public static void registerTranslations() {
        Main.translation.registerTranslations(Arrays.asList(
                "translation.command.language.set",
                "translation.command.language.does.not.exist",
                "translation.command.language.no.language.parameter",
                "translation.command.language.list",
                "translation.command.language.incorrect.parameter",
                "translation.command.language.no.parameters"
        ));
    }

}
