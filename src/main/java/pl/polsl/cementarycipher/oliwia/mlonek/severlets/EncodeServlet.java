
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;

/**
 * Servlet responsible for decoding user message.
 *
 * @author Oliwia Mlonek
 * @version 4.0
 */
@WebServlet(name = "EncodeServlet", urlPatterns = {"/EncodeServlet"})
public class EncodeServlet extends HttpServlet {
    /** Variable to count cookies  */
    private int count;
    /** Variable stores model which performs coding operations  */
    private CementaryCipherModel model = new CementaryCipherModel();
     /** Variable stores history of encoding */
    private List<String> encodeList = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession(true);
         
        String textToEncode = "";
        try {
            response.setContentType("text/html;charset = UTF-8");
            textToEncode = request.getParameter("textToEnocde");
            model.encodeMessage(textToEncode);
           
            
            PrintWriter writer = response.getWriter();
            
            String output = model.getEncodedValue().replace("\n", "<br/>");
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your ciphered text is:<p> </p>"  + output  + "</h2>";
            htmlRespone += "</html>";
            
            encodeList.add(new java.util.Date()+"//"+textToEncode+"//"+output);
            session.setAttribute("encodeList", encodeList);
 
            writer.println(htmlRespone);
             

            model.resetEncodedValue();
          
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
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Performs encoding operation";
    }// </editor-fold>

}
