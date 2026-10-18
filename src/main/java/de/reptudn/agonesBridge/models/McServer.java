package de.reptudn.agonesBridge.models;

import com.velocitypowered.api.proxy.server.ServerInfo;
import org.eclipse.jetty.server.Server;

import java.net.InetSocketAddress;

public class McServer {
    private final String host;
    private final int port;

    private final String name;

    private final ServerInfo serverInfo;

    public McServer(String host, int port, String name) {
        this.host = host;
        this.port = port;
        this.name = name;
        this.serverInfo = new ServerInfo(getFullName(), new InetSocketAddress(host, port));
    }

    public String getFullName()
    {
        return name;
    }

    public ServerInfo toServerInfo() {
        return this.serverInfo;
    }
}
