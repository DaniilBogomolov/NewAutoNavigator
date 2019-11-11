package servlets;

import freemarker.template.TemplateExceptionHandler;
import helpers.Config;
import models.Car;
import models.CarType;
import models.Maker;
import models.User;
import repository.implementations.CarsRepositoryImpl;
import repository.interfaces.CarsRepository;
import repository.interfaces.MakersRepository;
import repository.implementations.MakersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomePageServlet extends HttpServlet {


    private MakersRepository makersRepository;
    private CarsRepository carsRepository;

    @Override
    public void init() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
        makersRepository = new MakersRepositoryImpl();
        carsRepository = new CarsRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("cars", null);
        String userName = (String) request.getAttribute("user");
        if (userName != null) {
            root.put("user", new User(userName));
        }
        List<Maker> makers = makersRepository.getAll().stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
//        List<CarType> types = carsRepository.getAll().stream().filter(Optional::isPresent).map(Optional::get).map(Car::getType).collect(Collectors.toList());
//        root.put("types", types);
        root.put("makers", makers);
        response.setContentType("text/html");
        Config.render(request, response, "MainPage.ftl", root);
    }
}
