package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

@WebServlet("/GetCWListS")
public class GetCWListS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProfileDAO model = new ProfileDAO(ds);
		
		String filtro = request.getParameter("filter");
		if(filtro == null) {
			utils.UtilityClass.print(">. ERRORE NELL'APPLICARE IL FILTRO A MOMENTO DI LISTING");
		}
		
		try {

			Collection<UtenteEntity> creator = model.getAll(filtro);
            Iterator<UtenteEntity> it = creator.iterator();
            while (it.hasNext()) {
                UtenteEntity cw = it.next();

				JSONObject obj = new JSONObject();
				obj.put("ID", cw.getId());
				obj.put("username", cw.getUserName());
				if(filtro.equalsIgnoreCase("content_writer"))
					obj.put("competences", cw.getCompetenze());
				obj.put("role", cw.getRuolo());

				response.getWriter().print(obj);

            }

            request.setAttribute("CW", model.getAll(filtro));
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/CWView.jsp");
			requestDispatcher.forward(request, response);
			return;
            
		} catch (SQLException e) {
			utils.UtilityClass.print(e);
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
