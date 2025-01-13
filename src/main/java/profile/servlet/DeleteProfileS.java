package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.JSONObject;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;


@WebServlet("/DeleteProfile")
public class DeleteProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;


    private ProfileDAO profileDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DataSource ds = (DataSource) config.getServletContext().getAttribute("DataSource");
        profileDAO = new ProfileDAO(ds);
    }

    void setProfileDAO(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject json = new JSONObject();

        HttpSession session = request.getSession(false);
        if (session != null) {
            UtenteEntity user = (UtenteEntity) session.getAttribute("profile");


            if (user == null ) {
                utils.UtilityClass.print("###### Errore con l'email Ham_user in fase di eliminazione!"); //da eliminare
                //mandare su una pagina di errore
                return;
            }

            try {
                if (user.getRuolo().equals(UtenteEntity.Role.supervisore.toString())){
                    String id = request.getParameter("id");
                    if (id != null && !id.isEmpty()) {
                        UtenteEntity delete = profileDAO.getByID(Integer.parseInt(id));

                        boolean result = profileDAO.delete(delete);
                        json.put("success", result);


                    }
                }else {

                    if (profileDAO.delete(user)) {
                        session.invalidate();
                        json.put("success", true);
                        utils.UtilityClass.print("###### Eliminazione Ham_user effettuata!"); //da eliminare
                    }else {
                        json.put("success", false);
                        utils.UtilityClass.print("###### Eliminazione Ham_user fallita!"); //da eliminare
                    }
                }
            } catch (SQLException e) {
                utils.UtilityClass.print(e);
            }
        }
        response.getWriter().print(json);
	}

}