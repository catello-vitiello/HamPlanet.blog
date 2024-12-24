package commento.servlet;

import commento.dao.CommentoDAO;
import commento.entity.CommentoDTO;
import commento.entity.CommentoEntity;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentsPostSTest {


    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private CommentoDAO mockCommentoDAO;


    private CommentsPostS commentsPostS;


    @BeforeEach
    void setUp() throws ServletException {
        MockitoAnnotations.initMocks(this);


        commentsPostS = new CommentsPostS();


        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(request.getServletContext()).thenReturn(servletContext);

        commentsPostS.init(servletConfig);
        commentsPostS.setCommentoDAO(mockCommentoDAO);


    }

    @Test
    void testCommentpost() throws ServletException, IOException, SQLException {

        when(request.getParameter("postID")).thenReturn("3");

        //commenti di test
        CommentoDTO test_commento_1 = new CommentoDTO();
        CommentoDTO test_commento_2 = new CommentoDTO();

        //set id
        test_commento_1.setId(1);
        test_commento_2.setId(2);

        //set utente
        test_commento_1.setUsername("pippo");
        test_commento_2.setUsername("pluto");

        //set contentuto commento
        test_commento_1.setCommento("test 1");
        test_commento_2.setCommento("test 2");

        Collection<CommentoDTO> test_collection = new ArrayList<>();

        test_collection.add(test_commento_1);
        test_collection.add(test_commento_2);

        when(mockCommentoDAO.getAllByPost(3)).thenReturn(test_collection);


        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        JSONObject test_json = new JSONObject();
        test_json.put("comments", test_collection);

        commentsPostS.doPost(request, response);

        verify(mockCommentoDAO, times(1)).getAllByPost(3);
        assert (stringWriter.toString().contains(test_json.toString()));


    }

}