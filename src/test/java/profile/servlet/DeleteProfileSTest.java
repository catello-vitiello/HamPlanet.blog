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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class DeleteProfileSTest {
    @Mock
    private ProfileDAO mockProfileDAO;

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
    private RequestDispatcher requestDispatcher;

    private DeleteProfileS deleteProfileS;
    private UtenteEntity utenteEntity;

    @BeforeEach
    public void setUp()throws Exception{
        MockitoAnnotations.openMocks(this);

        deleteProfileS = new DeleteProfileS();

        // Configura il mock di ServletConfig e ServletContext
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);


        deleteProfileS.init(servletConfig);
        deleteProfileS.setProfileDAO(mockProfileDAO);


    }

    @Test
    public void testDeleteProfileSupervisor() throws Exception{

        utenteEntity = new UtenteEntity();
        utenteEntity.setId(1);
        utenteEntity.setEmail("n@n.it");
        utenteEntity.setRuolo(UtenteEntity.Role.supervisore);

        UtenteEntity entityToDelete =new UtenteEntity();
        entityToDelete.setId(5);
        entityToDelete.setEmail("pluto@p.it");


        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utenteEntity);
        when(request.getParameter("id")).thenReturn("5");

        when(mockProfileDAO.getByID(5)).thenReturn(entityToDelete);

        when(mockProfileDAO.delete(entityToDelete)).thenReturn(true);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        deleteProfileS.doPost(request, response);

        assertEquals("{\"success\":true}", stringWriter.toString());
    }

    @Test
    public void testDeleteOwnProfile() throws Exception{

        utenteEntity = new UtenteEntity();
        utenteEntity.setId(5);
        utenteEntity.setEmail("pippo@p.it");
        utenteEntity.setRuolo(UtenteEntity.Role.utente);


        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utenteEntity);

        when(mockProfileDAO.delete(utenteEntity)).thenReturn(true);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        deleteProfileS.doPost(request, response);

        assertEquals("{\"success\":true}", stringWriter.toString());
    }

}