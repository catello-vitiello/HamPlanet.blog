package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;


@WebServlet("/DeleteProfileS")
public class DeleteProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject obj = new JSONObject();

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProfileDAO model = new ProfileDAO(ds);
		
        UtenteEntity cw = new UtenteEntity();
        
        String id = request.getParameter("email");
        if(id == null || id.equalsIgnoreCase("")){
            utils.UtilityClass.print("###### Errore con l'email Ham_user in fase di eliminazione!"); //da eliminare
            //mandare su una pagina di errore
            return;
        }
        
        try {

            cw.setEmail(id);

            UtenteEntity entry = model.getByEmail(id);

            if (model.delete(cw)){
                utils.UtilityClass.print("###### Eliminazione Ham_user effettuata!"); //da eliminare

                obj.put("username", entry.getUserName());
                obj.put("email", entry.getEmail());
                obj.put("ruolo", entry.getRuolo());
                obj.put("competenze", entry.getCompetenze()!=null ? entry.getCompetenze() : "null");
                obj.put("result", "Delete successfully done!");
                response.getWriter().print(obj);

        } else {
                utils.UtilityClass.print("###### Eliminazione Ham_user fallita!"); //da eliminare
                obj.put("email", id);
                obj.put("result", "Delete error!");
                response.getWriter().print(obj);

            }

        } catch (SQLException e){
            utils.UtilityClass.print(e);
        }
        		
	}

}