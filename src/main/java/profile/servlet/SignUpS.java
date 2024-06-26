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

		//inserimento sicuro al minimo id libero
		try {
			utils.UtilityClass.resetAuto_increment("Ham_user", ds);
		} catch (SQLException e) {
			utils.UtilityClass.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		JSONObject obj = new JSONObject();

		UtenteEntity cw = new UtenteEntity();

		String username, email, passwd, competenze;

		username = request.getParameter("userName");
		email = request.getParameter("email");
		passwd = utils.CifraPassword.toHash(request.getParameter("passwd"));
		competenze = request.getParameter("comp");

		/*
		Slot di codice per la gestione della registrazione dell'utente*
		Controllo se il campo competenze è nullo o vuoto, in questo caso vuol dire che l'user
		che vuole registrarsi è un utente
		 */
		if (competenze.equalsIgnoreCase("") || competenze == null) {

			try {

				request.setAttribute("email", email);
				request.setAttribute("userName", username);

				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/GetEmailS");
				requestDispatcher.forward(request, response);

				//check sull'email
				if ((boolean) request.getAttribute("rispostaEmail")) {
					utils.UtilityClass.print("###### Email gia in uso!"); //da eliminare

					obj.put("username", username);
					obj.put("email", email);
					obj.put("ruolo", UtenteEntity.Role.utente);
					obj.put("errore", "Email gia in uso");

					response.getWriter().print(obj);

					return;
				}

				//check sull'userName
				if ((boolean) request.getAttribute("rispostaUserName")) {
					utils.UtilityClass.print("###### UserName gia in uso!"); //da eliminare

					obj.put("username", username);
					obj.put("email", email);
					obj.put("ruolo", UtenteEntity.Role.utente);
					obj.put("errore", "Username gia in uso");

					response.getWriter().print(obj);

					return;
				}

				//controllo se esiste un buco tra gli ID
				int freeID = utils.FindFreeID.findFreeID("Ham_user", ds);

				cw.setUserName(username);
				cw.setEmail(email);
				cw.setPasswd(passwd);
				cw.setRuolo(UtenteEntity.Role.utente); //da gestire con i nuovi button

				if(freeID != -1){

					cw.setId(freeID+1);
					//inserimento diretto tramite auto-increment
					if (profileDAO.insert(cw)) {
						utils.UtilityClass.print("###### Inserimento nuovo Utente effettuato tramite ID: " + cw.getId() +"!"); //da eliminareelse
						obj.put("username", username);
						obj.put("email", email);
						obj.put("ruolo", UtenteEntity.Role.utente);

						response.getWriter().print(obj);

					} else
						utils.UtilityClass.print("###### Inserimento nuovo Utente fallito tramite ID" + cw.getId() + "!"); //da eliminare

				} else {
					//inserimento diretto tramite auto-increment
					if (profileDAO.insert(cw)) {
						utils.UtilityClass.print("###### Inserimento nuovo Utente effettuato!"); //da eliminareelse
						obj.put("username", username);
						obj.put("email", email);
						obj.put("ruolo", UtenteEntity.Role.utente);

						response.getWriter().print(obj);

					} else
						utils.UtilityClass.print("###### Inserimento nuovo Utente fallito!"); //da eliminare
				}

			} catch (SQLException e) {
				utils.UtilityClass.print(e);
			}

		} else { //Inserimento Content Writer

			try {

				request.setAttribute("email", email);
				request.setAttribute("userName", username);

				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/GetEmailS");
				requestDispatcher.forward(request, response);

				//check sull'email
				if ((boolean) request.getAttribute("rispostaEmail")) {
					utils.UtilityClass.print("###### Email gia in uso!"); //da eliminare

					obj.put("username", username);
					obj.put("email", email);
					obj.put("ruolo", UtenteEntity.Role.content_writer);
					obj.put("errore", "Email gia in uso");

					response.getWriter().print(obj);

					return;
				}

				//check sull'userName
				if ((boolean) request.getAttribute("rispostaUserName")) {
					utils.UtilityClass.print("###### UserName gia in uso!"); //da eliminare

					obj.put("username", username);
					obj.put("email", email);
					obj.put("ruolo", UtenteEntity.Role.content_writer);
					obj.put("errore", "Username gia in uso");

					response.getWriter().print(obj);

					return;
				}

				//controllo se esiste un buco tra gli ID
				int freeID = utils.FindFreeID.findFreeID("Ham_user", ds);

				cw.setUserName(username);
				cw.setEmail(email);
				cw.setPasswd(passwd);
				cw.setCompetenze(competenze);
				cw.setRuolo(UtenteEntity.Role.content_writer); //da gestire con i nuovi button

				if(freeID != -1){

					cw.setId(freeID+1);
					//inserimento diretto tramite auto-increment
					if (profileDAO.insert(cw)) {
						utils.UtilityClass.print("###### Inserimento nuovo Content Writer effettuato tramite ID: " + cw.getId() +"!"); //da eliminareelse
						obj.put("username", username);
						obj.put("email", email);
						obj.put("competenze", competenze);
						obj.put("ruolo", UtenteEntity.Role.content_writer);

						response.getWriter().print(obj);

					} else
						utils.UtilityClass.print("###### Inserimento nuovo Content Writer fallito tramite ID" + cw.getId() + "!"); //da eliminare

				} else {
					//inserimento diretto tramite auto-increment
					if (profileDAO.insert(cw)) {
						utils.UtilityClass.print("###### Inserimento nuovo Utente effettuato!"); //da eliminareelse
						obj.put("username", username);
						obj.put("email", email);
						obj.put("competenze", competenze);
						obj.put("ruolo", UtenteEntity.Role.content_writer);

						response.getWriter().print(obj);

					} else
						utils.UtilityClass.print("###### Inserimento nuovo Content Writer fallito!"); //da eliminare
				}

			} catch (SQLException e) {
				utils.UtilityClass.print(e);
			}

		}
	}
}
