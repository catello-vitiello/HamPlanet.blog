package post.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
import post.entity.PostEntity;
import profile.entity.UtenteEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

class GetPostsHistorySTest {

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
    private PostDAO mockPostDAO;

    private GetPostsHistoryS getPostsHistoryS;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        getPostsHistoryS = new GetPostsHistoryS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);

        getPostsHistoryS.init(servletConfig);
        getPostsHistoryS.setPostDAO(mockPostDAO);



    }

    @Test
    void testGetPostsHistoryS() throws Exception {
        UtenteEntity user = new UtenteEntity();
        user.setId(5);
        user.setRuolo(UtenteEntity.Role.content_writer);
        user.setEmail("user@test.com");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(user);

        PostEntity post = new PostEntity();
        post.setId(10);
        post.setNomePost("test");
        post.setTesto("test");

        Collection<PostEntity> test = new ArrayList<>();
        test.add(post);

        when(mockPostDAO.getAllByContentWriter(5)).thenReturn(test);

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        getPostsHistoryS.doPost(request, response);

        verify(mockPostDAO, times(1)).getAllByContentWriter(5);
        assertEquals("{\"posts\":[{\"nomePost\":\"test\"," +
                "\"idContent_Writer\":0,\"id\":10,\"liked\":false," +
                "\"testo\":\"test\"}]}", stringWriter.toString());

    }

}