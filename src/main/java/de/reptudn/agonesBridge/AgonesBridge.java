package de.reptudn.agonesBridge;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import de.reptudn.agonesBridge.api.Api;
import de.reptudn.agonesBridge.serverManager.ServerManager;
import org.slf4j.Logger;

public class AgonesBridge {

    @Inject
    private Logger logger;

    @Inject
    private ProxyServer proxy;

    private Api api;
    private ServerManager serverManager;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {

        logger.info("AgonesBridge is initializing...");

        // Initialize the ServerManager with the proxy instance
        serverManager = new ServerManager(proxy, logger, "");
        api = new Api(4242, serverManager);
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        logger.info("AgonesBridge is shutting down...");
        // Perform any necessary cleanup here
    }
}
