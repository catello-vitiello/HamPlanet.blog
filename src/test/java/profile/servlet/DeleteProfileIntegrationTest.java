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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DeleteProfileIntegrationTest {

    @Mock
    private DataSource mockDataSource;

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

    private DeleteProfileS deleteProfileS;
    private UtenteEntity utenteEntity;

    private Connection getTestConnection() throws Exception {
        Connection conn = IntegrationTestIS.getTestDataSource().getConnection();
        conn.setAutoCommit(false);
        return conn;

    }

    @BeforeEach
    void setUp()throws Exception{
        MockitoAnnotations.openMocks(this);

        deleteProfileS = new DeleteProfileS();

        // Configura il mock di ServletConfig e ServletContext
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);


        deleteProfileS.init(servletConfig);
        ProfileDAO profileDAO = new ProfileDAO(mockDataSource);
        deleteProfileS.setProfileDAO(profileDAO);


    }

    @Test
    void testDeleteProfileSupervisor() throws Exception{

        utenteEntity = new UtenteEntity();
        utenteEntity.setId(1);
        utenteEntity.setEmail("n@n.it");
        utenteEntity.setRuolo(UtenteEntity.Role.supervisore);


        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utenteEntity);
        when(request.getParameter("id")).thenReturn("6");

        when(mockDataSource.getConnection()).thenReturn(getTestConnection(), getTestConnection());

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        deleteProfileS.doPost(request, response);

        assertEquals("{\"success\":true}", stringWriter.toString());
    }

    @Test
    void testDeleteOwnProfile() throws Exception{

        utenteEntity = new UtenteEntity();
        utenteEntity.setId(5);
        utenteEntity.setEmail("pippo@p.it");
        utenteEntity.setRuolo(UtenteEntity.Role.utente);


        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utenteEntity);

        when(mockDataSource.getConnection()).thenReturn(getTestConnection());

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        deleteProfileS.doPost(request, response);

        assertEquals("{\"success\":true}", stringWriter.toString());
    }
}
