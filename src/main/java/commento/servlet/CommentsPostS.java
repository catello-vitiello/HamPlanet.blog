package commento.servlet;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;
import commento.dao.CommentoDAO;
import commento.entity.CommentoEntity;
import org.json.JSONObject;
import profile.entity.UtenteEntity;
import utils.UtilityClass;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/CommentsPostS")
public class CommentsPostS extends HttpServlet {

    private static final long serialVersionUID = 80841482L;

    private CommentoDAO commentoDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DataSource dataSource = (DataSource) super.getServletContext().getAttribute("DataSource");
        commentoDAO = new CommentoDAO(dataSource);
    }

    void setCommentoDAO(CommentoDAO commentoDAO) {
        this.commentoDAO = commentoDAO;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();

        String postId = req.getParameter("postID");



        if (postId != null ) {

            try {
                Collection<CommentoEntity> collection =  commentoDAO.getAllByPost(Integer.parseInt(postId));
                jsonObject.put("commenti", collection);

            } catch (SQLException e) {
                UtilityClass.print(e);
            }
        }

        resp.getWriter().print(jsonObject);

    }


}
