package net.gigaclub.translation.commands;

import net.gigaclub.translation.Main;
import net.gigaclub.translation.Translation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        String playerUUID = player.getUniqueId().toString();
        Translation t = Main.getTranslation();

        if (args.length > 0) {
            switch (args[0]) {
                case "set":
                    if (args.length > 1) {
                        if (Main.getData().checkIfLanguageExists(args[1])) {
                            Main.getData().setLanguage(playerUUID, args[1]);
                            t.sendMessage("translation.command.language.set", player, Arrays.asList(args[1]));
                        } else {
                            t.sendMessage("translation.command.language.does.not.exist", player, Arrays.asList(args[1]));
                        }
                    } else {
                        t.sendMessage("translation.command.language.no.language.parameter", player);
                    }
                    break;
                case "list":
                    //Todo: improve after this issue is solved: https://github.com/gigaclub/TranslationAPI/issues/3
                    t.sendMessage("translation.command.language.list", player);
                    List<String> languages = Main.getData().getAvailableLanguages();
                    for (String language : languages) {
                        sender.sendMessage(language);
                    }
                    break;
                default:
                    t.sendMessage("translation.command.language.incorrect.parameter", player, Arrays.asList(args[0]));
                    break;
            }
        } else {
            t.sendMessage("translation.command.language.no.parameters", player);
        }
        return true;
    }
}
