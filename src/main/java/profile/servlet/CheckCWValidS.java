package profile.servlet;

import org.json.JSONObject;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/IsVerified")
public class CheckCWValidS extends HttpServlet {

    private ProfileDAO profileDAO;

    @Override
    public void init() throws ServletException{
        super.init();

        DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
        profileDAO = new ProfileDAO(ds);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        JSONObject jsonObject = new JSONObject();

        String email = req.getParameter("email");

        UtenteEntity cw;
        String status = "";

        try{

            cw = profileDAO.getByEmail(email);

            //controllo se si tratta di un Content Writer
            //if(!cw.getRuolo().equalsIgnoreCase("content_writer")){
            //    //pagina di errore
            //    return;
            // }

            status = profileDAO.status(cw);

            jsonObject.put("id", cw.getId());
            jsonObject.put("userName", cw.getUserName());
            jsonObject.put("email", cw.getEmail());
            jsonObject.put("ruolo", cw.getRuolo());
            jsonObject.put("competenze", cw.getCompetenze());
            jsonObject.put("status: ", status);

        } catch (SQLException e){
            utils.UtilityClass.print(e);
        }

        resp.getWriter().print(jsonObject);

        //redirect alla pagina di attesa se è in pending
        if(status.equalsIgnoreCase("waiting")){
            //pagina di attesa
            return;
        } else {
            //Home page
            return;
        }

    }

}
