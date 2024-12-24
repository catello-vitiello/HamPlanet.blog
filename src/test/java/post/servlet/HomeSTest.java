package post.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
import post.entity.PostEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

class HomeSTest {

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
    @Mock
    private HttpSession mockHttpSession;


    private HomeS homeS;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        homeS = new HomeS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/home.jsp")).thenReturn(mockRequestDispatcher);
        when(request.getSession(false)).thenReturn(mockHttpSession);

        homeS.init(servletConfig);
        homeS.setPostDAO(mockPostDAO);
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


        homeS.doPost(request, response);


        verify(mockPostDAO, times(1)).getAll(null);

        verify(request).setAttribute(eq("posts"), any(Collection.class));
        verify(mockRequestDispatcher).forward(request, response);

    }
}