package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import profile.dao.ProfileDAO;

import profile.entity.UtenteEntity;
import utils.CifraPassword;

@WebServlet("/UpdateProfile")
public class UpdateProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDAO profileDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) config.getServletContext().getAttribute("dataSource");
		 profileDAO = new ProfileDAO(ds);
	}

	void setProfileDAO(ProfileDAO profileDAO){
		this.profileDAO = profileDAO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		UtenteEntity cw = new UtenteEntity();
		UtenteEntity originale = new UtenteEntity();
		int change = 0;
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		String comp = request.getParameter("comp");

		if(id == null || id.equalsIgnoreCase("")){
			utils.UtilityClass.print("###### Errore con l'id Content Writer!"); //da eliminare
			//mandare su una pagina di errore
			return;
		}

		try {
			originale = profileDAO.getByID(Integer.parseInt(id));

		} catch (SQLException e){
			utils.UtilityClass.print(e);
		}

		cw.setId(Integer.parseInt(id));
		if(userName != null && !(userName.equalsIgnoreCase(""))){
			cw.setUserName(userName);
			change++;
		} else
			cw.setUserName(originale.getUserName());

		if(email != null && !(email.equalsIgnoreCase(""))){
			cw.setEmail(email);
			change++;
		} else
			cw.setEmail(originale.getEmail());

		if(password != null && !(password.equalsIgnoreCase(""))){
			cw.setPasswd(CifraPassword.toHash(password));
			change++;
		} else
			cw.setPasswd(originale.getPasswd());

		if(comp != null && !(comp.equalsIgnoreCase(""))){
			cw.setCompetenze(comp);
			change++;
		} else
			cw.setCompetenze(originale.getCompetenze());

		if(change != 0){
			try{

				if(profileDAO.update(cw))
					utils.UtilityClass.print("###### Aggiornamento Ham_user effettuato!"); //da eliminare
				else
					utils.UtilityClass.print("###### Aggiornamento Ham_user fallito!"); //da eliminare
				
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/LoginS");
				
				request.setAttribute("email", cw.getEmail());
				request.setAttribute("password", cw.getPasswd());
				
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e){
				utils.UtilityClass.print(e);
			}
		}
	}

}
