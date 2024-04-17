package post.servlet;

import cn.hutool.json.JSONObject;
import post.dao.PostDAO;
import post.entity.PostEntity;
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

@WebServlet("/DeletePost")
public class DeletePostS extends HttpServlet {

    private static final long serialVersionUID = 871483285L;
    private PostDAO postDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
        postDAO = new PostDAO(ds);
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
        if (session != null && idPost != null && !idPost.isBlank()){
            UtenteEntity user = (UtenteEntity) session.getAttribute("profile");

            if (!user.getRuolo().equals(UtenteEntity.Role.utente.toString())) {
                try {
                    PostEntity post = new PostEntity();
                    post.setId(Integer.parseInt(idPost));
                    postDAO.delete(post);
                    result = true;
                } catch (SQLException e) {
                    UtilityClass.print(e);
                }
            }

        }
        jsonObject.append("result", result);
        resp.getWriter().print(jsonObject);


    }
}
