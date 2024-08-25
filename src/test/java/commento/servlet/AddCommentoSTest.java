package commento.servlet;

import commento.dao.CommentoDAO;
import commento.entity.CommentoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import profile.entity.UtenteEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddCommentoSTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpSession session;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private CommentoDAO mockCommentoDAO;

    private AddCommentoS addCommentoS;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        addCommentoS = new AddCommentoS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getSession(false)).thenReturn(session);

        addCommentoS.init(servletConfig);
        addCommentoS.setCommentoDAO(mockCommentoDAO);

    }

    @Test
    void testAddCommento() throws Exception {

        UtenteEntity test_user = new UtenteEntity();
        test_user.setId(16);
        test_user.setEmail("Lc16@gmail.com");
        test_user.setUserName("Leclerc");

        when(request.getParameter("commento")).thenReturn("bella pizza");
        when(request.getParameter("postID")).thenReturn("3");

        when(session.getAttribute("profile")).thenReturn(test_user);

        CommentoEntity commento = new CommentoEntity();
        commento.setContenutoCommento("bella pizza");
        commento.setIdUtente(16);
        commento.setIdPost(3);

        when(mockCommentoDAO.insert(commento)).thenReturn(true);


        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        addCommentoS.doPost(request, response);

        verify(mockCommentoDAO, times(1)).insert(commento);
        assert (stringWriter).toString().contains("true");


    }

}