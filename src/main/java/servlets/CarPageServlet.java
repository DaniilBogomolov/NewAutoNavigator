package servlets;

import freemarker.template.TemplateExceptionHandler;
import helpers.Config;
import models.Car;
import models.CarBuilder;
import models.Model;
import models.User;
import repository.implementations.UsersRepositoryImpl;
import repository.interfaces.UsersRepository;
import services.CarsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/cars/*")
public class CarPageServlet extends HttpServlet {

    private UsersRepository usersRepository;
    private CarsService carsService;
    private static final String REGEX_FOR_CAR_ID = ".*\\/([0-9]+)";

    @Override
    public void init() throws ServletException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
        carsService = new CarsService();
        usersRepository = new UsersRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pattern pattern = Pattern.compile(REGEX_FOR_CAR_ID);
        Matcher matcher = pattern.matcher(request.getRequestURI());
        Long id = null;
        if (matcher.find()) {
            id = Long.parseLong(matcher.group(1));
        }
        Map<String, Object> root = new HashMap<>();
        String userName = (String) request.getAttribute("user");
        if (userName != null) {
            root.put("user", new User(userName));
            List<Car> favouriteCars = usersRepository.getUserByUsername(userName).get().getFavouriteCars();
            boolean isInFavourite = favouriteCars.stream()
                    .map(Car::getId).collect(Collectors.toList()).contains(id);
            if (isInFavourite) {
                root.put("src", "../images/star-solid.svg");
            } else {
                root.put("src", "../images/star-regular.svg");
            }
            root.put("favourite_cars", favouriteCars);
        }
        Car car = carsService.getFullCarInfoById(id);
        root.put("car", car);

        response.setContentType("text/html");
        Config.render(request, response, "CarPage.ftl", root);
    }
}
