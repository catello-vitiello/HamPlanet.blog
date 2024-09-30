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
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.json.JSONObject;
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
		response.setContentType("application/json");

		JSONObject json = new JSONObject();

		UtenteEntity user = new UtenteEntity();
		UtenteEntity originale = new UtenteEntity();
		int change = 0;
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String password = request.getParameter("pass");
		Part part = request.getPart("cover");

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
		if (originale.getRuolo().equals(UtenteEntity.Role.content_writer.toString())){
			if(part.getSize() > 0) {
				String filename = "profile/" + id + ".jpg";
				request.setAttribute("Upload", true);
				request.setAttribute("InputStream", part.getInputStream());
				request.setAttribute("Path", filename);
				request.getRequestDispatcher("FileManager").include(request, response);
			}
		}

		user.setId(Integer.parseInt(id));
		if(userName != null && !(userName.equalsIgnoreCase(""))){
			user.setUserName(userName);
			change++;
		} else
			user.setUserName(originale.getUserName());


		if(password != null && !(password.equalsIgnoreCase(""))){
			user.setPasswd(CifraPassword.toHash(password));
			change++;
		} else
			user.setPasswd(originale.getPasswd());

		if(change > 0){
			try{

				profileDAO.update(user);
				response.getWriter().print(json.put("result", "done"));

				
			} catch (SQLException e){
				utils.UtilityClass.print(e);
			}
		}
	}

}
