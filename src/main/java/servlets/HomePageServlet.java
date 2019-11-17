package servlets;

import freemarker.template.TemplateExceptionHandler;
import helpers.Config;
import models.*;
import repository.implementations.CarsRepositoryImpl;
import repository.implementations.TransmissionsRepositoryImpl;
import repository.interfaces.CarsRepository;
import repository.interfaces.MakersRepository;
import repository.implementations.MakersRepositoryImpl;
import repository.interfaces.TransmissionsRepository;

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
    private TransmissionsRepository transmissionsRepository;

    @Override
    public void init() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
        makersRepository = new MakersRepositoryImpl();
        carsRepository = new CarsRepositoryImpl();
        transmissionsRepository = new TransmissionsRepositoryImpl();
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
        List<CarType> types = carsRepository.getAll().stream().filter(Optional::isPresent).map(Optional::get).map(Car::getType).collect(Collectors.toList());
        List<Transmission> transmissions = transmissionsRepository.getAll().stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
//        System.out.println(makers.toString());
//        System.out.println(types.toString());
        List<Car> cars = getCars();
        root.put("cars", cars);
        root.put("types", types);
        root.put("makers", makers);
        root.put("transmissions", transmissions);
        response.setContentType("text/html");
        Config.render(request, response, "MainPage.ftl", root);
    }


    private List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        Car firstCar = new Car("Chevrolet_Niva.png");
        Car secondCar = new Car("Kia_Rio.png");
        Car thirdCar = new Car("Renault_Duster.png");
        Car forthCar = new Car("Toyota_Camry.png");
        cars.add(firstCar);
        cars.add(secondCar);
        cars.add(thirdCar);
        cars.add(forthCar);
        return cars;
    }
}
