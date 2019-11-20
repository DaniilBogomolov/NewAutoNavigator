package servlets;

import freemarker.template.TemplateExceptionHandler;
import helpers.Config;
import models.Car;
import models.CarBuilder;
import models.Model;
import models.User;
import services.CarsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/cars/*")
public class CarPageServlet extends HttpServlet {

    private CarsService carsService;
    private static final String REGEX_FOR_CAR_ID = ".*\\/([0-9]+)";

    @Override
    public void init() throws ServletException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
        carsService = new CarsService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        String userName = (String) request.getAttribute("user");
        if (userName != null) {
            root.put("user", new User(userName));
        }
        Pattern pattern = Pattern.compile(REGEX_FOR_CAR_ID);
        Matcher matcher = pattern.matcher(request.getRequestURI());
        Long id = null;
        if (matcher.find()) {
            id = Long.parseLong(matcher.group(1));
        }
        Car car = carsService.getFullCarInfoById(id);
        System.out.println(car);
        root.put("car", car);
        response.setContentType("text/html");
        Config.render(request, response, "CarPage.ftl", root);
    }
}
