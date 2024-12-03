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
            // Config the route of the static files, this files are of type css and js
            config.staticFiles.add("/public");
            // Config the path host for show the selected html files
            config.spaRoot.addFile("/register", "public/sobreti.html");
            config.spaRoot.addFile("/", "public/index.html");
        }).start(env.getIntVar("WEB_SERVER_PORT"));

        /* Declarations of the endpoints with differents http methods
         * endpoint with get method
         */
        // This endpoint was modified to mount the selected html file, in this path
        app.get("/login", ctx -> {
            ctx.result("ingrese credenciales");
        });
        // API ROUTES
        // All the API route have the prefix api in their path

        // The path /api/auth provide the users auth using the api conection to check if credencials are correct
        app.post("/api/auth", ctx -> {
            String[] req = {ctx.formParam("email"), ctx.formParam("password")};
            boolean res = login.startLogin(req[0], req[1]);
            if(res){ // Redirect to the correct app, depending if the auth process is succesful or not
                // If the auth process is true, add te cookies in the nav, and redirect, to the / path
                ctx.cookie("isAuth", Boolean.toString(res), -1);
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
            System.out.println(ctx.queryParam("page"));
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
            // Show the selected jobs offers
            System.out.println(res);
            // Return the result in a string
            ctx.result(res);
        });

        // The path /api/job, search an especific job offer info in the db
        app.get("/api/job", ctx -> {
            String res = "bad query";
            int id = 1;
            // In this path is required the param, because this param send what job want to search
            System.out.println(ctx.queryParam("jobid"));
            if(!(ctx.queryParam("jobid") == null)){
                // get the param and save this in a var
                id = Integer.parseInt(ctx.queryParam("jobid"));
                // Save the string of the json data
                res = job.chargeJob(id).toString();
            }else{ // if the param is null redirecto to the root page
                ctx.redirect("/"); // TO-DO: Redireccionar a la pagina principal de empleos
            }
            // Return the json string to the browser
            ctx.result(res);
        });

        // TO-DO: Only need to add other route to do filtered queries
    }
}
