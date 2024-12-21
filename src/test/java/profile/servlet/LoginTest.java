package profile.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;
import profile.servlet.LoginS;
import utils.MockDataSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

public class LoginTest {

    @Mock
    private ProfileDAO mockProfileDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext servletContext;

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private RequestDispatcher requestDispatcher;

    private LoginS loginServlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Inizializza la servlet
        loginServlet = new LoginS();

        // Configura il mock di ServletConfig e ServletContext
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        loginServlet.init(servletConfig);
        loginServlet.setProfileDAO(mockProfileDAO);


        when(request.getSession(anyBoolean())).thenReturn(session);
    }

    @Test
    public void testDoPostWithValidCredentials() throws Exception {
        // Configura il comportamento del mock del DAO
        when(request.getParameter("email")).thenReturn("n@n.it");
        when(request.getParameter("password")).thenReturn("test");
        when(mockProfileDAO.login("n@n.it", "test")).thenReturn(true);
        when(mockProfileDAO.getByEmail("n@n.it")).thenReturn(new UtenteEntity()); // Usa un oggetto fittizio

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        // Esegui il metodo doPost
        loginServlet.doPost(request, response);

        // Verifica che la sessione sia aggiornata con il profilo
        verify(session).setAttribute(eq("profile"), any());


        assert (stringWriter).toString().equals("{\"login\":true}");
    }


}
