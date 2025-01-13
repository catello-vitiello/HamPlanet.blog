package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import org.json.JSONObject;
import profile.dao.ProfileDAO;

import profile.entity.UtenteEntity;
import utils.CifraPassword;

@WebServlet("/UpdateProfile")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
		maxFileSize = 1024 * 1024 * 5,    // 5 MB
		maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class UpdateProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfileDAO profileDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DataSource ds = (DataSource) config.getServletContext().getAttribute("DataSource");
		profileDAO = new ProfileDAO(ds);
	}

	void setProfileDAO(ProfileDAO profileDAO){
		this.profileDAO = profileDAO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");

		JSONObject json = new JSONObject();

		UtenteEntity user;
		UtenteEntity originale = new UtenteEntity();
		int change = 0;
		boolean result = false;
		HttpSession session = request.getSession(false);

		user = (UtenteEntity) session.getAttribute("profile");

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String bio = request.getParameter("descrizione");



		if (user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){

			if (bio != null && !bio.isEmpty()) {
				user.setCompetenze(bio);
				change++;
			}else
				user.setCompetenze(originale.getCompetenze());


			Part part = request.getPart("cover");
			if(part != null && part.getSize() > 0) {

				String filename = "profile/" + user.getId() + ".jpeg";
				request.setAttribute("Upload", true);
				request.setAttribute("InputStream", part.getInputStream());
				request.setAttribute("Path", filename);
				request.getRequestDispatcher("FileManager").include(request, response);
			}
		}

		if(userName != null && !(userName.equalsIgnoreCase(""))){
			user.setUserName(userName);
			change++;
		} else
			user.setUserName(originale.getUserName());


		if(password != null && !(password.equalsIgnoreCase(""))){
			user.setPasswd(password);
			change++;
		} else
			user.setPasswd(originale.getPasswd());

		if(change > 0){
			try{

				result = profileDAO.update(user);

				
			} catch (SQLException e){
				utils.UtilityClass.print(e);
			}
		}
		json.put("result", result);
		response.getWriter().print(json);
	}

}
