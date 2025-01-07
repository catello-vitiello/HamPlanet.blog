package post.servlet;



import post.dao.PostDAO;
import post.entity.PostEntity;
import utils.UtilityClass;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    void setDAO(PostDAO postDAO) {
        this.postDAO = postDAO;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String postId = req.getParameter("postId");



        if (postId != null && !postId.isEmpty()){

            try {
                PostEntity post = postDAO.getByID(Integer.parseInt(postId));
                req.setAttribute("post", post);
            } catch (SQLException e) {
                UtilityClass.print(e);
            }


        }


        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/post.jsp");
        requestDispatcher.forward(req, resp);


    }
}
