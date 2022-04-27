/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.webpoker;

import checking.Card;
import checking.ProblemException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class that contains main poker controller
 *
 * @version 5.0
 * @author Jeremiasz Radłowski
 */
@WebServlet(name = "PokerControler", urlPatterns = {"/check"})
public class PokerController extends HttpServlet {

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
            throws ServletException, IOException, ProblemException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        
        try {
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

            
            ArrayList<Card> list = (ArrayList<Card>) context.getAttribute("list"); 
            Card cardOne = (Card) context.getAttribute("cardOne"); 
            List<String> history = (List<String>) context.getAttribute("history"); 
            list.clear();

            if (color1 != null && color2 != null && color3 != null && color4 != null && color5 != null && figure1 != null && figure2 != null && figure3 != null && figure4 != null && figure5 != null) {
                list.add(new Card(color1, figure1));
                list.add(new Card(color2, figure2));
                list.add(new Card(color3, figure3));
                list.add(new Card(color4, figure4));
                list.add(new Card(color5, figure5));
            }
            else {
                throw new ProblemException("input is null");
            }

            //String result = sAcc.cardOne.checkIfInSequence(sAcc.list, sAcc.cardOne.checkIfSameColor(sAcc.list));
            String result = "";
            result = cardOne.checkIfInSequence(list, cardOne.checkIfSameColor(list));
            history.add(result);
            String previousRes = "";
            if (request.getCookies() == null) {
                response.addCookie(new Cookie("previousResult", ""));
            } else {
                for (var cookie : request.getCookies()) {
                    if (cookie.getName().equals("previousResult")) {
                        previousRes = cookie.getValue();
                        break;
                    }
                }
            }

            response.addCookie(new Cookie("previousResult", result));

            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Poker!!!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>You have a " + result + "</h1>");
                if (!previousRes.isBlank()) {
                    out.println("<h3>In last game u had a " + previousRes + "</h3>");
                }
                out.println("</body>");
                out.println("</html>");
            }
        } catch (ProblemException ex) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Poker!!!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Exception:" + ex.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (ProblemException ex) {
            Logger.getLogger(PokerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PokerController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PokerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PokerController.class.getName()).log(Level.SEVERE, null, ex);
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
