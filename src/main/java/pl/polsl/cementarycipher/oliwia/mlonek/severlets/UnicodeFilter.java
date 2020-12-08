package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.*;


/**
 * Filter responsible for formatting of request body 
 * or header before sending it to servlet.
 *
 * @author Oliwia Mlonek
 * @version 4.0
 */
@WebFilter(filterName = "SecondsFilter", urlPatterns = {"/*"})
public class UnicodeFilter implements Filter {



    /**
     * Initialize any resources.
     *
     * @param filterConfig  provide init parameters and servlet context object to the Filter
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    /**
     * Apply filter to a resource.
     *
     * @param request servlet request
     * @param response servlet response
     * @param chain used to invoke the next filter in the chain
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
   request.setCharacterEncoding("UTF-8");
   response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    /**
     * Close any resources opened by filter.
     */
    @Override
    public void destroy() {
    }
}
