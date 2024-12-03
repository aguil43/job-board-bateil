package jobboardbateil;

import io.javalin.Javalin;
import jobboardbateil.HttpOpers.Job;
import jobboardbateil.HttpOpers.Login;
import jobboardbateil.HttpOpers.Offers;

public class App {
    public static void main(String[] args) {
        // Create a connection with the env file
        ReadEnv env = new ReadEnv();

        Login login = new Login();

        Offers offers = new Offers();

        Job job = new Job();

        // Mount of the web server, in the port 9000
        var app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.spaRoot.addFile("/register", "public/sobreti.html");
            config.spaRoot.addFile("/", "public/index.html");
        }).start(env.getIntVar("WEB_SERVER_PORT"));

        /* Declarations of the endpoints with differents http methods
         * endpoint with get method
         */
        // The root path, this show the home page 
        //app.get("/", ctx -> ctx.result("Hello world"));
        // The login path, this render the login page, to init sesion
        app.get("/login", ctx -> {
            ctx.result("ingrese credenciales");
        });
        
        // API ROUTES
        app.post("/api/auth", ctx -> {
            String[] req = {ctx.formParam("email"), ctx.formParam("password")};
            boolean res = login.startLogin(req[0], req[1]);
            if(res){
                ctx.cookie("isAuth", Boolean.toString(res), -1);
                ctx.redirect("/");
            }else{
                ctx.redirect("/login");
            }
        });
        app.get("/api/offers", ctx -> {
            String res = "bad query";
            System.out.println(ctx.queryParam("page"));
            String page = "1";
            if(!(ctx.queryParam("page") == null)){
                page = ctx.queryParam("page");
            }
            if(!page.equals("1")){
                res = offers.chargeOffers(Integer.parseInt(ctx.queryParam("page")), 1);
            }else{
                res = offers.chargeFirstOffers();
            }
            System.out.println(res);
            ctx.result(res);
        });

        app.get("/api/job", ctx -> {
            String res = "bad query";
            int id = 1;
            System.out.println(ctx.queryParam("jobid"));
            if(!(ctx.queryParam("jobid") == null)){
                id = Integer.parseInt(ctx.queryParam("jobid"));
                res = job.chargeJob(id).toString();
            }else{
                ctx.redirect("/"); // TO-DO: Redireccionar a la pagina principal de empleos
            }
            ctx.result(res);
        });
    }
}
