package profile.servlet;

import org.junit.jupiter.api.Test;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

class LoginTest {


    @Test
    public void testLogin() throws Exception {
        //DAO
        ProfileDAO dao = mock(ProfileDAO.class);

        //REQUEST & RESPONSE
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("password")).thenReturn("password");


        //SESSION
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("home.jsp")).thenReturn(dispatcher);


        //ENTITY
        UtenteEntity utente = new UtenteEntity();
        utente.setId(1);
        when(dao.login("email", "password")).thenReturn(true);
        when(dao.getByEmail("email")).thenReturn(utente);


        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        LoginS login = mock(LoginS.class);

        login.doPost(request, response);

        writer.flush();

    }

}