package servlets;

import freemarker.template.TemplateExceptionHandler;
import helpers.Config;
import models.*;
import repository.implementations.*;
import repository.interfaces.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/admin/manage/addcar")
public class AdminAddCarServlet extends HttpServlet {


    private CarsRepository carsRepository;
    private EnginesRepository enginesRepository;
    private MakersRepository makersRepository;
    private ModelsRepository modelsRepository;
    private TransmissionsRepository transmissionsRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();

        if (req.getSession().getAttribute("user") != null && req.getSession().getAttribute("user").equals("adminUser")) {
            root.put("user", new User("admin", "admin", Role.ADMIN));
        }
        Config.render(req, resp, "AdminAddCar.ftl", root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maker = req.getParameter("maker");
        String model = req.getParameter("model");
        Integer year = Integer.parseInt(req.getParameter("year"));
        Integer avgPrice = Integer.parseInt(req.getParameter("avgPrice"));
        String engine = req.getParameter("engine").toUpperCase();
        String transmission = req.getParameter("transmission").toUpperCase();
        Integer capacity = Integer.parseInt(req.getParameter("capacity"));
        String type = req.getParameter("type").toUpperCase();

        enginesRepository.save(new Engine(EngineType.valueOf(engine)));
        makersRepository.save(new Maker(maker));
        modelsRepository.save(new Model(model));
        transmissionsRepository.save(new Transmission(TransmissionType.valueOf(transmission)));

        Engine carEngine = enginesRepository.getEngineByType(EngineType.valueOf(engine));
        Transmission carTransmission = transmissionsRepository.getTransmissionByType(TransmissionType.valueOf(transmission));
        Maker carMaker = makersRepository.getMakerByName(maker);
        Model carModel = modelsRepository.getModelByName(model);

        Car car = new CarBuilder()
                .setMaker(carMaker)
                .setModel(carModel)
                .setYear(year)
                .setAvgPrice(avgPrice)
                .setEngine(carEngine)
                .setTransmission(carTransmission)
                .setCapacity(capacity)
                .setType(CarType.valueOf(type))
                .createCar();
        carsRepository.save(car);
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(this.getServletContext(), "/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        getServletContext().setAttribute("cfg", cfg);
        carsRepository = new CarsRepositoryImpl();
        enginesRepository = new EnginesRepositoryImpl();
        makersRepository = new MakersRepositoryImpl();
        modelsRepository = new ModelsRepositoryImpl();
        transmissionsRepository = new TransmissionsRepositoryImpl();
    }
}
