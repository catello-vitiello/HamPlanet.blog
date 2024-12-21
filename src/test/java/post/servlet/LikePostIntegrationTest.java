package post.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
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

import static org.mockito.Mockito.*;

public class LikePostIntegrationTest {


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


    private LikePostS likePostS;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        likePostS = new LikePostS();

        Connection conn = IntegrationTestIS.getTestDataSource().getConnection();
        conn.setAutoCommit(false);

        when(mockDataSource.getConnection()).thenReturn(conn);
        PostDAO postDAO = new PostDAO(mockDataSource);
        likePostS.setPostDAO(postDAO);
    }

    @Test
    void testLikePost() throws Exception {

        UtenteEntity user = new UtenteEntity();
        user.setRuolo(UtenteEntity.Role.utente);
        user.setId(6);

        when(request.getParameter("postId")).thenReturn("1");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(user);

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);



        likePostS.doPost(request, response);

        assert (stringWriter).toString().contains("true");

    }
}
