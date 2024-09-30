package profile.servlet;

import org.json.JSONObject;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/GetCommentHistoryS")
public class GetCommentHistoryS extends HttpServlet {

    private ProfileDAO profileDAO;

    @Override
    public void init() throws ServletException{
        super.init();

        DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
        profileDAO = new ProfileDAO(ds);

    }

    void setProfileDAO(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();

        String username = req.getParameter("username");
        UtenteEntity u = new UtenteEntity();
        u.setUserName(username);

        try{
            jsonObject.put("commenti", profileDAO.getHistoryComments(u));
        } catch (SQLException e){
            utils.UtilityClass.print(e);
        }

        resp.getWriter().print(jsonObject);

    }

}
