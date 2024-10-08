
package navigation.servlet;

import navigation.Navigator;
import navigation.Page;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PrevPageTest {



    @Test
    public void prevPageTest() throws Exception{
        //REQUEST & RESPONSE
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //SESSION
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        //ATTRIBUTES
        Navigator navigator = new Navigator();
        Page page = new Page(0, Page.Type.HOME);
        navigator.setCurrent(page);
        navigator.save();


        when(session.getAttribute("Navigator")).thenReturn(navigator);

        //WRITER
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        PrevPageS prevPage = new PrevPageS();
        prevPage.doPost(request,response);
        writer.flush();
        assert(stringWriter.toString().contains("id"));
    }

}
