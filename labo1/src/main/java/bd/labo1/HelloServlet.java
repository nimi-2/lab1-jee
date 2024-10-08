/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package bd.labo1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emilia
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"})
public class HelloServlet extends HttpServlet {

    // Deklaracja pola data1 typu Date
    private Date data1;

    // Nadpisanie metody init(), aby zainicjalizować wartość data1
    @Override
    public void init() {
        data1 = new Date();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Tworzenie formatu daty yyyy-MM-dd
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>data1 z init(): " + dateFormat.format(data1) + "</p>");
            out.println("<p>data z processRequest: " + dateFormat.format(new Date()) + "</p>");
            out.println("<h2>Dane serwera</h2>");
            out.println("<p>request.getServerName(): " + request.getServerName() + "</p>");
            out.println("<p>request.getServerPort(): " + request.getServerPort() + "</p>");
            out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() + "</p>");
            out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");
            out.println("<h2>Szczegóły żądania</h2>");
            out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
            out.println("<p>request.getQueryString(): " + request.getQueryString() + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
