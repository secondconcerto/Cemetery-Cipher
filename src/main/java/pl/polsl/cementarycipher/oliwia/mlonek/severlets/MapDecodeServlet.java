/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.severlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;

/**
 * Servlet downloads and prepares data to be dedoded.
 *
 * @author Oliwia Mlonek
 * @version 4.0
 */
@WebServlet(name = "MapDecodeServlet", urlPatterns = {"/MapDecodeServlet"})
public class MapDecodeServlet extends HttpServlet {

     /** Variable stores model which performs coding operations  */
    private CementaryCipherModel model = new CementaryCipherModel();
    /** Variable stores model which holds decoding alphabet */
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
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
      
        TreeMap<String, String> sorted = new TreeMap<>(decodeModel.getMap()); 
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
        String key = entry.getKey();
        String value = entry.getValue();
        String value2 = value.replace("\n", "<br/>");
        String value3 = value2.replace(" ", "\u00A0");
        String htmlRespone = "<html> <table><tr> ";
        htmlRespone += "<th>" +value3+ "<th/><br/>";
        htmlRespone +="<th>" +key+ "<th/>";
        htmlRespone += " </tr></table></html>";
        PrintWriter writer = response.getWriter();
        writer.println(htmlRespone);
        

        }
        PrintWriter out = response.getWriter();
        
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
        return "Displays decoding map and get user input";
    }// </editor-fold>

}
