package post.servlet;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import post.dao.PostDAO;
import post.entity.PostEntity;
import profile.entity.UtenteEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

class DeletePostSTest {

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
    private PostDAO mockPostaDAO;
    @Mock
    private PrintWriter printWriter;


    private DeletePostS deletePostS;


    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);

        deletePostS = new DeletePostS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);

        deletePostS.init(servletConfig);
        deletePostS.setPostDAO(mockPostaDAO);
    }

    @Test
    public void deletePostSupervisor() throws Exception {
        UtenteEntity utente = new UtenteEntity();
        utente.setRuolo(UtenteEntity.Role.supervisore);

        when(request.getParameter("postId")).thenReturn("1");
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utente);


        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        deletePostS.doPost(request, response);

        verify(mockPostaDAO, times(1)).delete(any(PostEntity.class));
        assert(stringWriter).toString().equals("{\"result\":true}");
    }

    @Test
    public void deletePostContentWriter() throws Exception {
        UtenteEntity utente = new UtenteEntity();
        utente.setRuolo(UtenteEntity.Role.content_writer);

        when(request.getParameter("postId")).thenReturn("1");
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utente);
        when(mockPostaDAO.isCwOwnPost(anyInt(), anyInt())).thenReturn(true);


        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        deletePostS.doPost(request, response);

        verify(mockPostaDAO, times(1)).delete(any(PostEntity.class));
        assert(stringWriter).toString().equals("{\"result\":true}");
    }


    @Test
    void failureDeletePost() throws Exception {
        UtenteEntity utente = new UtenteEntity();
        utente.setRuolo(UtenteEntity.Role.content_writer);

        when(request.getParameter("postId")).thenReturn("1");
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(utente);
        when(mockPostaDAO.isCwOwnPost(20, 5)).thenReturn(false);//post non esistente

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        deletePostS.doPost(request, response);

        verify(mockPostaDAO, times(0)).delete(any(PostEntity.class));
        assert(stringWriter).toString().equals("{\"result\":false}");

    }

}