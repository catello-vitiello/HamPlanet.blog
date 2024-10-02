package profile.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
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

		PrintWriter writer = response.getWriter();

		UtenteEntity user = new UtenteEntity();

		String username, email, competenze;
		String passwd = null;

		Part cover = request.getPart("cover");
		username = request.getParameter("username");
		email = request.getParameter("email");
		String pass_to_hash = request.getParameter("passwd");



		competenze = request.getParameter("comp");

		if (username == null || email == null || pass_to_hash == null ){
			response.getWriter().print(obj.append("errore", "credenziali non inserite"));
			return;
		}


		user.setUserName(username);
		user.setEmail(email);
		user.setPasswd(pass_to_hash);

		/*
		Slot di codice per la gestione della registrazione dell'utente*
		Controllo se il campo competenze è nullo o vuoto, in questo caso vuol dire che l'user
		che vuole registrarsi è un utente
		 */
		if (competenze.equalsIgnoreCase("") || competenze == null) {

			try {

				//check sull'email
				if (profileDAO.checkEmail(email)) {

					obj.put("errore", "Email gia in uso");

					writer.print(obj);

					return;
				}

				//check sull'userName
				if (profileDAO.checkUserName(username)) {

					obj.put("errore", "Username gia in uso");
					writer.print(obj);

					return;
				}

				user.setRuolo(UtenteEntity.Role.utente);

				if (profileDAO.insert(user)) {
					obj.put("result", true);

					String filename = "profile/" + user.getId() + ".jpg";

					request.setAttribute("Upload", true);
					request.setAttribute("InputStream", cover.getInputStream());
					request.setAttribute("Path", filename);
					request.getRequestDispatcher("FileManager").include(request, response);


				}else {
					obj.put("result", false);
				}



			} catch (SQLException e) {
				utils.UtilityClass.print(e);
			}

		} else { //Inserimento Content Writer

			try {

				//check sull'email
				if (profileDAO.checkEmail(email)) {

					obj.put("errore", "Email gia in uso");

					writer.print(obj);

					return;
				}

				//check sull'userName
				if (profileDAO.checkUserName(username)) {

					obj.put("errore", "Username gia in uso");

					writer.print(obj);
					return;
				}

				user.setCompetenze(competenze);
				user.setRuolo(UtenteEntity.Role.content_writer);

				if (profileDAO.insert(user)) {
					obj.put("result", true);

					String filename = "profile/" + user.getId() + ".jpg";

					request.setAttribute("Upload", true);
					request.setAttribute("InputStream", cover.getInputStream());
					request.setAttribute("Path", filename);
					request.getRequestDispatcher("FileManager").include(request, response);


				}else {
					obj.put("result", false);
				}
			} catch (SQLException e) {
				utils.UtilityClass.print(e);
			}

		}


		writer.print(obj);

	}
}
