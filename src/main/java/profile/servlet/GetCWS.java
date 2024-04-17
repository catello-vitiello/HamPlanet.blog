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

@WebServlet("/GetCWS")
public class GetCWS extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProfileDAO model = new ProfileDAO(ds);

		String id = null;
		UtenteEntity cw = new UtenteEntity();

		try {
			id = request.getParameter("id");
			if(id == null || id.equalsIgnoreCase("")) {
				utils.UtilityClass.print("##### Errore nel recuperare il ID del Ham_user");
				//mandare su una pagina di errore
				return;
			}

			cw = model.getByID(Integer.parseInt(id));
			utils.UtilityClass.print(cw.toString()); //da eliminare
		} catch (SQLException e) {
			utils.UtilityClass.print(e);
		}

	}

}
