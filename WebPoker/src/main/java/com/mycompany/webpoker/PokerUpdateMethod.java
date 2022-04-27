/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.webpoker;

import checking.Card;
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
 * class that contains update method to database
 *
 * @version 5.0
 * @author Jeremiasz Radłowski
 */
@WebServlet(name = "PokerUpdateMethod", urlPatterns = {"/updateHand"})
public class PokerUpdateMethod extends HttpServlet {

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

        String color1 = request.getParameter("Color1");
        String color2 = request.getParameter("Color2");
        String color3 = request.getParameter("Color3");
        String color4 = request.getParameter("Color4");
        String color5 = request.getParameter("Color5");

        String figure1 = request.getParameter("Figure1");
        String figure2 = request.getParameter("Figure2");
        String figure3 = request.getParameter("Figure3");
        String figure4 = request.getParameter("Figure4");
        String figure5 = request.getParameter("Figure5");

        
        if (color1 != null && color2 != null && color3 != null && color4 != null && color5 != null && figure1 != null && figure2 != null && figure3 != null && figure4 != null && figure5 != null) {
            String result = "";
            
            Card c1 = new Card(color1, figure1);
            Card c2 = new Card(color2, figure2);
            Card c3 = new Card(color3, figure3);
            Card c4 = new Card(color4, figure4);
            Card c5 = new Card(color5, figure5);
            Hand hand = new Hand();
            hand.setName(result);
            entityMenager.getTransaction().begin();
            for (var i : listOfHand) {
                if (i.getId().toString().equals(request.getParameter("Id"))) {
                    i.setCard1(c1);
                    i.setCard2(c2);
                    i.setCard3(c3);
                    i.setCard4(c4);
                    i.setCard5(c5);
                    entityMenager.merge(i);
                    break;
                }
            }
            entityMenager.getTransaction().commit();

            response.sendRedirect("/WebPoker/PokerHandDataBase");
            try (PrintWriter out = response.getWriter()) {

            }
        } else {
            throw new ProblemException("input is null");
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
            Logger.getLogger(PokerUpdateMethod.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PokerUpdateMethod.class.getName()).log(Level.SEVERE, null, ex);
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
