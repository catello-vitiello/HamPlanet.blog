package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
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

import profile.entity.UtenteEntity;

@WebServlet("/Profile")
public class GetProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDAO profileDAO;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource dataSource = (DataSource) config.getServletContext().getAttribute("dataSource");
		profileDAO = new ProfileDAO(dataSource);
	}

	void setProfileDAO(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}

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

			int id = Integer.parseInt(request.getParameter("pageId"));
			boolean new_page = Boolean.parseBoolean(request.getParameter("new_page"));



			try {
				if (utente== null) {
					utils.UtilityClass.print("##### Errore nel recuperare il ID del Ham_user");
					//mandare su una pagina di errore
					return;
				}

				UtenteEntity user = profileDAO.getByID(utente.getId());
				json.put("user", user);
				utils.UtilityClass.print(user.toString()); //da eliminare
			} catch (SQLException e) {
				utils.UtilityClass.print(e);
			}


			//NAVIGATION
			Navigator navigator = (Navigator) session.getAttribute("Navigator");
			if (new_page)
				navigator.save();
			navigator.setCurrent(new Page(id, Page.Type.POST));

		}
		response.getWriter().print(json);

	}

}
