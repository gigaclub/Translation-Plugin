package net.gigaclub.translation.data;

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

    public List getAvailableLanguages() {
        try {
            JSONArray o = new JSONArray(this.odoo.getModels().execute("execute_kw", Arrays.asList(
                    this.odoo.getDatabase(), this.odoo.getUid(), this.odoo.getPassword(),
                    "res.lang", "get_langs", Arrays.asList()
                )
            ));
            return Stream.of(o).map(o1 -> o1.get(0)).collect(Collectors.toList());
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void setLanguage(String playerUUID, String language) {
        try {
            this.odoo.getModels().execute("execute_kw", Arrays.asList(
                    this.odoo.getDatabase(), this.odoo.getUid(), this.odoo.getPassword(),
                    "gc.user", "set_language", Arrays.asList(playerUUID, language)
            ));
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }

}
