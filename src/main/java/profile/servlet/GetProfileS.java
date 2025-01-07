package profile.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONObject;

import profile.entity.UtenteEntity;

@WebServlet("/Profile")
public class GetProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");

		JSONObject json = new JSONObject();

		HttpSession session = request.getSession(false);
		if (session != null) {


			UtenteEntity utente = (UtenteEntity) session.getAttribute("profile");


			if (utente== null) {
				utils.UtilityClass.print("##### Errore nel recuperare il ID del Ham_user");
				return;
			}

			json.put("user", utente.getUserName());
			json.put("email", utente.getEmail());
			json.put("role", utente.getRuoloEnum());
			json.put("id", utente.getId());


		}
		response.getWriter().print(json);

	}

}
