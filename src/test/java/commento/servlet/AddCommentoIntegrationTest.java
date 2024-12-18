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

import static org.mockito.Mockito.when;

public class AddCommentoIntegrationTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Test
    public void addCommentTest() throws Exception{

        MockitoAnnotations.openMocks(this);

        DataSource ds = IntegrationTestIS.getConnection();

        CommentoDAO dao = new CommentoDAO(ds);

        //mock user
        UtenteEntity user = new UtenteEntity();
        user.setId(6);




        when(request.getParameter("commento")).thenReturn("test");
        when(request.getParameter("postID")).thenReturn("1");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("profile")).thenReturn(user);

        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        AddCommentoS addCommentoS = new AddCommentoS();
        addCommentoS.setCommentoDAO(dao);

        addCommentoS.doPost(request, response);

        assert (stringWriter.toString().contains("{\"outcome\":true}"));


    }
}
