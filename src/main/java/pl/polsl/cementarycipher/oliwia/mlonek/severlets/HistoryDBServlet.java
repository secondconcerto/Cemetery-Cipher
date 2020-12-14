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

/**
 *
 * @author roza
 */
public class HistoryDBServlet extends HttpServlet {

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
        try {
           PrintWriter out = response.getWriter();
            List<HistoryEntity> operations = new ArrayList<>();
            EntityManager em = (EntityManager) getServletContext().getAttribute("DbCon");
            Manager manager = new Manager();
            operations = manager.selectHistory(em);
            
            if (operations != null){
                out.println("<!doctype html public \"-//w3c//dtd html 4.0 " +"transitional//en\">\n" 
                          + "<html>\n" +"<head><title>" + "Database history" + "</title></head>\n" +

            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + "Database history" + "</h1>\n" +
               "<h2 align = \"center\">History records from databse</h2>\n"
               );
                    
            for(int i = operations.size()-1; i >= 0; i--)


                   out.println("<table border = \"1\"  align = \"center\">\n"+"<tr bgcolor = \"#949494\">\n" +
                      "  <th></th><th></th></tr>\n"+
                   "<tr>\n" +
                      "  <td>Creation Time</td>\n" +
                      "  <td>" + operations.get(i).getNow() + "</td></tr>\n" +

                   "<tr>\n" +
                      "  <td>User Input</td>\n" +
                      "  <td>" + operations.get(i).getOperationsEntity().getUserInput()+ "  </td> </tr>\n" +

                   "<tr>\n" +
                      "  <td>Program Output</td>\n" +
                      "  <td>" + operations.get(i).getOperationsEntity().getUserOuput() + "  </td> </tr>\n" +


                "</table>\n" +
                "</body></html>"
             );
            }
            else{
                PrintWriter writer = response.getWriter();
                String htmlRespone = "<html>";
                htmlRespone += "<h2>Database is empty!</h2>";
                htmlRespone += "</html>";
                writer.println(htmlRespone);
            }
        }
        catch (PersistenceException e) {
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
