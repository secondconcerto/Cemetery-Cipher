/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;

/**
 *
 * @author roza
 */
@WebServlet(name = "EncodeServlet", urlPatterns = {"/EncodeServlet"})
public class EncodeServlet extends HttpServlet {
    private int count;
    private CementaryCipherModel model = new CementaryCipherModel();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TextToEnocde</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TextToEnocde at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          String textToEncode = "";
          
        try {
            response.setContentType("text/html;charset = UTF-8");
            textToEncode = request.getParameter("textToEnocde");
            model.encodeMessage(textToEncode);
            PrintWriter writer = response.getWriter();
            
            String ouput = model.getEncodedValue().replace("\n", "<br/>");
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your ciphered text is:<p> </p>"  + ouput  + "</h2>";
            htmlRespone += "</html>";

            writer.println(htmlRespone);
            model.resetValue();
          
        } catch (WrongInputException ex) {
             Cookie[] cookies = request.getCookies();
            count = 1;
            if (cookies != null) {
                for (Cookie cookie : cookies)
                {
                    if (cookie.getName().equals("EncodeErrorCounter")) {
                        count = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
                }
                Cookie ck = new Cookie( "EncodeErrorCounter" , Integer.toString(++count)); 
                response.addCookie(ck);
            textToEncode = "";
            response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
            

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String textToEncode = "";
        try {
            response.setContentType("text/html;charset = UTF-8");
            textToEncode = request.getParameter("textToEnocde");
            model.encodeMessage(textToEncode);
            PrintWriter writer = response.getWriter();
            
            String ouput = model.getEncodedValue().replace("\n", "<br/>");
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your ciphered text is:<p> </p>"  + ouput  + "</h2>";
            htmlRespone += "</html>";

            writer.println(htmlRespone);
            model.resetValue();
          
        } catch (WrongInputException ex) {
            
             Cookie[] cookies = request.getCookies();
             count = 1;;
            if (cookies != null) {
                for (Cookie cookie : cookies)
                {
                    if (cookie.getName().equals("EncodeErrorCounter")) {
                        count = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
                }
                Cookie ck = new Cookie( "EncodeErrorCounter" , Integer.toString(++count)); 
                response.addCookie(ck);
            textToEncode = "";
            response.sendError(response.SC_BAD_REQUEST, ex.getMessage());

        }
       
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}