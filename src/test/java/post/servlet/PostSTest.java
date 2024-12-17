package post.servlet;

import com.mysql.cj.xdevapi.JsonParser;
import commento.dao.CommentoDAO;
import commento.entity.CommentoEntity;
import navigation.Navigator;
import org.json.JSONObject;
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

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostSTest {

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
    private PostDAO mockPostDAO;

    @Mock
    private Navigator mockNavigator;

    private PostS postS;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        postS = new PostS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/post.jsp")).thenReturn(requestDispatcher);

        postS.init(servletConfig);
        postS.setDAO(mockPostDAO);


    }

    @Test
    void testGetPosts() throws Exception {
        //Post test
        PostEntity postTest = new PostEntity();
        postTest.setId(1);
        postTest.setTesto("test");
        postTest.setNomePost("test");
        postTest.setIdContent_Writer(4);



        when(request.getParameter("postId")).thenReturn("1");
        when(request.getParameter("new_page")).thenReturn("true");

        when(mockPostDAO.getByID(1)).thenReturn(postTest);


        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("Navigator")).thenReturn(mockNavigator);


        postS.doGet(request, response);

        verify(mockPostDAO, times(1)).getByID(1);
        verify(mockNavigator).save();

        verify(request).setAttribute(eq("post"), eq(postTest));
        verify(requestDispatcher).forward(request, response);

    }

}