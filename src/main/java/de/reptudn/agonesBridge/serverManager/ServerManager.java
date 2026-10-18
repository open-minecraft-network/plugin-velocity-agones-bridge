package de.reptudn.agonesBridge.serverManager;

import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import de.reptudn.agonesBridge.models.McServer;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

public class ServerManager {

    // check how to do this best in here: https://claude.ai/chat/4daaba2e-fa8e-45eb-8ed4-c8e54b4ecba2

    private final ProxyServer proxy;
    private final Logger logger;
    private final String agonesConnectorUrl;

    public ServerManager(@NonNull ProxyServer proxy, @NonNull Logger logger, String agonesConnectorUrl) {
        this.proxy = proxy;
        this.logger = logger;
        this.agonesConnectorUrl = agonesConnectorUrl;

        // Start a scheduled task to sync servers with Agones every 5 minutes
        proxy.getScheduler().buildTask(this, this::syncProxyWithAgones).repeat(5, TimeUnit.MINUTES).schedule();
    }

    private void syncProxyWithAgones() {
        logger.info("Syncing servers with Agones...");
        // Implement logic to sync servers with Agones
        // This could involve fetching server data from Agones and updating the proxy's server list accordingly
    }

    public void registerServer(@NonNull McServer server) {
        logger.info("Registering server: {}", server.getFullName());
        proxy.registerServer(server.toServerInfo());
    }

    public void unregisterServer(@NonNull McServer server) {
        logger.info("Unregistering server: {}", server.getFullName());
        proxy.unregisterServer(server.toServerInfo());
    }

    public Collection<RegisteredServer> getAllServers() {
        return proxy.getAllServers();
        // Do something with the servers
    }

    public CompletableFuture<McServer> requestServer(@NonNull String type) {
        // Implement logic to request a server of the specified type
        return CompletableFuture.completedFuture(null);
    }
}
