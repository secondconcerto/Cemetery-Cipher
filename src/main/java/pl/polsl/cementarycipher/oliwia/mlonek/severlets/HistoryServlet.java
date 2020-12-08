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
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author roza
 */
public class HistoryServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
       // Date createTime = new Date(session.getCreationTime());
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      List<String> encodeHistory = (List<String>) session.getAttribute("encodeList");
      List<String> decodeHistory = (List<String>) session.getAttribute("decodeList");
        if (encodeHistory == null && decodeHistory == null) {
            PrintWriter writer = response.getWriter();
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your history is empty!</h2>";
            htmlRespone += "</html>";
            writer.println(htmlRespone);
        }
        else{
           
            out.println("<!doctype html public \"-//w3c//dtd html 4.0 " +"transitional//en\">\n" 
                          + "<html>\n" +"<head><title>" + "History" + "</title></head>\n" +

            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + "History" + "</h1>\n" +
               "<h2 align = \"center\">Session Infomation</h2>\n"
               );
            if (encodeHistory != null){

                for (String listElement : encodeHistory) 
                {
                    List<String> numbersList =  new ArrayList<String>(Arrays.asList(listElement.split("//")));


                      out.println("<table style=\"float: left; margin-right:10px; margin-left:400px\" border = \"1\"  align = \"center\">\n"+"<tr bgcolor = \"#949494\">\n" +
                         "  <th></th><th></th></tr>\n"+
                      "<tr>\n" +
                         "  <td>Creation Time</td>\n" +
                         "  <td>" + numbersList.get(0) + "</td></tr>\n" +

                      "<tr>\n" +
                         "  <td>User Input</td>\n" +
                         "  <td>" + numbersList.get(1) + "  </td> </tr>\n" +

                      "<tr>\n" +
                         "  <td>Program Output</td>\n" +
                         "  <td>" + numbersList.get(2) + "  </td> </tr>\n" +

                   "</table>\n" +
                "</body></html>"
                );
                }
            }
             if (decodeHistory != null){
                  
               for (String listElement : decodeHistory) 
               {
                   List<String> numbersList =  new ArrayList<String>(Arrays.asList(listElement.split("//")));


                     out.println("<table style=\"float: left\" border = \"1\" align = \"center\">\n"+"<tr bgcolor = \"#949494\">\n" +
                        "  <th></th><th></th></tr>\n"+
                     "<tr>\n" +
                        "  <td>Creation Time</td>\n" +
                        "  <td>" + numbersList.get(0) + "</td></tr>\n" +

                     "<tr>\n" +
                        "  <td>User Input</td>\n" +
                        "  <td>" + numbersList.get(1) + "  </td> </tr>\n" +

                     "<tr>\n" +
                        "  <td>Program Output</td>\n" +
                        "  <td>" + numbersList.get(2) + "  </td> </tr>\n" +

                  "</table>\n" +
               "</body></html>"
               );
            }
            
        }
          
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        return "Short description";
    }// </editor-fold>

}
