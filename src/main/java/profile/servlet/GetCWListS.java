package profile.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import profile.dao.Ham_userDao;
import profile.entity.Ham_user;

@WebServlet("/GetCWListS")
public class GetCWListS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Ham_userDao model = new Ham_userDao(ds);
		
		String filtro = request.getParameter("filter");
		if(filtro != null) {
			utils.UtilityClass.print(">. ERRORE NELL'APPLICARE IL FILTRO A MOMENTO DI LISTING");
		}
		
		try {
			
			Collection<Ham_user> creator = model.getAll(filtro);
            Iterator<Ham_user> it = creator.iterator();
            while (it.hasNext()) {
                Ham_user cw = it.next();
                utils.UtilityClass.print(cw.toString()); //da eliminare
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
