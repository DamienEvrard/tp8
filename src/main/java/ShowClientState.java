/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author damie
 */
@WebServlet(urlPatterns = {"/ShowClientState"})
public class ShowClientState extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowClient</title>");
            out.println("</head>");
            out.println("<body>");
            try {   
                DAOEXT daoext = new DAOEXT(DataSourceFactory.getDataSource());
                ArrayList<String> val=daoext.getState();
                if (val == null) {
                    throw new Exception("States introuvable");
                }
                // on doit convertir cette valeur en entier (attention aux exceptions !)
                ArrayList<String> state = val;
                
                
                out.println("<form method=\"post\"><select  name=\"state\">");
                for(String sta : state){
                    out.printf("<option value=\"%s\">%s</option>", sta,sta);
                }
                out.println("</select><input type=\"submit\" value=\"Valider\" /></form>");
                
            out.printf("<a href='%s'>Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
    }
    }
    
        
        
    protected void affiche(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowClient</title>");
            out.println("</head>");
            out.println("<body>");
            try {   
                DAOEXT daoext = new DAOEXT(DataSourceFactory.getDataSource());
                ArrayList<String> val=daoext.getState();
                if (val == null) {
                    throw new Exception("States introuvable");
                }
                // on doit convertir cette valeur en entier (attention aux exceptions !)
                ArrayList<String> state = val;
                
                
                out.println("<form method=\"post\"><select  name=\"state\">");
                for(String sta : state){
                    out.printf("<option value=\"%s\">%s</option>", sta,sta);
                }
                out.println("</select><input type=\"submit\" value=\"Valider\"/></form>");
                    
                String stateC = request.getParameter("state");
                
                DAO dao = new DAO(DataSourceFactory.getDataSource());
                List<CustomerEntity> customerList=dao.customersInState(stateC);
                
                if (customerList == null) {
                    throw new Exception("Pays inconnu");
                }
                // Afficher les propriétés du client   
                out.println("<table border=\"1\"><tr><td>ID</td><td>Name</td><td>Adresse</td></tr>");
                for( CustomerEntity customer : customerList){
                out.println("<tr>");
                out.printf("<td>%d</td><td>%s</td><td>%s</td>",
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getAddressLine1());
                out.println("</tr>");
                }
                out.println("</table>");
                
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
            out.printf("<a href='%s'>Retour au menu</a>", request.getContextPath());
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
        affiche(request, response);
        
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
