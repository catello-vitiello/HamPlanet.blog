package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import navigation.Navigator;
import navigation.Page;
import org.json.JSONObject;
import profile.dao.ProfileDAO;


@WebServlet("/Login")
public class LoginS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDAO profileDAO;


	@Override
	public void init() throws ServletException {
		super.init();
		DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
		profileDAO = new ProfileDAO(ds);


	}

	// Setter per scopi di testing
	void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject json = new JSONObject();
		json.put("login", false);
		//DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		//ProfileDAO model = new ProfileDAO(ds);
		
		String email, password;
		
		email = request.getParameter("email");
		password = request.getParameter("password");
		
//		if((email == null || password == null) || (email.equalsIgnoreCase("") || password.equalsIgnoreCase("")) ) {
//
//			utils.UtilityClass.print("IF di controllo");
//
//
//			try {
//
//				HttpSession session = request.getSession(false);
//
//				if (profileDAO.login(email, password)) {
//					session.setAttribute("profile", profileDAO.getByEmail(email));
//				}
//
//
//				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("./WaitPage.html"); //area utente
//				requestDispatcher.forward(request, response);
//
//				return;
//
//			}catch (SQLException e) {
//				utils.UtilityClass.print(e);
//			}
//
//		}
		
		try {
			
			HttpSession session = request.getSession(true);
			Navigator navigator = new Navigator();
			session.setAttribute("Navigator", navigator);
			
			 if(profileDAO.login(email, password)) {
				 session.setAttribute("profile", profileDAO.getByEmail(email));
				 json.put("login", true);

			 }

			
		}catch (SQLException e) {
			utils.UtilityClass.print(e);
		}
		response.getWriter().print(json);
	}

}
