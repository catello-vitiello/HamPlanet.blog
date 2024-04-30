package post.servlet;


import navigation.Navigator;
import navigation.Page;
import org.json.JSONObject;
import post.dao.PostDAO;
import post.entity.PostEntity;
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
        int id = Integer.parseInt(req.getParameter("pageId"));
        boolean new_page = Boolean.parseBoolean(req.getParameter("new_page"));



        if (postId != null && !postId.isEmpty()){

            try {
                PostEntity post = postDAO.getByID(Integer.parseInt(postId));
                jsonObject.put("id", post.getId());
                jsonObject.put("title", post.getNomePost());
                jsonObject.put("text", post.getTesto());
                jsonObject.put("writerId", post.getIdContent_Writer());


            } catch (SQLException e) {
                UtilityClass.print(e);
            }


        }

        //NAVIGATION
        HttpSession session = req.getSession(false);
        if (session != null) {
            Navigator navigator = (Navigator) session.getAttribute("Navigator");
            if (new_page)
                navigator.save();
            navigator.setCurrent(new Page(id, Page.Type.POST));
        }

        resp.getWriter().print(jsonObject);



    }
}
