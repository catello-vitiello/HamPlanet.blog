package post.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
import post.entity.PostEntity;
import profile.entity.UtenteEntity;
import utils.IntegrationTestIS;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DeletePostIntegrationTest {

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
    private DataSource mockDataSource;
    @Mock
    private PrintWriter printWriter;


    private DeletePostS deletePostS;


    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);

        deletePostS = new DeletePostS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);

        deletePostS.init(servletConfig);
        PostDAO postDAO = new PostDAO(mockDataSource);
        deletePostS.setPostDAO(postDAO);
    }

    private Connection getTestConnection() throws Exception {
        Connection connection = IntegrationTestIS.getTestDataSource().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    @Test
    void deletePostSupervisor() throws Exception {
        UtenteEntity utente = new UtenteEntity();
        utente.setRuolo(UtenteEntity.Role.supervisore);

        when(request.getParameter("postId")).thenReturn("1");
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utente);

        when(mockDataSource.getConnection()).thenReturn(getTestConnection());

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        deletePostS.doPost(request, response);

        assert(stringWriter).toString().equals("{\"result\":true}");
    }

    @Test
    void deletePostContentWriter() throws Exception {
        UtenteEntity utente = new UtenteEntity();
        utente.setId(1);
        utente.setRuolo(UtenteEntity.Role.content_writer);

        when(request.getParameter("postId")).thenReturn("1");
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utente);

        when(mockDataSource.getConnection()).thenReturn(getTestConnection(), getTestConnection());

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        deletePostS.doPost(request, response);

        assert(stringWriter).toString().equals("{\"result\":true}");
    }
}
