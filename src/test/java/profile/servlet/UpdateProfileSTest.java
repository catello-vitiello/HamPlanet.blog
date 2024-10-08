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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateProfileSTest {

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


        when(mockProfileDAO.getByID(1)).thenReturn(original);

        when(mockProfileDAO.update(any(UtenteEntity.class))).thenReturn(true);

        updateProfileS.doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }

}