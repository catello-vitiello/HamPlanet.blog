package profile.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateProfileSTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ProfileDAO mockProfileDAO;
    @Mock
    private HttpSession mockSession;
    @Mock
    private RequestDispatcher requestDispatcher;

    private UpdateProfileS updateProfileS;

    @BeforeEach
    void setUp()throws Exception {
        MockitoAnnotations.openMocks(this);

        updateProfileS = new UpdateProfileS();

        // Configura il mock di ServletConfig e ServletContext
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(request.getSession(false)).thenReturn(mockSession);

        updateProfileS.init(servletConfig);
        updateProfileS.setProfileDAO(mockProfileDAO);


    }

    @Test
    void updateTest()throws Exception {

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("userName")).thenReturn("pino");
        when(request.getParameter("password")).thenReturn("pino");

        UtenteEntity original = new UtenteEntity();
        original.setId(1);
        when(mockSession.getAttribute("profile")).thenReturn(original);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);



        when(mockProfileDAO.update(any(UtenteEntity.class))).thenReturn(true);

        updateProfileS.doPost(request, response);

        assert (stringWriter).toString().equals("{\"result\":true}");

    }

}