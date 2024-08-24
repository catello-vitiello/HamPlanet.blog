package post.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
import post.entity.PostEntity;
import profile.entity.UtenteEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

class RetrievePostTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private PostDAO mockPostDAO;
    @Mock
    private RequestDispatcher mockRequestDispatcher;


    private RetrievePost retrievePost;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        retrievePost = new RetrievePost();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/index.jsp")).thenReturn(mockRequestDispatcher);

        retrievePost.init(servletConfig);
        retrievePost.setPostDAO(mockPostDAO);
    }

    @Test
    void testRetrievePost() throws Exception {

        Collection<PostEntity> collection = new LinkedList<>();

        PostEntity post_test_1= new PostEntity();
        PostEntity post_test_2= new PostEntity();

        post_test_1.setId(1);
        post_test_2.setId(2);

        post_test_1.setNomePost("test_1");
        post_test_2.setNomePost("test_2");

        post_test_1.setIdContent_Writer(6);
        post_test_2.setIdContent_Writer(6);

        post_test_1.setTesto("test_1");
        post_test_2.setTesto("test_2");

        collection.add(post_test_1);
        collection.add(post_test_2);

        when(mockPostDAO.getAll(null)).thenReturn(collection);



        retrievePost.doPost(request, response);


        verify(mockPostDAO, times(1)).getAll(null);

        verify(request).setAttribute(eq("collection"), any(Collection.class));
        verify(mockRequestDispatcher).forward(request, response);

    }
}