package servlets;

import DataSets.UsersDataSet;
import com.google.gson.Gson;
import service.AccountService;
import service.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get logged user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        try {
            UsersDataSet profile = accountService.getCurUser(request.getParameter("login"));
            if (profile == null) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                Gson gson = new Gson();
                String json = gson.toJson(profile);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println(json);
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }catch (DBException ex){
            ex.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try{
            UsersDataSet profile = accountService.getCurUser(login);
            if (profile == null || !profile.getPassword().equals(pass)) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            //accountService.addSession(request.getSession().getId(), profile);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized: " + profile.getLogin());
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (DBException ex){
            ex.printStackTrace();
        }

    }

}
