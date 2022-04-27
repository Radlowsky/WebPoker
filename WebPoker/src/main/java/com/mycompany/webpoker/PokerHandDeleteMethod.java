/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.webpoker;


import checking.Hand;
import checking.ProblemException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class that contains delete method
 *
 * @version 5.0
 * @author Jeremiasz Radłowski
 */
@WebServlet(name = "PokerHandDeleteMethod", urlPatterns = {"/deleteHand"})
public class PokerHandDeleteMethod extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws checking.ProblemException throws problem exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ProblemException {
        response.setContentType("text/html;charset=UTF-8");
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();

        List<checking.Card> listOfCard = new ArrayList<>();
        List<Hand> listOfHand = new ArrayList<>();
        EntityManager entityMenager = (EntityManager) context.getAttribute("entityMenager");

        entityMenager.getTransaction().begin();
        listOfCard.clear();
        listOfCard.addAll(entityMenager.createQuery("SELECT u FROM Card u", checking.Card.class).getResultList());
        listOfHand.clear();
        listOfHand.addAll(entityMenager.createQuery("SELECT u FROM Hand u", checking.Hand.class).getResultList());
        entityMenager.getTransaction().commit();

        entityMenager.getTransaction().begin();
        for (var i : listOfCard){
            entityMenager.persist(i);
        }
        for (var i : listOfHand){
            entityMenager.persist(i);
        }
        entityMenager.getTransaction().commit();
            entityMenager.getTransaction().begin();
            for (var i : listOfHand) {
                if (i.getId().toString().equals(request.getParameter("Id"))) {
                    entityMenager.remove(i);
                    break;
                }
            }
            entityMenager.getTransaction().commit();

            response.sendRedirect("/WebPoker/PokerHandDataBase");
            try (PrintWriter out = response.getWriter()) {

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
        try {
            processRequest(request, response);
        } catch (ProblemException ex) {
            Logger.getLogger(PokerHandDeleteMethod.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ProblemException ex) {
            Logger.getLogger(PokerHandDeleteMethod.class.getName()).log(Level.SEVERE, null, ex);
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
