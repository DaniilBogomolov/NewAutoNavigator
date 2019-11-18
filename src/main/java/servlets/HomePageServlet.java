package servlets;

import freemarker.template.TemplateExceptionHandler;
import helpers.Config;
import models.*;
import repository.implementations.CarsRepositoryImpl;
import repository.implementations.EnginesRepositoryImpl;
import repository.implementations.TransmissionsRepositoryImpl;
import repository.interfaces.CarsRepository;
import repository.interfaces.EnginesRepository;
import repository.interfaces.MakersRepository;
import repository.implementations.MakersRepositoryImpl;
import repository.interfaces.TransmissionsRepository;
import services.CarsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@WebServlet("/home")
public class HomePageServlet extends HttpServlet {


    private MakersRepository makersRepository;
    private CarsRepository carsRepository;
    private TransmissionsRepository transmissionsRepository;
    private CarsService carsService;
    private EnginesRepository enginesRepository;

    @Override
    public void init() {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
        makersRepository = new MakersRepositoryImpl();
        carsRepository = new CarsRepositoryImpl();
        carsService = new CarsService();
        transmissionsRepository = new TransmissionsRepositoryImpl();
        enginesRepository = new EnginesRepositoryImpl();
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
        List<CarType> types = Stream.of(CarType.SEDAN, CarType.CROSSOVER, CarType.CONVERTIBLE, CarType.COUPE, CarType.VAN, CarType.WAGON, CarType.TRUCK, CarType.HATCHBACK)
                .collect(Collectors.toList());
        List<Transmission> transmissions = transmissionsRepository.getAll().stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        List<Integer> years = IntStream.range(2002, 2020).boxed().collect(Collectors.toList());
        List<Integer> capacity = IntStream.of(2, 4, 5, 6, 7).boxed().collect(Collectors.toList());
        List<Engine> engines = enginesRepository.getAll().stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
//        System.out.println(makers.toString());
//        System.out.println(types.toString());
        if (request.getServletContext().getAttribute("cars") != null) {
            List<Car> cars = (List<Car>) request.getServletContext().getAttribute("cars");
            root.put("cars", cars);
        } else {
//            List<Car> cars =
        }
        root.put("engines", engines);
        root.put("capacities", capacity);
        root.put("years", years);
        root.put("types", types);
        root.put("makers", makers);
        root.put("transmissions", transmissions);
        response.setContentType("text/html");
        Config.render(request, response, "MainPage.ftl", root);
    }


}
