package profile.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;
import utils.IntegrationTestIS;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.when;

public class SignupIntegrationTest {


    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
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

        ProfileDAO profileDAO = new ProfileDAO(IntegrationTestIS.getTestDataSource());

        signUpS.setProfileDAO(profileDAO);
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

        String token = "testtoken";

        when(request.getParameter("username")).thenReturn("pino");
        when(request.getParameter("email")).thenReturn("pino@gmail.com");
        when(request.getParameter("password")).thenReturn("pino");
        when(request.getParameter("comp")).thenReturn("");
        when(request.getParameter("token")).thenReturn(token);



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


        UtenteEntity user2 = user.clone();
        user2.setId(5);
        user2.setRuolo(UtenteEntity.Role.content_writer);

        when(request.getRequestDispatcher("FileManager")).thenReturn(mockRequestDispatcher);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        signUpS.doPost(request, response);

        assert (stringWriter).toString().contains("true");


    }


}
