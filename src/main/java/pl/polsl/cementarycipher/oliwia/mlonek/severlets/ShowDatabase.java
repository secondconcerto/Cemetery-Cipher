/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.cementarycipher.oliwia.mlonek.model.HistoryEntity;
import pl.polsl.cementarycipher.oliwia.mlonek.model.OperationsEntity;

/**
 * Servlet responsible for managing and displaying history of operations.
 *
 * @author Oliwia Mlonek
 * @version 4.0
 */
public class ShowDatabase extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try{
        List<HistoryEntity> histories = new ArrayList<>();
        EntityManager em = (EntityManager) getServletContext().getAttribute("DbCon");
        Manager manager = new Manager();
        histories = manager.selectHistory(em);

        List<OperationsEntity> operations = new ArrayList<>();
        EntityManager em2 = (EntityManager) getServletContext().getAttribute("DbCon");
        Manager manager2 = new Manager();
        operations = manager2.selectOperations(em);
        
        PrintWriter out = response.getWriter();
        

      
        if (operations == null && histories == null) {
            PrintWriter writer = response.getWriter();
            String htmlRespone = "<html>";
            htmlRespone += "<h2>Your history is empty!</h2>";
            htmlRespone += "</html>";
            writer.println(htmlRespone);
        }
        else{
            out.println("<!doctype html public \"-//w3c//dtd html 4.0 " +"transitional//en\">\n" 
                          + "<html>\n" +"<head><title>" + "Database" + "</title></head>\n" +

            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + "Database" + "</h1>\n" +
               "<h2 align = \"center\">All records</h2>\n"
               );
            if (operations.size() > 0){
                out.println("<table style=\"float: left; margin-right:10px; margin-left:400px\" border = \"1\"  align = \"center\">\n"+"<tr bgcolor = \"#949494\">\n" +
                         " <caption>Operations Entities Table</caption>  <th></th><th></th></tr>\n");

                for (OperationsEntity listElement : operations) 
                {
                   
                     
                     out.println(  "<tr>\n" +
                         "  <td>User input</td>\n" +
                         "  <td>" + listElement.getUserInput() + "</td></tr>\n" +

                      "<tr>\n" +
                         "  <td>Program Output</td>\n" +
                         "  <td>" + listElement.getUserOuput()+ "</td> </tr>\n" +
                                 
                    "<tr>\n" +
                         "<td> ID of record</td>\n" +
                         "<td>" + listElement.getId() + "</td> </tr>\n");

                      "<tr>\n" +
                         "<td> Key to history</td>\n" +
                         "<td>" + listElement.getHistoryEntity().getId() + "</td> </tr>\n");
                }
                
                out.println(
                 "</table>\n" + "</body></html>");
            }
             if (histories.size()>0){
                  out.println("<table  style=\"float: left\" border = \"1\" align = \"center\">\n"+"<tr bgcolor = \"#949494\">\n" +
                        " <caption>Histories Entities Table</caption> <th></th><th></th></tr>\n");
                  
               for (HistoryEntity listElement : histories) 
               {
                  
                    out.println(  "<tr>\n" +
                        "  <td>Creation Time</td>\n" +
                        "  <td>" + listElement.getNow() + "</td></tr>\n" +

                     "<tr>\n" +
                        "  <td>ID of record</td>\n" +
                        "  <td>" + listElement.getId() + "  </td> </tr>\n"+
                    
                    "<tr>\n" +
                         "<td> Key to operation</td>\n" +
                         "<td>" + listElement.getOperationsEntity().getId() + "</td> </tr>\n");
                }

            } 
               out.println(
                 "</table>\n" + "</body></html>");
        }   
        }catch (PersistenceException e) {
                 response.sendError(response.SC_CONFLICT, e.getMessage());
        }
        catch (Exception e) {
                 response.sendError(response.SC_CONFLICT, e.getMessage());
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
        return "Manages history of operations";
    }// </editor-fold>

}
