package post.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import javax.servlet.http.Part;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AddPostSTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpSession session;
    @Mock
    private PostDAO mockPostDAO;
    @Mock
    private Part part;


    private AddPostS addPostS;


    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.openMocks(this);

        addPostS = new AddPostS();

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);


        addPostS.init(servletConfig);
        addPostS.setPostDAO(mockPostDAO);
    }

    @Test
    void addPost() throws Exception {
        UtenteEntity cw = new UtenteEntity();
        cw.setId(4);
        cw.setRuolo(UtenteEntity.Role.content_writer);


        when(request.getParameter("title")).thenReturn("gino paoli");
        when(request.getParameter("text")).thenReturn("test");
        when(request.getPart("cover")).thenReturn(part);
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(cw);

        when(mockPostDAO.insert(any(PostEntity.class))).thenReturn(true);



        addPostS.doPost(request, response);

        //verifica
        verify(mockPostDAO, times(1)).insert(any(PostEntity.class));
    }

    @Test
    void invalidAddPost() throws Exception {
        UtenteEntity cw = new UtenteEntity();
        cw.setId(4);
        cw.setRuolo(UtenteEntity.Role.utente);


        when(request.getParameter("title")).thenReturn(null); //senza titolo
        when(request.getParameter("text")).thenReturn("test");
        when(request.getPart("cover")).thenReturn(null); //senza cover
        when(request.getSession(anyBoolean())).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(cw);

        when(mockPostDAO.insert(any(PostEntity.class))).thenReturn(true);



        addPostS.doPost(request, response);

        //verifica
        verify(mockPostDAO, times(0)).insert(any(PostEntity.class));

    }

}