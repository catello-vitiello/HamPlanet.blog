package navigation.servlet;

import navigation.Navigator;
import navigation.Page;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NextPage", value = "/NextPage")
public class NextPageS extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");

        Navigator navigator = (Navigator) request.getSession().getAttribute("Navigator");
        Page page = navigator.next();

        JSONObject jsonObject = new JSONObject();
        if(page != null) {
            jsonObject.append("id", page.getId());
            jsonObject.append("type", page.getType().toString());
        }
        response.getWriter().print(jsonObject);


    }
}