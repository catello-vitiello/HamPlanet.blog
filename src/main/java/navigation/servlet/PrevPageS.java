package navigation.servlet;

import navigation.Navigator;
import navigation.Page;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("PrevPage")
public class PrevPageS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();

        Navigator navigator = (Navigator) request.getSession().getAttribute("Navigator");
        Page page = navigator.prev();


        if(page != null) {
            jsonObject.append("id", page.getId());
            jsonObject.append("type", page.getType().toString());
        }
        response.getWriter().print(jsonObject);

    }
}