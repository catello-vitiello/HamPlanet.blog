package profile.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ToUpdateProfile")
public class ToUpdateProfileS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("id", request.getParameter("id_"));
		utils.UtilityClass.print("ID: " + request.getParameter("id_"));
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/UpdateCW.jsp");
		requestDispatcher.forward(request, response);
		

	}

}
