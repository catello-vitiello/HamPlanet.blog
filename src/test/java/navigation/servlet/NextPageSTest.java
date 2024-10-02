
package navigation.servlet;

import navigation.Navigator;
import navigation.Page;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NextPageTest {

    @Test
    public void nextPageTest() throws Exception{
        //REQUEST & RESPONSE
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //SESSION
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        //ATTRIBUTES
        Navigator navigator = mock(Navigator.class);
        when(session.getAttribute("Navigator")).thenReturn(navigator);
        Page page = new Page(1, Page.Type.HOME);
        when(navigator.next()).thenReturn(page);

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        NextPageS nextPage = new NextPageS();
        nextPage.doPost(request,response);

        writer.flush();
        assert(stringWriter.toString().contains("id"));
    }

}
