package net.gigaclub.translation.data;

import net.gigaclub.base.odoo.Odoo;

import java.util.*;

public class Data {
    private Odoo odoo;

    public Data(String hostname, String database, String username, String password) {
        this.odoo = new Odoo(hostname, database, username, password);
    }

    public boolean checkIfLanguageExists(String language) {
        return this.odoo.search_count(
                "gc.language",
                Arrays.asList(
                        Arrays.asList(
                                Arrays.asList("name", "=ilike", language)
                        )
                )
        ) > 0;
    }

    public List<HashMap<Object, Object>> getAvailableLanguages() {
        return this.odoo.search_read(
                "gc.language",
                new ArrayList<>(new ArrayList<>()),
                new HashMap() {{ put("fields", Collections.singletonList("name")); }}
        );
    }

    public void setLanguage(String playerUUID, String language) {

    }

}
