package commento.servlet;

import commento.dao.CommentoDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utils.IntegrationTestIS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.when;

class CommentsPostIntegrationTest{

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;




    @Test
    public void commentsPostIntegrationTest() throws Exception{
        MockitoAnnotations.initMocks(this);

        DataSource ds = IntegrationTestIS.getConnection();

        CommentoDAO commentoDAO = new CommentoDAO(ds);

        when(request.getParameter("postID")).thenReturn("4");

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        CommentsPostS commentsPost = new CommentsPostS();
        commentsPost.setCommentoDAO(commentoDAO);

        commentsPost.doPost(request, response);


        assert(stringWriter.toString().contains("Nemmeno io lo sapevo"));


    }
}