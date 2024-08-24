package post.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
import profile.entity.UtenteEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

class UnlikePostsTest {

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
    private PostDAO mockPostDAO;


    private UnlikePosts unlikePosts;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        unlikePosts = new UnlikePosts();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);

        unlikePosts.init(servletConfig);
        unlikePosts.setPostDAO(mockPostDAO);
    }

    @Test
    void testUnlikePost() throws Exception {

        UtenteEntity user = new UtenteEntity();
        user.setRuolo(UtenteEntity.Role.utente);
        user.setId(6);

        when(request.getParameter("postId")).thenReturn("1");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(user);

        when(mockPostDAO.unlike(1, 6)).thenReturn(true);

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        unlikePosts.doPost(request, response);

        verify(mockPostDAO, times(1)).unlike(1, 6);
        assert (stringWriter).toString().contains("true");


    }
}