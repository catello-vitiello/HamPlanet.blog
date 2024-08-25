package profile.servlet;

import navigation.Navigator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetProfileSTest {

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
    void setUp()throws Exception {
        MockitoAnnotations.openMocks(this);

        getProfileS = new GetProfileS();

        when(request.getParameter("pageId")).thenReturn("1");
        when(request.getParameter("new_page")).thenReturn("false");

    }


    @Test
    void testGetProfileS() throws Exception {

        //profilo fittizio
        UtenteEntity user_test = new UtenteEntity();
        user_test.setId(5);
        user_test.setRuolo(UtenteEntity.Role.utente);
        user_test.setEmail("test@test.com");
        user_test.setUserName("test");

        Navigator navigator = new Navigator();

        when(request.getSession(false)).thenReturn(session);

        when(session.getAttribute("profile")).thenReturn(user_test);
        when(session.getAttribute("Navigator")).thenReturn(navigator);



        //Writer
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        getProfileS.doPost(request, response);

        assert(navigator.getCurrent().getId() == 1);
        assert (stringWriter).toString().contains("user");


    }

}