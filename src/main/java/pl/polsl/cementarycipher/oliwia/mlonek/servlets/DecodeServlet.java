
package pl.polsl.cementarycipher.oliwia.mlonek.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;

/**
 * Servlet responsible for decoding user message.
 *
 * @author Oliwia Mlonek
 * @version 5.0
 */
@WebServlet(name = "DecodeServlet", urlPatterns = {"/DecodeServlet"})
public class DecodeServlet extends HttpServlet {

     /** Variable to count cookies  */
    private int count;
    /** Variable stores model which performs coding operations  */
    private CementaryCipherModel model = new CementaryCipherModel();
     /** Variable stores model which holds decoding alphabet */
    private DecodeAlphabetModel decodeModel = new DecodeAlphabetModel();
      /** Variable stores history of decoding */
    private List<String> decodeList = new ArrayList<>();


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
         
        String output = "";
        String textToDecode = "";
        int cookieCount = 0;
        
        try {
            response.setContentType("text/html;charset = UTF-8");
            textToDecode = request.getParameter("textToDecode");
            List<String> numbersList =  new ArrayList<String>(Arrays.asList(textToDecode.split(",")));
            List<String>  userInputNumbers =  new ArrayList<String>();
            String temp = "";    
            for (String listElement : numbersList) 
            {
                
                if(listElement.isBlank() == true)
                    userInputNumbers.add(" ");
                else {
                    temp = listElement.replaceAll("\\s", "");
                    model.checkInput(temp);
                    userInputNumbers.add(decodeModel.getMap().get(temp));
                }
                
            }
            
            
            PrintWriter writer = response.getWriter();
            output = model.decodeMessage(userInputNumbers);
            Manager manager = new Manager();
            EntityManager em = (EntityManager) getServletContext().getAttribute("DbCon");
            manager.addRecord(textToDecode, output, em);
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your ciphered text is:<p> </p>"  + output + "</h2>";
            htmlRespone += "</html>";
            decodeList.add(new java.util.Date()+"//"+textToDecode+"//"+output);
            session.setAttribute("decodeList", decodeList);
 

            writer.println(htmlRespone);
            model.resetDecodedValue();
            
 
            numbersList.clear();
            userInputNumbers.clear();

           
        } catch (WrongInputException ex) {
           Cookie[] cookies = request.getCookies();
        count = 1;
        if (cookies != null) {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals("DecodeErrorCounter")) {
                    count = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
            }
            Cookie ck = new Cookie( "DecodeErrorCounter" , Integer.toString(++count)); 
            response.addCookie(ck);
            textToDecode = "";
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            model.resetDecodedValue();
        }
        catch (PersistenceException e) {
                 response.sendError(response.SC_INTERNAL_SERVER_ERROR , e.getMessage());
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
        return "Performs decoding operation";
    }// </editor-fold>

}
