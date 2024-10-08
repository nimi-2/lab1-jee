package bd.labo1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {

    // Metoda pomocnicza do obliczeń
    private double oblicz(HttpServletRequest request) {
        // Pobranie parametrów z formularza
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        String operation = request.getParameter("operation");

        // Konwersja z String na double
        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double result = 0;

        // Wykonanie odpowiedniej operacji
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    throw new IllegalArgumentException("Dzielenie przez zero!");
                }
                break;
        }
        return result;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            try {
                // Wywołanie metody oblicz() i pobranie wyniku
                double wynik = oblicz(request);

                // Wyświetlenie wyniku
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Wynik Kalkulatora</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Wynik: " + wynik + "</h1>");
                out.println("<a href='calc.html'>Powrót do kalkulatora</a>");
                out.println("</body>");
                out.println("</html>");
            } catch (NumberFormatException e) {
                out.println("<p>Błąd: nieprawidłowe dane wejściowe. Upewnij się, że wprowadziłeś liczby.</p>");
                out.println("<a href='calc.html'>Powrót do kalkulatora</a>");
            } catch (IllegalArgumentException e) {
                out.println("<p>Błąd: " + e.getMessage() + "</p>");
                out.println("<a href='calc.html'>Powrót do kalkulatora</a>");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Kalkulator serwletowy";
    }
}
