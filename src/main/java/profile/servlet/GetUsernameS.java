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

@WebServlet("/GetUsernameS")
public class GetUsernameS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProfileDAO model = new ProfileDAO(ds);
		
		try {
			request.setAttribute("rispostaUserName", model.checkUserName(request.getAttribute("userName").toString()));
			return;
		} catch (SQLException e) {
			utils.UtilityClass.print(e);
		}
		
	}

}
