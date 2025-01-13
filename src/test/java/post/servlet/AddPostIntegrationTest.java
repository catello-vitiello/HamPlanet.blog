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
import javax.servlet.http.Part;
import javax.sql.DataSource;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AddPostIntegrationTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpSession session;
    @Mock
    private DataSource mockDataSource;
    @Mock
    private Part part;


    private AddPostS addPostS;


    private Connection getTestConnection() throws Exception {
        Connection conn = IntegrationTestIS.getTestDataSource().getConnection();
        conn.setAutoCommit(false);
        return conn;
    }

    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);

        addPostS = new AddPostS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        addPostS.init(servletConfig);


        when(mockDataSource.getConnection()).thenReturn(getTestConnection(), getTestConnection());
        PostDAO postDAO = new PostDAO(mockDataSource);
        addPostS.setPostDAO(postDAO);
    }

    @Test
    void addPost() throws Exception {
        UtenteEntity cw = new UtenteEntity();
        cw.setId(4);
        cw.setRuolo(UtenteEntity.Role.content_writer);


        when(request.getParameter("title")).thenReturn("gino paoli");
        when(request.getParameter("text")).thenReturn("test");
        when(request.getPart("cover")).thenReturn(part);
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(cw);


        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        addPostS.doPost(request, response);

        //verifica
        assert (stringWriter).toString().contains("{\"result\":true}");
    }


}
