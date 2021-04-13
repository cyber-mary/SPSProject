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
import java.util.LinkedList;
import java.util.List;

@WebServlet("/families")
public class FamilyServlet extends HttpServlet {

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        // Parse input + clean it
        System.out.println("Post /families");
        String familyNameValue = Jsoup.clean(request.getParameter("family_name"), Whitelist.none());

        // Instantiate DataStore Object
        Datastore db = DatastoreOptions.getDefaultInstance().getService();

        // Check if Family Exists
        Query<Entity> query = Query.newEntityQueryBuilder()
            .setKind("Family")
            .setFilter(CompositeFilter.and(
                PropertyFilter.eq("name", familyNameValue)))
            .build();
        QueryResults<Entity> family = db.run(query);

        if (family.hasNext()){
            response.setContentType("text/html;");
            response.getWriter().println("Error: That Family already exists!");
        } else {
            KeyFactory keyFactory = db.newKeyFactory().setKind("Family");
            FullEntity contactEntity =
                Entity.newBuilder(keyFactory.newKey())
                    .set("name", familyNameValue)
                    .set("members", new ArrayList<>())
                    .build();
            db.put(contactEntity);
            //redirect 
            response.sendRedirect("news.html");
        }
    }
}