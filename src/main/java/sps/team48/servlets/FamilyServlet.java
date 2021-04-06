package sps.team48;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.ListValue;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.KeyQuery;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.DatastoreOptions;

@WebServlet("/families")
public class FamilyServlet extends HttpServlet {

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // Parse input + clean it
        System.out.println("Post /families");
        String familyNameValue = Jsoup.clean(request.getParameter("family_name"), Whitelist.none());

        System.out.println("Instantiate DataStore");
        // Instantiate DataStore Objects
        Datastore accountStore = DatastoreOptions.getDefaultInstance().getService();

        KeyFactory keyFactory = accountStore.newKeyFactory().setKind("Account");

        System.out.println("Query");
        Query<Entity> query = Query.newEntityQueryBuilder()
            .setKind("Family")
            .setFilter(CompositeFilter.and(
                PropertyFilter.eq("name", familyNameValue)))
            .build();

        QueryResults<Entity> family = accountStore.run(query);

        System.out.println(family);
            
        FullEntity contactEntity =
            Entity.newBuilder(keyFactory.newKey())
                .set("name", familyNameValue)
                .set("members", ListValue.of(null))
                .build();
        accountStore.put(contactEntity);
        //redirect 
        response.sendRedirect("welcome.html");
    }

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        System.out.println("Get /families");
        String familyNameValue = Jsoup.clean(request.getParameter("family_name"), Whitelist.none());

        System.out.println("Instantiate DataStore");
        // Instantiate DataStore Objects
        Datastore accountStore = DatastoreOptions.getDefaultInstance().getService();
        
        
        System.out.println("Query");
        Query<Key> query = Query.newKeyQueryBuilder()
            .setKind("Family")
            .setFilter(CompositeFilter.and(
                PropertyFilter.eq("name", familyNameValue)))
            .build();
        
        
        QueryResults<Key> familyKey = accountStore.run(query);

    }
}