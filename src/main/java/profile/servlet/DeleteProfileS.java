package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import profile.dao.Ham_userDao;
import profile.entity.Ham_user;

@WebServlet("/DeleteProfileS")
public class DeleteProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        Ham_userDao model = new Ham_userDao(ds);
		
        Ham_user cw = new Ham_user();
        
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
