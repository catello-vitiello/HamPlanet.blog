package profile.servlet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SignUpSTest {

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
    private RequestDispatcher mockRequestDispatcher;

    private SignUpS signUpS;

    @BeforeEach
    void setUp()throws Exception {
        MockitoAnnotations.openMocks(this);

        signUpS = new SignUpS();

        // Configura il mock di ServletConfig e ServletContext
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(mockRequestDispatcher);

        signUpS.init(servletConfig);
        signUpS.setProfileDAO(mockProfileDAO);
    }

    @Test
    void signUpUser() throws Exception {
        when(request.getParameter("username")).thenReturn("pino");
        when(request.getParameter("email")).thenReturn("pino@gmail.com");
        when(request.getParameter("passwd")).thenReturn("pino");
        when(request.getParameter("comp")).thenReturn("");



        when(mockProfileDAO.insert(any(UtenteEntity.class))).thenReturn(true);


        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        signUpS.doPost(request, response);

        assert (stringWriter).toString().contains("{username:\"pino\", }");


    }


}