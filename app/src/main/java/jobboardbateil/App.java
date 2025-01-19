package jobboardbateil;

import io.javalin.Javalin;
import jobboardbateil.HttpOpers.Add;
import jobboardbateil.HttpOpers.Job;
import jobboardbateil.HttpOpers.Login;
import jobboardbateil.HttpOpers.Offers;
import jobboardbateil.HttpOpers.Register;

public class App {
    public static void main(String[] args) {
        // Create a connection with the env file
        ReadEnv env = new ReadEnv();

        Login login = new Login();

        Offers offers = new Offers();

        Job job = new Job();

        Register register = new Register();

        Add add = new Add();

        // Mount of the web server, in the port 8070
        var app = Javalin.create(config -> {
            
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });
            // Config the route of the static files, this files are of type css and js
            config.staticFiles.add("/public");
            // Config the path host for show the selected html files
            config.spaRoot.addFile("/register-job", "public/create.html");
            config.spaRoot.addFile("/register", "public/sobreti.html");
            config.spaRoot.addFile("/login", "public/login.html");
            config.spaRoot.addFile("/", "public/index.html");
        }).start(env.getIntVar("WEB_SERVER_PORT"));
        /* Declarations of the endpoints with differents http methods
         * endpoint with get method
         */

        // API ROUTES
        // All the API route have the prefix api in their path

        // The path /api/auth provide the users auth using the api conection to check if credencials are correct
        app.post("/api/auth", ctx -> {
            String[] req = {ctx.formParam("email"), ctx.formParam("password")};
            String[] res = login.startLogin(req[0], req[1], ctx);
            if(Boolean.parseBoolean(res[0])){ // Redirect to the correct app, depending if the auth process is succesful or not
                // If the auth process is true, add te cookies in the nav, and redirect, to the / path
                //ctx.cookie("isAuth", res[0], -1);
                String[] sep = res[1].split(" ");
                //ctx.cookie("id", sep[1], -1);
                ctx.cookie("name", sep[0], -1);
                ctx.cookie("id", res[3], -1);
                //ctx.result(Boolean.toString(res));
                ctx.redirect("/");
            }else{
                // If the auth process is false, the app, send again to the page login 
                ctx.redirect("/login");
            }
        });

        // The path /api/offers, search and response the search in the db of all the jobs, separating all the jobs
        // in page of 10 jobs each page
        app.get("/api/offers", ctx -> {
            String res = "bad query";
            // The get method can get a param to indicate the page num in the job search
            String page = "1";
            // Check if the param is different of null
            if(!(ctx.queryParam("page") == null)){
                page = ctx.queryParam("page");
            }
            // Select if the search is the first search or is grater than first search
            if(!page.equals("1")){
                // Call the method, to search the selected page of jobs
                res = offers.chargeOffers(Integer.parseInt(ctx.queryParam("page")), 1);
            }else{
                // Call the method for charge of the first page of jobs offers
                res = offers.chargeFirstOffers();
            }
            // Return the result in a string
            ctx.json(res);
        });

        // The path /api/job, search an especific job offer info in the db
        app.get("/api/job", ctx -> {
            String res = "bad query";
            int id = 1;
            // In this path is required the param, because this param send what job want to search
            if(!(ctx.queryParam("jobid") == null)){
                // get the param and save this in a var
                id = Integer.parseInt(ctx.queryParam("jobid"));
                // Save the string of the json data
                res = job.chargeJob(id).toString();
            }else{ // if the param is null redirecto to the root page
                ctx.redirect("/"); // TO-DO: Redireccionar a la pagina principal de empleos
            }
            // Return the json string to the browser
            ctx.json(res);
        });

        // TODO: Only need to add other route to do filtered queries

        // The path /api/register, is used to register new users, getting the params needed to register to the user
        app.post("/api/register", ctx -> {
            // Params: name, email, password, speciality
            boolean status = register.reg(ctx.formParam("name"), ctx.formParam("email"), ctx.formParam("password"), ctx.formParam("speciality"));
            // Check the status of the register to redirect some path
            if(status){
                // If register is correct redirect to the home
                ctx.redirect("/");
            }else{
                // If register is failed redirect again to the register
                ctx.redirect("/register");
            }
        });

        // The path /api/add, is used to register new job offers, getting the params needed to register new job offers
        app.post("/api/add", ctx -> {
            // Params: name, location, journey, category, description, responsabilities, experience
            String[] data = {ctx.formParam("name"), ctx.formParam("location"), ctx.formParam("journey"), ctx.formParam("category"), ctx.formParam("description"), ctx.formParam("responsabilities"), ctx.formParam("experience")};
            // Add the offer to the db
            boolean status = add.addJob("joboffers", data);
            // check and redirect, if the register is sucessful
            if(status){
                ctx.redirect("/register-job");
            }
        });

        app.get("/api/check", ctx -> {
            int value = Integer.parseInt(ctx.cookie("id"));
            if(value == 1){
                ctx.redirect("/register-job");
            }else{
                ctx.redirect("/");
            }
        });
    }
}
