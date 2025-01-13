package post.servlet;

import org.json.JSONObject;
import post.dao.PostDAO;
import post.entity.PostEntity;
import profile.entity.UtenteEntity;
import utils.UtilityClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AddPost")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
        maxFileSize = 1024 * 1024 * 5,    // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class    AddPostS extends HttpServlet {

    private static final long serialVersionUID = 9898431841L;

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

        JSONObject json = new JSONObject();

        String title = req.getParameter("title");
        String plainText = req.getParameter("text");
        Part cover = req.getPart("cover");
        HttpSession session = req.getSession(false);
        UtenteEntity user = (UtenteEntity) session.getAttribute("profile");

        if (title != null && plainText != null && user != null) {
            if (!title.isEmpty() && !plainText.isEmpty() && user.getRuolo().equals(UtenteEntity.Role.content_writer.toString())) {
                PostEntity post = new PostEntity();
                post.setNomePost(title);
                post.setTesto(plainText);
                post.setIdContent_Writer(user.getId());

                try {
                    if(!postDAO.insert(post))
                        return;

                    int postId = postDAO.getByName(post.getNomePost());

                    if (cover.getSize() > 0){
                        String filename = "post/" + postId + ".jpeg";
                        req.setAttribute("Upload", true);
                        req.setAttribute("InputStream", cover.getInputStream());
                        req.setAttribute("Path", filename);
                        req.getRequestDispatcher("/FileManager").include(req, resp);


                    }
                    json.put("result", true);

                } catch (SQLException sqlException) {
                    UtilityClass.print(sqlException);
                }

            }
        }

        resp.getWriter().print(json);
    }
}
