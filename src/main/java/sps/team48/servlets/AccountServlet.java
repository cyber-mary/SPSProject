package sps.team48.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.PathElement;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.ListValue;

@WebServlet("/account-handler")
public class AccountServlet extends HttpServlet {
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // Parse input + clean it
        System.out.println("Entered Servlet");
        String emailValue = Jsoup.clean(request.getParameter("email"), Whitelist.none());
        String firstNameValue = Jsoup.clean(request.getParameter("first_name"), Whitelist.none());
        String lastNameValue = Jsoup.clean(request.getParameter("last_name"), Whitelist.none());
        String ageValue = Jsoup.clean(request.getParameter("age"), Whitelist.none());
        boolean caughtCovidValue = Boolean.parseBoolean(Jsoup.clean(request.getParameter("caught_covid"), Whitelist.none()));
        String vaccinationStatusValue = Jsoup.clean(request.getParameter("vaccination_status"), Whitelist.none());
        String passwordValue = Jsoup.clean(request.getParameter("password"), Whitelist.none());

        System.out.println("Instantiate DataStore");
        // Instantiate DataStore Objects
        Datastore db = DatastoreOptions.getDefaultInstance().getService();

        KeyFactory keyFactory = db.newKeyFactory().setKind("Account");

        System.out.println("Query");
        Query<Entity> query = Query.newEntityQueryBuilder()
            .setKind("Account")
            .setFilter(CompositeFilter.and(
                PropertyFilter.eq("email", emailValue)))
            .build();

        QueryResults<Entity> account = db.run(query);

        if(account.hasNext()){
            response.setContentType("text/html;");
            response.getWriter().println("Error: Already account with that email. Use a different email or reset your password\n");
        } else {
            FullEntity contactEntity =
            Entity.newBuilder(keyFactory.newKey())
                .set("first name", firstNameValue)
                .set("last name", lastNameValue)
                .set("email", emailValue)
                .set("password", passwordValue)
                .set("age", ageValue)
                .set("caught covid", caughtCovidValue)
                .set("vaccination status", vaccinationStatusValue)
                .set("families", new ArrayList<>())
                .build();
            db.put(contactEntity);
            //redirect 
            response.sendRedirect("register-family.html");
        }
    }
}