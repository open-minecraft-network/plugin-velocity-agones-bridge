package de.reptudn.agonesBridge.api;

import com.velocitypowered.api.proxy.ProxyServer;
import de.reptudn.agonesBridge.models.McServer;
import de.reptudn.agonesBridge.serverManager.ServerManager;
import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Api {

    private final int port;
    private Javalin app;
    private final ServerManager serverManager;

    public Api(int port, ServerManager serverManager) {
        this.port = port;
        this.serverManager = serverManager;

        app = Javalin.create(config -> {
            config.routes.get("/servers", ctx -> ctx.result("Hello World"));
            config.routes.post("/servers/register", this::registerServer);
            config.routes.post("/servers/unregister", this::unregisterServer);
        });

        app.start(port);
    }

    // /servers
    // /servers/register
    // /servers/unregister
    // /server/types
    // /server/types/{type}
    // /server/types/{type}/instances
    // /server/types/{type}/instances/{instance}

    private void registerServer(@NotNull io.javalin.http.Context ctx){

        String host;
        String name; // <type>-<sub-type>-<instance> - for example: gamemode-bedwars-1
        int port;

        McServer mcServer = ctx.bodyAsClass(McServer.class);
        this.serverManager.registerServer(mcServer);
    }

    private void unregisterServer(@NotNull io.javalin.http.Context ctx) {
        McServer mcServer = ctx.bodyAsClass(McServer.class);
        this.serverManager.unregisterServer(mcServer);
    }

    private void getAllServers(@NotNull io.javalin.http.Context ctx) {
        ctx.json(this.serverManager.getAllServers());
    }
}