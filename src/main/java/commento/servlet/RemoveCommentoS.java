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

@WebServlet("/DeleteComment")
public class RemoveCommentoS extends HttpServlet {

    private static final long serialVersionUID = 897431L;

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

        String commentoId = req.getParameter("commentoID");
        String postId = req.getParameter("postID");

        HttpSession session = req.getSession(false);

        UtenteEntity user = (UtenteEntity) session.getAttribute("profile");
        if (postId != null && commentoId != null) {
            if (user.getRuolo().equals(UtenteEntity.Role.utente.toString())) {

                try {
                        int commId = Integer.parseInt(commentoId);
                        int user_id = user.getId();
                    if (commentoDAO.isOwnComment(commId, user_id)) {

                        CommentoEntity commento = new CommentoEntity();
                        commento.setId(commId);


                        jsonObject.put("outcome",
                                commentoDAO.delete(commento));
                    }
                } catch (SQLException e) {
                    UtilityClass.print(e);
                }


            } else if (user.getRuolo().equals(UtenteEntity.Role.supervisore.toString())) {
                try {
                    CommentoEntity commento = new CommentoEntity();
                    commento.setId(Integer.parseInt(commentoId));

                    jsonObject.put("outcome",
                            commentoDAO.delete(commento));

                } catch (SQLException e) {
                UtilityClass.print(e);
            }
            }
        }

        resp.getWriter().print(jsonObject);
    }


}
