package post.servlet;

import navigation.Navigator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
import post.entity.PostEntity;
import utils.IntegrationTestIS;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.sql.Connection;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PostIntegrationTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private HttpSession session;
    @Mock
    private DataSource mockDataSource;

    private PostS postS;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        postS = new PostS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/post.jsp")).thenReturn(requestDispatcher);

        postS.init(servletConfig);

        Connection conn = IntegrationTestIS.getTestDataSource().getConnection();
        conn.setAutoCommit(false);

        when(mockDataSource.getConnection()).thenReturn(conn);
        PostDAO postDAO = new PostDAO(mockDataSource);
        postS.setDAO(postDAO);


    }

    @Test
    void testGetPosts() throws Exception {
        //Post test
        PostEntity postTest = new PostEntity();
        postTest.setId(1);
        postTest.setTesto("I prosciutti sono troppo belli");
        postTest.setNomePost("Post_1");
        postTest.setIdContent_Writer(1);



        when(request.getParameter("postId")).thenReturn("1");
        when(request.getParameter("new_page")).thenReturn("true");



        when(request.getSession(false)).thenReturn(session);
        Navigator navigator = new Navigator();
        when(session.getAttribute("Navigator")).thenReturn(navigator);


        postS.doGet(request, response);

        verify(request).setAttribute(eq("post"), eq(postTest));
        verify(requestDispatcher).forward(request, response);

    }

}
