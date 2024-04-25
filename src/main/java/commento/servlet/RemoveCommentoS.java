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

@WebServlet("/RemoveCommentoS")
public class RemoveCommentoS extends HttpServlet {

    private static final long serialVersionUID = 897431L;

    private CommentoDAO commentoDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DataSource dataSource = (DataSource) super.getServletContext().getAttribute("DataSource");
        commentoDAO = new CommentoDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();

        String commentoStr = req.getParameter("commentoID");
        String postId = req.getParameter("postID");

        HttpSession session = req.getSession(false);

        UtenteEntity user = (UtenteEntity) session.getAttribute("profile");
        if (user.getRuolo().equals(UtenteEntity.Role.utente.toString()) && user.getRuolo().equals(UtenteEntity.Role.supervisore.toString())) {

            if (postId != null && commentoStr != null) {
                CommentoEntity commento = new CommentoEntity();
                commento.setIdPost(Integer.parseInt(commentoStr));

                try {
                    commentoDAO.delete(commento);

                    jsonObject.put("outcome", true);
                } catch (SQLException e) {
                    UtilityClass.print(e);
                }
            }

            resp.getWriter().print(jsonObject);

        }
    }


}
