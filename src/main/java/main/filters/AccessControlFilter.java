package main.filters;

import profile.entity.UtenteEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {

    public AccessControlFilter() {
        super();

    }

    public void init(FilterConfig fConfig) throws ServletException {

    }


    public void destroy() {

    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();


        UtenteEntity user = (UtenteEntity) session.getAttribute("profile");
        String pathString = httpServletRequest.getServletPath();
        pathString = pathString.toLowerCase();

        if (user==null && !(pathString.contains("/login") || pathString.contains("/signup"))){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
            return;

        }



        chain.doFilter(request, response);
    }

}
