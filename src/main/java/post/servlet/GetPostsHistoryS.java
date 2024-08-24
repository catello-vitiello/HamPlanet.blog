package post.servlet;

import org.json.JSONObject;
import post.dao.PostDAO;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class GetPostsHistoryS extends HttpServlet {

    private PostDAO postDAO;

    @Override
    public void init() throws ServletException {
        super.init();

        DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
        postDAO = new PostDAO(ds);

    }

    void setPostDAO(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();

        HttpSession session = req.getSession(false);
        if (session != null) {
            UtenteEntity u = (UtenteEntity) session.getAttribute("profile");

            if (u != null && u.getRuolo().equals(UtenteEntity.Role.content_writer.toString())) {
                try {
                    jsonObject.put("posts", postDAO.getAllByContentWriter(u.getId()));
                } catch (SQLException e) {
                    utils.UtilityClass.print(e);
                }
            }


        }
        resp.getWriter().print(jsonObject);


    }
}
