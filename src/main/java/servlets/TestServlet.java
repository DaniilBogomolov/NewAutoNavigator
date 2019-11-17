package servlets;

import services.CarsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private CarsService service;

    @Override
    public void init() throws ServletException {
        service = new CarsService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/home").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parameters = req.getParameterNames();
        while (parameters.hasMoreElements()) {
            String[] parameterParts = parameters.nextElement().split("-");
            service.addClause(parameterParts[0], parameterParts[1]);
        }
        System.out.println(service.getCarsQuery());
        resp.sendRedirect("/home");
    }
}
