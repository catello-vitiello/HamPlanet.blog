package profile.servlet;

import org.json.JSONObject;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ContentWriter")
public class ContentWriterInfoS extends HttpServlet {


    private static final long serialVersionUID = 1L;

    private ProfileDAO profileDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        DataSource ds = (DataSource) config.getServletContext().getAttribute("DataSource");
        profileDAO = new ProfileDAO(ds);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JSONObject json = new JSONObject();

        String id = req.getParameter("id");

        try {
            UtenteEntity contentWriter = profileDAO.getByID(Integer.parseInt(id));
            json.put("id", contentWriter.getId());
            json.put("name", contentWriter.getUserName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().print(json);
    }
}
