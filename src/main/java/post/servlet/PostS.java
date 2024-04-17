package post.servlet;

import cn.hutool.json.JSONObject;
import com.mysql.cj.xdevapi.JsonParser;
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

@WebServlet("/Post")
public class PostS extends HttpServlet {

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
        String postId = req.getParameter("postId");



        if (postId != null && !postId.isBlank()){

            try {
                PostEntity post = postDAO.getByID(Integer.parseInt(postId));
                jsonObject.set("id", post.getId());
                jsonObject.set("title", post.getNomePost());
                jsonObject.set("text", post.getTesto());
                jsonObject.set("writerId", post.getIdContent_Writer());


            } catch (SQLException e) {
                UtilityClass.print(e);
            }


        }

        resp.getWriter().print(jsonObject);


    }
}
