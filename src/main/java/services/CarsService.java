package services;

import repository.implementations.CarsRepositoryImpl;
import repository.interfaces.CarsRepository;

public class CarsService {


    private String carsQuery = "select * from cars";
    private boolean noClauses = true;
    private String lastClause = "";

    private CarsRepository carsRepository;

    public CarsService() {
        carsRepository = new CarsRepositoryImpl();
    }


    public void addClause(String type, String value) {
        StringBuilder builder = new StringBuilder(carsQuery);
        if (noClauses) {
            builder.append(" where (");
            noClauses = false;
            lastClause = type;
        } else {
            if (lastClause.equals(type)) {
                builder.append(" or ");
            } else {
                builder.append(") and ");
                lastClause = type;
            }
        }
        builder.append(type).append(" = ").append(value);
        carsQuery = builder.toString();
    }


    public String getCarsQuery() {
        return carsQuery;
    }

    public void setCarsQuery(String carsQuery) {
        this.carsQuery = carsQuery;
    }
}
