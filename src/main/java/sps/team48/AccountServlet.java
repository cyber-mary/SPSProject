package sps.team48;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreException;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.EntityQuery;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.ListValue;
import com.google.cloud.datastore.PathElement;
import com.google.cloud.datastore.ProjectionEntity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.ReadOption;
import com.google.cloud.datastore.StringValue;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.Transaction;
import com.google.cloud.datastore.testing.LocalDatastoreHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.datastore.v1.TransactionOptions;
import com.google.datastore.v1.TransactionOptions.ReadOnly;
import com.google.cloud.datastore.DatastoreOptions;

@WebServlet("/account-handler")
public class AccountServlet extends HttpServlet {

  @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        //get input + clean it
        String firstNameValue = Jsoup.clean(request.getParameter("name"), Whitelist.none());
        String lastNameValue = Jsoup.clean(request.getParameter("last_name"), Whitelist.none());
        String emailValue = Jsoup.clean(request.getParameter("email"), Whitelist.none());
        String passwordValue = Jsoup.clean(request.getParameter("password"), Whitelist.none());
       
        Datastore accountStore = DatastoreOptions.getDefaultInstance().getService();
 
        KeyFactory keyFactory = accountStore.newKeyFactory().setKind("Account");

        Query<Entity> query = Query.newEntityQueryBuilder()
            .setKind("Account")
            .setFilter(CompositeFilter.and(
                PropertyFilter.eq("email", emailValue)))
            .build();

        QueryResults<Entity> account = accountStore.run(query);
    
        if(account.equals(null)){
            response.setContentType("text/html;");
            response.getWriter().println("Error: Already account with that email. Use a different email or reset your password");
         } else{
            //create entity and store it 
            FullEntity contactEntity =
            Entity.newBuilder(keyFactory.newKey())
                .set("first name", firstNameValue)
                .set("family", lastNameValue)
                .set("email", emailValue)
                .set("password", passwordValue)
                .build();
            accountStore.put(contactEntity);
            //redirect 
            response.sendRedirect("welcome.html");
        }

  }
}