package controller;

import content_writer.dao.Content_WriterDao;
import content_writer.entity.Content_Writer;
import utils.UtilityClass;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/CWControl")
public class Content_WriterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        Content_WriterDao model = new Content_WriterDao(ds);

        String servizio = req.getParameter("service");
        if(servizio == null || servizio.equalsIgnoreCase("")){
            utils.UtilityClass.print("######## Errore nel servizio Content Writer");
            //mandare su una pagina di errore
            return;
        }

        /********************************************************/
        /*	                    SELECT ALL              		*/
        /********************************************************/
        if(servizio.equalsIgnoreCase("ALL")) {
            try {
                Collection<Content_Writer> creator = model.getAll();
                //req.setAttribute("getAll_CW", model.getAll()); //codice da utilizzare
                //da eliminare e inviare il tutto ad una jsp per la visualizzazione
                Iterator<Content_Writer> it = creator.iterator();
                while (it.hasNext()) {
                    Content_Writer cw = it.next();
                    utils.UtilityClass.print(cw.toString()); //da eliminare
                }

            } catch (SQLException e) {
                utils.UtilityClass.print(e);
            }
        }

        /********************************************************/
        /*	                  RICERCA PER ID            		*/
        /********************************************************/
        if(servizio.equalsIgnoreCase("ID")){

            String id = req.getParameter("id");
            if(id == null || id.equalsIgnoreCase("")){
                utils.UtilityClass.print("###### Errore con l'id Content Writer");
                //mandare su una pagina di errore
                return;
            }

            Content_Writer c = new Content_Writer();
            c.setId(Integer.parseInt(id));

            try {
                Content_Writer cw = model.getByID(c);
                //req.setAttribute("getAll_CW", model.getByID(c)); //codice da utilizzare
                //da eliminare e gestire in modo opportuno
                utils.UtilityClass.print(cw.toString()); //da eliminare
            } catch (SQLException e){
                UtilityClass.print(e);
            }
        }

        /********************************************************/
        /*	            INSERT NEW CONTENT WRITER            	*/
        /********************************************************/
        if(servizio.equalsIgnoreCase("ADD")){

            String userName = req.getParameter("userName");
            String email = req.getParameter("email");
            String password = req.getParameter("pass");
            String competenze = req.getParameter("comp");

            Content_Writer cw = new Content_Writer();
            cw.setUserName(userName);
            cw.setEmail(email);
            cw.setPasswd(password);
            cw.setCompetenze(competenze);

            try{
                if(model.insert(cw))
                    utils.UtilityClass.print("###### Inserimento nuovo Content Writer effettuato!"); //da eliminare
                else
                    utils.UtilityClass.print("###### Inserimento nuovo Content Writer fallito!"); //da eliminare
            } catch (SQLException e){
                utils.UtilityClass.print(e);
            }

        }

        /********************************************************/
        /*	               DELETE CONTENT WRITER               	*/
        /********************************************************/
        if(servizio.equalsIgnoreCase("DELETE")) {

            String id = req.getParameter("id");
            if(id == null || id.equalsIgnoreCase("")){
                utils.UtilityClass.print("###### Errore con l'id Content Writer!"); //da eliminare
                //mandare su una pagina di errore
                return;
            }

            Content_Writer cw = new Content_Writer();
            cw.setId(Integer.parseInt(id));

            try{

                if(model.delete(cw))
                    utils.UtilityClass.print("###### Eliminazione Content Writer effettuata!"); //da eliminare
                else
                    utils.UtilityClass.print("###### Eliminazione Content Writer fallita!"); //da eliminare

            } catch (SQLException e){
                utils.UtilityClass.print(e);
            }

        }

        /********************************************************/
        /*	               UPDATE CONTENT WRITER               	*/
        /********************************************************/
        if(servizio.equalsIgnoreCase("MERGE")){

            Content_Writer cw = new Content_Writer();
            Content_Writer originale = new Content_Writer();
            int change = 0;
            String id = req.getParameter("id");
            String userName = req.getParameter("userName");
            String email = req.getParameter("email");
            String password = req.getParameter("pass");
            String comp = req.getParameter("comp");

            if(id == null || id.equalsIgnoreCase("")){
                utils.UtilityClass.print("###### Errore con l'id Content Writer!"); //da eliminare
                //mandare su una pagina di errore
                return;
            }

            try {
                Content_Writer c = new Content_Writer();
                c.setId(Integer.parseInt(id));
                originale = model.getByID(c);

            } catch (SQLException e){
                utils.UtilityClass.print(e);
            }

            cw.setId(Integer.parseInt(id));
            if(userName != null && !(userName.equalsIgnoreCase(""))){
                cw.setUserName(userName);
                change++;
            } else
                cw.setUserName(originale.getUserName());

            if(email != null && !(email.equalsIgnoreCase(""))){
                cw.setEmail(email);
                change++;
            } else
                cw.setEmail(originale.getEmail());

            if(password != null && !(password.equalsIgnoreCase(""))){
                cw.setPasswd(password);
                change++;
            } else
                cw.setPasswd(originale.getPasswd());

            if(comp != null && !(comp.equalsIgnoreCase(""))){
                cw.setCompetenze(comp);
                change++;
            } else
                cw.setCompetenze(originale.getCompetenze());

            if(change != 0){
                try{

                    if(model.update(cw))
                        utils.UtilityClass.print("###### Aggiornamento Content Writer effettuato!"); //da eliminare
                    else
                        utils.UtilityClass.print("###### Aggiornamento Content Writer fallito!"); //da eliminare
                } catch (SQLException e){
                    utils.UtilityClass.print(e);
                }
            }
        }

    }//fine doPost
}//fine Servlet
