package net.gigaclub.translation.commands;

import net.gigaclub.translation.Main;
import net.gigaclub.translation.Translation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

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
                            ArrayList<String> parameters = new ArrayList<>();
                            parameters.add(args[1]);
                            sender.sendMessage(t.t("translation.command.language.set", parameters, playerUUID));
                        } else {
                            ArrayList<String> parameters = new ArrayList<>();
                            parameters.add(args[1]);
                            sender.sendMessage(t.t("translation.command.language.does.not.exist", parameters, playerUUID));
                        }
                    } else {
                        sender.sendMessage(t.t("translation.command.language.no.language.parameter", new ArrayList<>(), playerUUID));
                    }
                    break;
                case "list":
                    //Todo: improve after this issue is solved: https://github.com/gigaclub/TranslationAPI/issues/3
                    sender.sendMessage(t.t("translation.command.language.list", new ArrayList<>(), playerUUID));
                    for (HashMap<Object, Object> language : Main.getData().getAvailableLanguages()) {
                        sender.sendMessage((String) language.get("name"));
                    }
                    break;
                default:
                    ArrayList<String> parameters = new ArrayList<>();
                    parameters.add(args[0]);
                    sender.sendMessage(t.t("translation.command.language.incorrect.parameter", parameters, playerUUID));
                    break;
            }
        } else {
            sender.sendMessage(t.t("translation.command.language.no.parameters", new ArrayList<>(), playerUUID));
        }
        return true;
    }
}
