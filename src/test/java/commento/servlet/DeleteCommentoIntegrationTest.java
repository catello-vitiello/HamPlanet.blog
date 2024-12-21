package commento.servlet;

import commento.dao.CommentoDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import profile.entity.UtenteEntity;
import utils.IntegrationTestIS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import static org.mockito.Mockito.when;

public class DeleteCommentoIntegrationTest {



    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private DataSource mockDataSource;

    private Connection connection;
    private DataSource datasource;



    private Connection setUp() throws Exception {

        datasource = IntegrationTestIS.getTestDataSource();
        connection = datasource.getConnection();

        connection.setAutoCommit(false); // Disabilita l'auto-commit
        return connection;
    }


    @Test
    public void deleteOwnCommentTest() throws Exception{

        MockitoAnnotations.openMocks(this);



        when(mockDataSource.getConnection()).thenReturn(setUp(), setUp());
//        when(mockDataSource.getConnection()).thenReturn(setUp());
        CommentoDAO dao = new CommentoDAO(mockDataSource);

        //mock user
        UtenteEntity user = new UtenteEntity();
        user.setId(6);




        when(request.getParameter("commentoID")).thenReturn("2");
        when(request.getParameter("postID")).thenReturn("4");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(user);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RemoveCommentoS deleteCommentoS = new RemoveCommentoS();
        deleteCommentoS.setCommentoDAO(dao);

        deleteCommentoS.doPost(request, response);

        assert (stringWriter.toString().contains("{\"outcome\":true}"));


    }
}
