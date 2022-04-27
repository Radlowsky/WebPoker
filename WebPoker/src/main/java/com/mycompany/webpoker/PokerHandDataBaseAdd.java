/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.webpoker;

import checking.Card;
import checking.Hand;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class that contains method that adds to data base
 *
 * @version 5.0
 * @author Jeremiasz Radłowski
 */
@WebServlet(name = "PokerHandDataBaseAdd", urlPatterns = {"/PokerHandDataBaseAdd"})
public class PokerHandDataBaseAdd extends HttpServlet {

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

        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();

        List<checking.Card> listOfCard = new ArrayList<>();
        List<Hand> listOfHand = new ArrayList<>();
        EntityManager entityMenager = (EntityManager) context.getAttribute("entityMenager");;

        entityMenager.getTransaction().begin();
        listOfCard.clear();
        listOfCard.addAll(entityMenager.createQuery("SELECT u FROM Card u", checking.Card.class).getResultList());
        listOfHand.clear();
        listOfHand.addAll(entityMenager.createQuery("SELECT u FROM Hand u", checking.Hand.class).getResultList());
        entityMenager.getTransaction().commit();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PokerHandDataBase</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<td>" + "Name");
            out.println("</td>");
            out.println("<td>" + "Card 1");
            out.println("</td>");
            out.println("<td>" + "Card 2");
            out.println("</td>");
            out.println("<td>" + "Card 3");
            out.println("</td>");
            out.println("<td>" + "Card 4");
            out.println("</td>");
            out.println("<td>" + "Card 5");
            out.println("</td>");
            out.println("</tr>");
            for (var i : listOfHand) {
                out.println("<tr>");
                out.println("<td>" + i.getName());
                out.println("</td>");
                out.println("<td>" + i.getCard1().getColor() + " " + i.getCard1().getFigure());
                out.println("</td>");
                out.println("<td>" + i.getCard2().getColor() + " " + i.getCard2().getFigure());
                out.println("</td>");
                out.println("<td>" + i.getCard3().getColor() + " " + i.getCard3().getFigure());
                out.println("</td>");
                out.println("<td>" + i.getCard4().getColor() + " " + i.getCard4().getFigure());
                out.println("</td>");
                out.println("<td>" + i.getCard5().getColor() + " " + i.getCard5().getFigure());
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<a href=\"/WebPoker/PokerHistory\"><button>Delete</button></a>\n"
                    + "        <a href=\"/WebPoker/PokerHandDataBase\"><button><span style=\"color:green;\">NEW </span>Hand</button></a>");
            out.println("<div>Select your cards</div>\n"
                    + "        <form name=\"checkForm\" action=\"handAdd\">\n"
                    + "            <label>Color1: \n"
                    + "                <select name=\"Color1\">\n"
                    + "                    <option value=\"d\">d</option>\n"
                    + "                    <option value=\"h\">h</option>\n"
                    + "                    <option value=\"c\">c</option>\n"
                    + "                    <option value=\"s\">s</option>\n"
                    + "                </select>\n"
                    + "            </label>\n"
                    + "            <label>Figure1:\n"
                    + "                <select name=\"Figure1\">\n"
                    + "                    <option value=\"1\">1</option>\n"
                    + "                    <option value=\"2\">2</option>\n"
                    + "                    <option value=\"3\">3</option>\n"
                    + "                    <option value=\"4\">4</option>\n"
                    + "                    <option value=\"5\">5</option>\n"
                    + "                    <option value=\"6\">6</option>\n"
                    + "                    <option value=\"7\">7</option>\n"
                    + "                    <option value=\"8\">8</option>\n"
                    + "                    <option value=\"9\">9</option>\n"
                    + "                    <option value=\"0\">0</option>\n"
                    + "                    <option value=\"j\">j</option>\n"
                    + "                    <option value=\"q\">q</option>\n"
                    + "                    <option value=\"k\">k</option>\n"
                    + "                </select>\n"
                    + "            </label><br>\n"
                    + "            <label>Color2: \n"
                    + "                <select name=\"Color2\">\n"
                    + "                    <option value=\"d\">d</option>\n"
                    + "                    <option value=\"h\">h</option>\n"
                    + "                    <option value=\"c\">c</option>\n"
                    + "                    <option value=\"s\">s</option>\n"
                    + "                </select>\n"
                    + "            </label>\n"
                    + "            <label>Figure2:\n"
                    + "                <select name=\"Figure2\">\n"
                    + "                    <option value=\"1\">1</option>\n"
                    + "                    <option value=\"2\">2</option>\n"
                    + "                    <option value=\"3\">3</option>\n"
                    + "                    <option value=\"4\">4</option>\n"
                    + "                    <option value=\"5\">5</option>\n"
                    + "                    <option value=\"6\">6</option>\n"
                    + "                    <option value=\"7\">7</option>\n"
                    + "                    <option value=\"8\">8</option>\n"
                    + "                    <option value=\"9\">9</option>\n"
                    + "                    <option value=\"0\">0</option>\n"
                    + "                    <option value=\"j\">j</option>\n"
                    + "                    <option value=\"q\">q</option>\n"
                    + "                    <option value=\"k\">k</option>\n"
                    + "                </select>\n"
                    + "            </label><br>\n"
                    + "            <label>Color3: \n"
                    + "                <select name=\"Color3\">\n"
                    + "                    <option value=\"d\">d</option>\n"
                    + "                    <option value=\"h\">h</option>\n"
                    + "                    <option value=\"c\">c</option>\n"
                    + "                    <option value=\"s\">s</option>\n"
                    + "                </select>\n"
                    + "            </label>\n"
                    + "            <label>Figure3:\n"
                    + "                <select name=\"Figure3\">\n"
                    + "                    <option value=\"1\">1</option>\n"
                    + "                    <option value=\"2\">2</option>\n"
                    + "                    <option value=\"3\">3</option>\n"
                    + "                    <option value=\"4\">4</option>\n"
                    + "                    <option value=\"5\">5</option>\n"
                    + "                    <option value=\"6\">6</option>\n"
                    + "                    <option value=\"7\">7</option>\n"
                    + "                    <option value=\"8\">8</option>\n"
                    + "                    <option value=\"9\">9</option>\n"
                    + "                    <option value=\"0\">0</option>\n"
                    + "                    <option value=\"j\">j</option>\n"
                    + "                    <option value=\"q\">q</option>\n"
                    + "                    <option value=\"k\">k</option>\n"
                    + "                </select>\n"
                    + "            </label><br>\n"
                    + "            <label>Color4: \n"
                    + "                <select name=\"Color4\">\n"
                    + "                    <option value=\"d\">d</option>\n"
                    + "                    <option value=\"h\">h</option>\n"
                    + "                    <option value=\"c\">c</option>\n"
                    + "                    <option value=\"s\">s</option>\n"
                    + "                </select>\n"
                    + "            </label>\n"
                    + "            <label>Figure4:\n"
                    + "                <select name=\"Figure4\">\n"
                    + "                    <option value=\"1\">1</option>\n"
                    + "                    <option value=\"2\">2</option>\n"
                    + "                    <option value=\"3\">3</option>\n"
                    + "                    <option value=\"4\">4</option>\n"
                    + "                    <option value=\"5\">5</option>\n"
                    + "                    <option value=\"6\">6</option>\n"
                    + "                    <option value=\"7\">7</option>\n"
                    + "                    <option value=\"8\">8</option>\n"
                    + "                    <option value=\"9\">9</option>\n"
                    + "                    <option value=\"0\">0</option>\n"
                    + "                    <option value=\"j\">j</option>\n"
                    + "                    <option value=\"q\">q</option>\n"
                    + "                    <option value=\"k\">k</option>\n"
                    + "                </select>\n"
                    + "            </label><br>\n"
                    + "            <label>Color5: \n"
                    + "                <select name=\"Color5\">\n"
                    + "                    <option value=\"d\">d</option>\n"
                    + "                    <option value=\"h\">h</option>\n"
                    + "                    <option value=\"c\">c</option>\n"
                    + "                    <option value=\"s\">s</option>\n"
                    + "                </select>\n"
                    + "            </label>\n"
                    + "            <label>Figure5:\n"
                    + "                <select name=\"Figure5\">\n"
                    + "                    <option value=\"1\">1</option>\n"
                    + "                    <option value=\"2\">2</option>\n"
                    + "                    <option value=\"3\">3</option>\n"
                    + "                    <option value=\"4\">4</option>\n"
                    + "                    <option value=\"5\">5</option>\n"
                    + "                    <option value=\"6\">6</option>\n"
                    + "                    <option value=\"7\">7</option>\n"
                    + "                    <option value=\"8\">8</option>\n"
                    + "                    <option value=\"9\">9</option>\n"
                    + "                    <option value=\"0\">0</option>\n"
                    + "                    <option value=\"j\">j</option>\n"
                    + "                    <option value=\"q\">q</option>\n"
                    + "                    <option value=\"k\">k</option>\n"
                    + "                </select>\n"
                    + "            </label><br>\n"
                    + "            <input type=\"submit\" value=\"ADD!\" name=\"handAdd\" />\n"
                    + "        </form>");

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
