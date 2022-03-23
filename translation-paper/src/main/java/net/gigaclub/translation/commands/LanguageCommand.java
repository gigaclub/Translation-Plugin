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

import java.util.Arrays;

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
                            sender.sendMessage(t.t("translation.command.language.set", playerUUID, Arrays.asList(args[1])));
                        } else {
                            sender.sendMessage(t.t("translation.command.language.does.not.exist", playerUUID, Arrays.asList(args[1])));
                        }
                    } else {
                        sender.sendMessage(t.t("translation.command.language.no.language.parameter", playerUUID));
                    }
                    break;
                case "list":
                    //Todo: improve after this issue is solved: https://github.com/gigaclub/TranslationAPI/issues/3
                    sender.sendMessage(t.t("translation.command.language.list", playerUUID));
                    JSONArray languages = Main.getData().getAvailableLanguages();
                    for (int i = 0; i < languages.length(); i++) {
                        JSONObject language = languages.getJSONObject(i);
                        sender.sendMessage(language.getString("name"));
                    }
                    break;
                default:
                    sender.sendMessage(t.t("translation.command.language.incorrect.parameter", playerUUID, Arrays.asList(args[0])));
                    break;
            }
        } else {
            sender.sendMessage(t.t("translation.command.language.no.parameters", playerUUID));
        }
        return true;
    }
}
