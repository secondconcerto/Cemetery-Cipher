/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;

/**
 *
 * @author roza
 */
@WebServlet(name = "DecodeServlet", urlPatterns = {"/DecodeServlet"})
public class DecodeServlet extends HttpServlet {

    private int count;
    private CementaryCipherModel model = new CementaryCipherModel();
    private DecodeAlphabetModel decodeModel = new DecodeAlphabetModel();


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
            out.println("<title>Servlet TextToDecode</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TextToDecode at " + request.getContextPath() + "</h1>");
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
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your ciphered text is:<p> </p>"  + output + "</h2>";
            htmlRespone += "</html>";

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
        String output = "";
        String textToDecode = "";
        
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
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your ciphered text is:<p> </p>"  + output + "</h2>";
            htmlRespone += "</html>";

            writer.println(htmlRespone);
            model.resetDecodedValue();
            
            temp = "";  
            output = "";
            textToDecode = "";
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
                response.addCookie(ck);response.addCookie(ck);
                textToDecode = "";
                response.sendError(response.SC_BAD_REQUEST, ex.getMessage());
                model.resetDecodedValue();
  


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
