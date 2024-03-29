package servlets;

import models.Car;
import services.CarsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

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
            String parameter = parameters.nextElement();
            if (parameter.equals("avg_price") || parameter.equals("year")) {
                String values = req.getParameter(parameter);
                service.addClause(parameter, values);
            } else {
                String[] parameterParts = parameter.split("-");
                service.addClause(parameterParts[0], parameterParts[1]);
            }
        }
        List<Car> cars = service.getCars();
        req.getServletContext().setAttribute("cars", cars);
        service.clearQuery();
        resp.sendRedirect("/home");
    }
}
