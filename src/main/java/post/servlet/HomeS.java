package post.servlet;


import org.json.JSONObject;
import post.dao.PostDAO;
import post.entity.PostEntity;
import utils.UtilityClass;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

@WebServlet("/Home")
public class HomeS extends HttpServlet {

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
        //resp.setContentType("application/json");

        Collection<PostEntity> posts;


        try{
            //Collection<JSONObject> collection = new LinkedList<>();
            posts = postDAO.getAll(null);

//            for(PostEntity p : posts){
//
//                JSONObject obj = new JSONObject();
//                obj.put("id", p.getId());
//                obj.put("id-cw", p.getIdContent_Writer());
//                obj.put("name", p.getNomePost());
//                obj.put("body", p.getTesto());
//
//                collection.add(obj);
//
//            }
//
//            collection.forEach(
//                    x -> {
//                        UtilityClass.print(x.toString());
//                    }
//            );


            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
            req.setAttribute("posts", posts);
            requestDispatcher.forward(req, resp);

        } catch (SQLException e){
            UtilityClass.print(e);
            resp.getWriter().print("{\"error\": \"An error occurred while retrieving posts\"}");
        }


    }
}
