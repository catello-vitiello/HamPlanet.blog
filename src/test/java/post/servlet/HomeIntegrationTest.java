package post.servlet;

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
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collection;
import java.util.LinkedList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class HomeIntegrationTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private RequestDispatcher mockRequestDispatcher;


    private HomeS homeS;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        homeS = new HomeS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/home.jsp")).thenReturn(mockRequestDispatcher);
        homeS.init(servletConfig);

        PostDAO postDAO = new PostDAO(IntegrationTestIS.getTestDataSource());

        homeS.setPostDAO(postDAO);
    }

    @Test
    void testRetrievePost() throws Exception {

        homeS.doPost(request, response);

        verify(request).setAttribute(eq("posts"), any(Collection.class));
        verify(mockRequestDispatcher).forward(request, response);

    }
}
