package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.*;


@WebFilter(filterName = "SecondsFilter", urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "mood", value = "awake")})
public class SecondsFilter implements Filter {

    String mood = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        mood = filterConfig.getInitParameter("mood");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
   request.setCharacterEncoding("UTF-8");
   response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
