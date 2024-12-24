package profile.servlet;

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

import static org.mockito.Mockito.when;

public class GetProfileSTest {

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


    private GetProfileS getProfileS;


    @BeforeEach
    public void setUp()throws Exception {
        MockitoAnnotations.openMocks(this);

        getProfileS = new GetProfileS();

        when(request.getParameter("pageId")).thenReturn("1");
        when(request.getParameter("new_page")).thenReturn("false");

    }


    @Test
    public void testGetProfileS() throws Exception {

        //profilo fittizio
        UtenteEntity user_test = new UtenteEntity();
        user_test.setId(5);
        user_test.setRuolo(UtenteEntity.Role.utente);
        user_test.setEmail("test@test.com");
        user_test.setUserName("test");


        when(request.getSession(false)).thenReturn(session);

        when(session.getAttribute("profile")).thenReturn(user_test);



        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        getProfileS.doPost(request, response);

        assert (stringWriter).toString().contains("user");


    }

}