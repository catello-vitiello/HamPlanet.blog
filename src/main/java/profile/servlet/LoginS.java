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
import profile.dao.ProfileDAO;


@WebServlet("/LoginS")
public class LoginS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDAO profileDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
		profileDAO = new ProfileDAO(ds);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		//ProfileDAO model = new ProfileDAO(ds);
		
		String email, password;
		
		email = request.getParameter("email");
		password = request.getParameter("pass");
		
		if((email == null || password == null) || (email.equalsIgnoreCase("") || password.equalsIgnoreCase("")) ) {
			
			utils.UtilityClass.print("IF di controllo");
			email = (String) request.getAttribute("email");
			password = (String) request.getAttribute("password");
			
			try {
				
				HttpSession session = request.getSession(false);

				if (profileDAO.login(email, password)) {
					session.setAttribute("profile", profileDAO.getByEmail(email));
				}
				//session.setAttribute("loggato", (boolean) profileDAO.login(email, password));

				//CifraPassword.checkPass(password, passwordNelDB);
				//session.setAttribute("cw", profileDAO.getByEmail(email));
				
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("./WaitPage.html"); //area utente
				requestDispatcher.forward(request, response);
				
				return;
				
			}catch (SQLException e) {
				utils.UtilityClass.print(e);
			}
			
		}
		
		try {
			
			HttpSession session = request.getSession(true);
			
			 if(profileDAO.login(email, password)) {
				 session.setAttribute("profile", profileDAO.getByEmail(email));

				 //NAVIGATOR
				 Navigator navigator = new Navigator();
				 navigator.setCurrent(new Page(0, Page.Type.HOME));
				 session.setAttribute("Navigator", navigator);


				 RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				 requestDispatcher.forward(request, response);
			 }
			
		}catch (SQLException e) {
			utils.UtilityClass.print(e);
		}
		
	}

}
