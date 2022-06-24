package net.gigaclub.translation.commands;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.gigaclub.translation.Main;
import net.gigaclub.translation.Translation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class LanguageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId().toString();
        Translation t = Main.getTranslation();
        Gson gson = new Gson();
        JsonObject values;

        if (args.length > 0) {
            switch (args[0]) {
                case "set":
                    if (args.length > 1) {
                        JsonObject params = new JsonObject();
                        params.addProperty("language", args[1]);
                        values = new JsonObject();
                        values.add("params", params);
                        if (Main.getData().checkIfLanguageExists(args[1])) {
                            Main.getData().setLanguage(playerUUID, args[1]);
                            t.sendMessage("translation.command.language.set", player, values);
                        } else {
                            t.sendMessage("translation.command.language.does.not.exist", player, values);
                        }
                    } else {
                        t.sendMessage("translation.command.language.no.language.parameter", player);
                    }
                    break;
                case "list":
                    List<String> languages = Main.getData().getAvailableLanguages();
                    JsonObject languagesList = new JsonObject();
                    languagesList.add("languages", gson.toJsonTree(languages));
                    values = new JsonObject();
                    values.add("list", languagesList);
                    t.sendMessage("translation.command.language.list", player, values);
                    break;
                default:
                    JsonObject params = new JsonObject();
                    params.addProperty("language", args[0]);
                    values = new JsonObject();
                    values.add("params", params);
                    t.sendMessage("translation.command.language.incorrect.parameter", player, values);
                    break;
            }
        } else {
            t.sendMessage("translation.command.language.no.parameters", player);
        }
        return true;
    }
}
