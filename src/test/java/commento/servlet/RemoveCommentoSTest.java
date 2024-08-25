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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RemoveCommentoSTest {

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
    private CommentoDAO mockCommentoDAO;


    private RemoveCommentoS removeCommentoS;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        removeCommentoS = new RemoveCommentoS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getSession(false)).thenReturn(session);

        when(request.getParameter("commentoID")).thenReturn("5");
        when(request.getParameter("postID")).thenReturn("7");

        removeCommentoS.init(servletConfig);
        removeCommentoS.setCommentoDAO(mockCommentoDAO);



    }

    private CommentoEntity getFakeComment(){

        //commento fittizio
        CommentoEntity commento_test = new CommentoEntity();
        commento_test.setId(5);

        return commento_test;
    }

    @Test
    void testRemoveCommentoUser() throws Exception {

        //Utente fittizio
        UtenteEntity user_test = new UtenteEntity();
        user_test.setId(6);
        user_test.setEmail("test@test.com");
        user_test.setRuolo(UtenteEntity.Role.utente);

        CommentoEntity commento_test = getFakeComment();

        when(session.getAttribute("profile")).thenReturn(user_test);

        when(mockCommentoDAO.isOwnComment(5, 6)).thenReturn(true);
        when(mockCommentoDAO.delete(commento_test)).thenReturn(true);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        removeCommentoS.doPost(request, response);

        assert (stringWriter).toString().contains("true");
    }


    @Test
    void testRemoveCommentoSupervisor() throws Exception {
        //Utente fittizio
        UtenteEntity user_test = new UtenteEntity();
        user_test.setId(3);
        user_test.setEmail("testSuper@test.com");
        user_test.setRuolo(UtenteEntity.Role.supervisore);

        CommentoEntity commento_test = getFakeComment();

        when(session.getAttribute("profile")).thenReturn(user_test);

        when(mockCommentoDAO.delete(commento_test)).thenReturn(true);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        removeCommentoS.doPost(request, response);

        assert (stringWriter).toString().contains("true");
    }

}