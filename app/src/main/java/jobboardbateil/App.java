package jobboardbateil;

import io.javalin.Javalin;
import jobboardbateil.DbQueries.DbQuery;

public class App {
    public static void main(String[] args) {
        // Create a connection with the env file
        ReadEnv env = new ReadEnv();

        DbQuery query = new DbQuery();

        // Mount of the web server, in the port 9000
        var app = Javalin.create().start(env.getIntVar("WEB_SERVER_PORT"));

        /* Declarations of the endpoints with differents http methods
         * endpoint with get method
         */
        // The root path, this show the home page 
        app.get("/", ctx -> ctx.result("Hello world"));
        // The login path, this render the login page, to init sesion
        app.get("/login", ctx -> {
            ctx.result(query.get_datum_str("admin", "userName", "users", 1));
        });
    }
}
