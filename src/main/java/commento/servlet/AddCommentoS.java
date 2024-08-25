package commento.servlet;

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

@WebServlet("/AddCommentoS")
public class AddCommentoS extends HttpServlet {

    private static final long serialVersionUID = 7845312L;

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

        String commentoStr = req.getParameter("commento");
        String postId = req.getParameter("postID");

        HttpSession session = req.getSession(false);

        UtenteEntity user = (UtenteEntity) session.getAttribute("profile");

        if (postId != null && commentoStr != null) {
            CommentoEntity commento = new CommentoEntity();
            commento.setIdUtente(user.getId());
            commento.setContenutoCommento(commentoStr);
            commento.setIdPost(Integer.parseInt(postId));

            try {
                jsonObject.put("outcome",
                        commentoDAO.insert(commento));
            } catch (SQLException e) {
                UtilityClass.print(e);
            }
        }

        resp.getWriter().print(jsonObject);

    }



}
