package net.gigaclub.translation.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.gigaclub.base.odoo.Odoo;
import org.apache.xmlrpc.XmlRpcException;
import org.json.JSONArray;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Data {
    private Odoo odoo;

    public Data(String hostname, String database, String username, String password) {
        this.odoo = new Odoo(hostname, database, username, password);
    }

    public boolean checkIfLanguageExists(String languageCode) {
        return this.odoo.search_count(
                "res.lang",
                Arrays.asList(
                        Arrays.asList(
                                Arrays.asList("code", "=", languageCode)
                        )
                )
        ) > 0;
    }

    public List<String> getAvailableLanguages() {
        try {
            Gson gson = new Gson();
            JsonElement installedLanguages = gson.toJsonTree((this.odoo.getModels().execute("execute_kw", Arrays.asList(
                    this.odoo.getDatabase(), this.odoo.getUid(), this.odoo.getPassword(),
                    "res.lang", "get_installed", Arrays.asList()
                )
            )));
            List<String> languages = new ArrayList<>();
            installedLanguages.getAsJsonArray().forEach(language -> {
                JsonArray languageTuple = language.getAsJsonArray();
                languages.add(languageTuple.get(0).getAsString());
            });
            return languages;
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void setLanguage(String playerUUID, String languageCode) {
        try {
            this.odoo.getModels().execute("execute_kw", Arrays.asList(
                    this.odoo.getDatabase(), this.odoo.getUid(), this.odoo.getPassword(),
                    "gc.user", "set_language", Arrays.asList(playerUUID, languageCode)
            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }

}
