public class Api {

    private final String host;
    private final int port;
    private final ProxyServer proxy;
    private final Logger logger;

    public Api(String host, int port, ProxyServer proxy, Logger logger) {
        this.host = host;
        this.port = port;
        this.proxy = proxy;
        this.logger = logger;
    }

    private Javalin app;

    public void Serve() throws Exception {
        app = Javalin.create(config -> {
            config.showJavalinBanner = false;
            config.jsonMapper(new JavalinGson());
        });

        app.before(this::auth);
        app.get("/servers", ctx -> ctx.json(List.copyOf(dynamic.values())));
        app.post("/servers/register", this::registerServer);
        app.post("/servers/unregister", this::unregisterServer);

        app.start(host, port);
        logger.info("Api listening on {}:{}", host, port);
    }



    private void registerServer(){
        throw new UnimplementedOperationException("Register server is not implemented yet");
    }

    private void unregisterServer() {
        throw new UnimplementedOperationException("Unregister server is not implemented yet");
    }
}