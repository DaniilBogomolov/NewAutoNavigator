package services;

import models.Car;
import repository.implementations.CarsRepositoryImpl;
import repository.interfaces.CarsRepository;

import java.util.List;

public class CarsService {


    private String carsQuery = "select * from car";
    private boolean noClauses = true;
    private String lastClause = "";
    private int openBrackets = 0;
    private int closedBrackets = 0;

    private CarsRepository carsRepository;

    public CarsService() {
        carsRepository = new CarsRepositoryImpl();
    }


    public void addClause(String type, String value) {
        StringBuilder builder = new StringBuilder(carsQuery);
        if (noClauses) {
            builder.append(" where (");
            openBrackets++;
            noClauses = false;
            lastClause = type;
        } else {
            if (lastClause.equals(type)) {
                builder.append(" or ");
            } else {
                builder.append(") and (");
                openBrackets++;
                closedBrackets++;
                lastClause = type;
            }
        }
        builder.append(type).append(" = ").append(value);
        carsQuery = builder.toString();
    }


    public List<Car> getCars() {
        if (openBrackets != closedBrackets) {
            System.out.println(carsQuery + ") limit 4");
            return carsRepository.getCarsWithCustomScript(carsQuery + ") limit 4");
        } else {
            System.out.println(carsQuery);
            return carsRepository.getCarsWithCustomScript(carsQuery);
        }
    }

    public String getCarsQuery() {
        return carsQuery;
    }

    public void clearQuery() {
        carsQuery = "select * from car";
        openBrackets = 0;
        closedBrackets = 0;
        lastClause = "";
        noClauses = true;
    }
}
