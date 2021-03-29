package sps.team48;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/account-handler")
public class AccountServlet extends HttpServlet {

  @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String firstNameValue = Jsoup.clean(request.getParameter("name"), Whitelist.none());
        String lastNameValue = Jsoup.clean(request.getParameter("last_name"), Whitelist.none());
        String emailValue = Jsoup.clean(request.getParameter("email"), Whitelist.none());
        String passwordValue = Jsoup.clean(request.getParameter("password"), Whitelist.none());
        
        Datastore accountStore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = accountStore.newKeyFactory().setKind("Account");
        FullEntity contactEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("first name", firstNameValue)
            .set("family", lastNameValue)
            .set("email", emailValue)
            .set("password", passwordValue)
            .build();
        accountStore.put(contactEntity);
        //redirect 
        //response.sendRedirect("");
  }
}