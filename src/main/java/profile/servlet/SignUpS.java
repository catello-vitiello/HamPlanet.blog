package profile.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.json.JSONObject;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

@WebServlet("/SignUp")
public class SignUpS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDAO profileDAO;
	private DataSource ds;

	@Override
	public void init() throws ServletException {
		super.init();
		ds = (DataSource) super.getServletContext().getAttribute("DataSource");
		profileDAO = new ProfileDAO(ds);

	}

	void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		JSONObject obj = new JSONObject();

		UtenteEntity user = new UtenteEntity();

		String username, email, competenze;
		String passwd = null;

		Part cover = request.getPart("cover");
		username = request.getParameter("userName");
		email = request.getParameter("email");
		String pass_to_hash = request.getParameter("passwd");
		if (pass_to_hash != null)
			passwd = utils.CifraPassword.toHash(pass_to_hash);

		competenze = request.getParameter("comp");

		if (username == null || email == null || passwd == null )
			response.getWriter().print(obj.put("errore", "credenziali non inserite"));

		user.setUserName(username);
		user.setEmail(email);
		user.setPasswd(passwd);

		/*
		Slot di codice per la gestione della registrazione dell'utente*
		Controllo se il campo competenze è nullo o vuoto, in questo caso vuol dire che l'user
		che vuole registrarsi è un utente
		 */
		if (competenze.equalsIgnoreCase("") || competenze == null) {

			try {

				//check sull'email
				if (profileDAO.checkEmail(email)) {

					obj.put("username", username);
					obj.put("email", email);
					obj.put("ruolo", UtenteEntity.Role.utente);
					obj.put("errore", "Email gia in uso");

					response.getWriter().print(obj);

					return;
				}

				//check sull'userName
				if (profileDAO.checkUserName(username)) {

					obj.put("errore", "Username gia in uso");
					response.getWriter().print(obj);

					return;
				}

				user.setRuolo(UtenteEntity.Role.utente);

				profileDAO.insert(user);



			} catch (SQLException e) {
				utils.UtilityClass.print(e);
			}

		} else { //Inserimento Content Writer

			try {

				//check sull'email
				if (profileDAO.checkEmail(email)) {

					obj.put("errore", "Email gia in uso");

					response.getWriter().print(obj);

					return;
				}

				//check sull'userName
				if (profileDAO.checkUserName(username)) {

					obj.put("errore", "Username gia in uso");

					response.getWriter().print(obj);
					return;
				}

				user.setCompetenze(competenze);
				user.setRuolo(UtenteEntity.Role.content_writer);

				if (profileDAO.insert(user)) {
					obj.put("username", username);
					obj.put("email", email);
					obj.put("competenze", competenze);
					obj.put("ruolo", UtenteEntity.Role.content_writer);

					String filename = "profile/" + user.getId() + ".jpg";

					request.setAttribute("Upload", true);
					request.setAttribute("InputStream", cover.getInputStream());
					request.setAttribute("Path", filename);
					request.getRequestDispatcher("FileManager").include(request, response);


				}
			} catch (SQLException e) {
				utils.UtilityClass.print(e);
			}

		}
		//REDIRECT TO LOGIN
		request.setAttribute("email", user.getEmail());
		request.setAttribute("password", pass_to_hash);
		request.getRequestDispatcher("Login").forward(request, response);
	}
}
