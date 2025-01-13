package post.servlet;

import org.json.JSONObject;
import post.dao.PostDAO;
import profile.entity.UtenteEntity;
import utils.UtilityClass;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Unlike")
public class UnlikePosts extends HttpServlet {

    private static final long serialVersionUID = 12348301L;
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
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();
        boolean result = false;

        String idPost = req.getParameter("postId");
        HttpSession session = req.getSession(false);
        if (session != null && idPost != null && !idPost.isEmpty()){
            UtenteEntity user = (UtenteEntity) session.getAttribute("profile");

            if (user.getRuolo().equals(UtenteEntity.Role.utente.toString())) {
                try {
                    result = postDAO.unlike(Integer.parseInt(idPost), user.getId());

                } catch (SQLException e) {
                    UtilityClass.print(e);
                }
            }

        }
        jsonObject.append("result", result);
        resp.getWriter().print(jsonObject);


    }


}
