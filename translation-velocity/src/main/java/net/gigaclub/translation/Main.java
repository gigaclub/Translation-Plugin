package net.gigaclub.translation;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(
        id = "translation-velocity",
        name = "Translation",
        version = "@version@",
        description = "A Translation Plugin for GigaClub.net",
        url = "https://GigaClub.net",
        authors = {"KevTVKevin"}
)
public class Main {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        System.out.println("Test");
    }
}
