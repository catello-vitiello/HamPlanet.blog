package profile.servlet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;
import utils.CifraPassword;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SignUpSTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ProfileDAO mockProfileDAO;
    @Mock
    private RequestDispatcher mockRequestDispatcher;
    @Mock
    private Part cover;

    private SignUpS signUpS;

    @BeforeEach
    public void setUp()throws Exception {
        MockitoAnnotations.openMocks(this);

        signUpS = new SignUpS();

        // Configura il mock di ServletConfig e ServletContext
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);

        signUpS.init(servletConfig);
        signUpS.setProfileDAO(mockProfileDAO);
    }

    @Test
    public void signUpUser() throws Exception {
        UtenteEntity user = new UtenteEntity();
        user.setUserName("pino");
        user.setEmail("pino@gmail.com");
        user.setPasswd("pino");

        user.setRuolo(UtenteEntity.Role.utente);


        when(request.getParameter("username")).thenReturn("pino");
        when(request.getParameter("email")).thenReturn("pino@gmail.com");
        when(request.getParameter("password")).thenReturn("pino");
        when(request.getParameter("comp")).thenReturn("");



        when(mockProfileDAO.insert(user)).thenReturn(true);


        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        signUpS.doPost(request, response);

        assert (stringWriter).toString().contains("true");


    }

    @Test
    public void signupSupervisor() throws Exception {
        UtenteEntity user = new UtenteEntity();
        user.setUserName("pino");
        user.setEmail("pino@gmail.com");
        user.setPasswd("pino");


        user.setRuolo(UtenteEntity.Role.utente);

        when(request.getPart("cover")).thenReturn(cover);

        String token = "tydtydytk";

        when(request.getParameter("username")).thenReturn("pino");
        when(request.getParameter("email")).thenReturn("pino@gmail.com");
        when(request.getParameter("password")).thenReturn("pino");
        when(request.getParameter("comp")).thenReturn("");
        when(request.getParameter("token")).thenReturn(token);


        when(mockProfileDAO.isValidToken(token)).thenReturn(true);
        UtenteEntity supervisor = user.clone();
        supervisor.setRuolo(UtenteEntity.Role.supervisore);
        supervisor.setId(5);

        when(mockProfileDAO.insertSupervisor(supervisor, token)).thenReturn(true);


        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        signUpS.doPost(request, response);

        assert (stringWriter).toString().contains("true");


    }

    @Test
    public void signUpContentwriter() throws Exception {
        UtenteEntity user = new UtenteEntity();

        user.setUserName("pino");
        user.setEmail("pino@gmail.com");
        user.setPasswd("pino");
        user.setCompetenze("test competenze");
        user.setRuolo(UtenteEntity.Role.content_writer);

        when(request.getPart("cover")).thenReturn(cover);


        when(request.getParameter("username")).thenReturn("pino");
        when(request.getParameter("email")).thenReturn("pino@gmail.com");
        when(request.getParameter("password")).thenReturn("pino");
        when(request.getParameter("comp")).thenReturn("test competenze");


        when(mockProfileDAO.insert(user)).thenReturn(true);
        UtenteEntity user2 = user.clone();
        user2.setId(5);
        user2.setRuolo(UtenteEntity.Role.content_writer);
        when(mockProfileDAO.getByEmail(user.getEmail())).thenReturn(user2);

        when(request.getRequestDispatcher("FileManager")).thenReturn(mockRequestDispatcher);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        signUpS.doPost(request, response);

        assert (stringWriter).toString().contains("true");


    }



    @Test
    public void testDoPost_CredentialsMissing() throws Exception {
        // Simula una richiesta con credenziali mancanti
        when(request.getParameter("username")).thenReturn(null);
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);

        // Simula lo stream di output della risposta
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Chiamata al metodo doPost
        signUpS.doPost(request, response);

        // Verifica che la risposta JSON contenga un messaggio di errore
        writer.flush(); // Scrive i dati su stringWriter
        assert (stringWriter.toString().contains("credenziali non inserite"));
    }

    @Test
    public void testDoPost_ExistingEmail() throws Exception {
        // Simula una richiesta con email già in uso
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("email")).thenReturn("sbocciario@hamplanet.blog.it");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("comp")).thenReturn("");

        when(request.getPart("cover")).thenReturn(cover);

        // Simula che l'email esista già
        when(mockProfileDAO.checkEmail("sbocciario@hamplanet.blog.it")).thenReturn(true);

        // Simula lo stream di output della risposta
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Chiamata al metodo doPost
        signUpS.doPost(request, response);

        // Verifica che la risposta JSON contenga un messaggio di errore
        writer.flush();

        assert(stringWriter.toString().contains("Email gia in uso"));
    }


}