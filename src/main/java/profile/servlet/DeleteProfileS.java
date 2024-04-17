package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;


@WebServlet("/DeleteProfileS")
public class DeleteProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProfileDAO model = new ProfileDAO(ds);
		
        UtenteEntity cw = new UtenteEntity();
        
        String id = request.getParameter("email");
        if(id == null || id.equalsIgnoreCase("")){
            utils.UtilityClass.print("###### Errore con l'email Ham_user in fase di eliminazione!"); //da eliminare
            //mandare su una pagina di errore
            return;
        }
        
        try{
        	
        	cw.setEmail(id);
            if(model.delete(cw))
                utils.UtilityClass.print("###### Eliminazione Ham_user effettuata!"); //da eliminare
            else
                utils.UtilityClass.print("###### Eliminazione Ham_user fallita!"); //da eliminare

        } catch (SQLException e){
            utils.UtilityClass.print(e);
        }
        		
	}





}

/*


@ -0,0 +1,73 @@
package profile.servlet;

import cn.hutool.json.JSONObject;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/DeleteProfile")
public class DeleteProfileS extends HttpServlet {

    private static final long serialVersionUID = 478164610L;


    private ProfileDAO profileDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
        profileDAO = new ProfileDAO(ds);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String deleteUser = req.getParameter("deleteUser");

        if (session != null){
            UtenteEntity user = (UtenteEntity) session.getAttribute("profile");
            try {
                if (user.getRuolo().equals(UtenteEntity.Role.supervisore.toString()) && deleteUser != null){
                    UtenteEntity utente = new UtenteEntity();
                    utente.setId(Integer.parseInt(deleteUser));
                    profileDAO.delete(user);

                    resp.setContentType("application/json");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.append("result", true);

                    resp.getWriter().print(jsonObject);
                }else {
                    profileDAO.delete(user);
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                utils.UtilityClass.print(e);
            }

        }else {
            resp.setContentType("application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.append("result", false);
            resp.getWriter().print(jsonObject);
        }

    }
}
 */
