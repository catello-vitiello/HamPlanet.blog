package profile.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

@WebServlet("/RegistrationS")
public class RegistrationS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDAO profileDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
		profileDAO = new ProfileDAO(ds);

		//inserimento sicuro al minimo id libero
		try {
			utils.UtilityClass.resetAuto_increment("UtenteEntity", ds);
		} catch (SQLException e) {
			utils.UtilityClass.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		if(competenze.equalsIgnoreCase("") || competenze == null) {

			UtenteEntity u = new UtenteEntity();
			u.setUserName(username);
			u.setEmail(email);
			u.setPasswd(passwd);
			u.setRuolo(UtenteEntity.Role.utente); //da gestire con i nuovi button
			/*
			try {

				request.setAttribute("email", email);
				request.setAttribute("userName", username);

				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/GetEmailS");
				requestDispatcher.forward(request, response);

				if((boolean) request.getAttribute("rispostaEmail")) {
					utils.UtilityClass.print("###### Email gia in uso!"); //da eliminare
					return;
				}

				if((boolean) request.getAttribute("rispostaUserName")) {
					utils.UtilityClass.print("###### UserName gia in uso!"); //da eliminare
					return;
				}
				
				int freeID = utils.FindFreeID.findFreeID("UtenteEntity", ds);
				
				if(freeID != -1) {
					
					Connection con = null;
					PreparedStatement ps = null;
					String sql = "INSERT INTO UtenteEntity(id, userName, email, passwd, competenze, ruolo) VALUES (?, ?, ?, ?, ?, ?)";
					
					try {
						
						//con = ds.getConnection();
						ps = con.prepareStatement(sql);
						ps.setInt(1, freeID);
						ps.setString(2, username);
						ps.setString(3, email);
						ps.setString(4, utils.CifraPassword.toHash(passwd));
						ps.setString(5, competenze);
						ps.setString(6, "utente");
						
						utils.UtilityClass.print("INSERIMENTO ID CUSTOM UTENTE: " + ps.toString());
						ps.executeUpdate();
						
					}finally {
						if(ps != null)
							ps.close();
						if(con != null)
							con.close();
					}
					
					return;
				}

				if(model.insert(u))
					utils.UtilityClass.print("###### Inserimento nuovo Utente effettuato!"); //da eliminare
				else
					utils.UtilityClass.print("###### Inserimento nuovo Utente fallito!"); //da eliminare
			}catch (SQLException e) {
				utils.UtilityClass.print(e);
			}*/
			
			//redirect su homePage
			
			return;
/*********************************************************************************************************/
		} else { //parte la gestione per la registrazione Content Writer

			cw.setUserName(username);
			cw.setEmail(email);
			cw.setPasswd(passwd);
			cw.setCompetenze(competenze);
			cw.setRuolo(UtenteEntity.Role.content_writer); //da gestire con i nuovi button
			/*
			try {

				request.setAttribute("email", email);
				request.setAttribute("userName", username);

				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/GetEmailS");
				requestDispatcher.forward(request, response);

				if((boolean) request.getAttribute("rispostaEmail")) {
					utils.UtilityClass.print("###### Email gia in uso!"); //da eliminare
					return;
				}

				if((boolean) request.getAttribute("rispostaUserName")) {
					utils.UtilityClass.print("###### UserName gia in uso!"); //da eliminare
					return;
				}
				
				int freeID = utils.FindFreeID.findFreeID("UtenteEntity", ds);
				
				if(freeID != -1) {
					
					Connection con = null;
					PreparedStatement ps = null;
					String sql = "INSERT INTO UtenteEntity(id, userName, email, passwd, competenze, ruolo) VALUES (?, ?, ?, ?, ?, ?)";
					
					try {
						
						con = ds.getConnection();
						ps = con.prepareStatement(sql);
						ps.setInt(1, freeID);
						ps.setString(2, username);
						ps.setString(3, email);
						ps.setString(4, utils.CifraPassword.toHash(passwd));
						ps.setString(5, competenze);
						ps.setString(6, "content_writer");
						
						utils.UtilityClass.print("INSERIMENTO ID CUSTOM CONTEN_WRITER: " + ps.toString());
						ps.executeUpdate();
						
					}finally {
						if(ps != null)
							ps.close();
						if(con != null)
							con.close();
					}
					
					return;
				}
				
				//inserimento diretto tramite auto-increment
				if(model.insert(cw))
					utils.UtilityClass.print("###### Inserimento nuovo Content Writer effettuato!"); //da eliminareelse
				else
					utils.UtilityClass.print("###### Inserimento nuovo Content Writer fallito!"); //da eliminare
			}catch (SQLException e) {
				utils.UtilityClass.print(e);
			}
			*/


		//redirect sulla pagina di attessa per la validazione account
		return;
		
		}
	}

}
