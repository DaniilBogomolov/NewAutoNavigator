package models;

public class Car {

    private Long id;
    private Maker maker;
    private Model model;
    private Integer year;
    private Integer avgPrice;
    private Engine engine;
    private Transmission transmission;
    private Integer capacity;
    private CarType type;


    public Car(Long id, Maker maker, Model model, Integer year, Integer avgPrice, Engine engine, Transmission transmission, Integer capacity, CarType type) {
        this (maker, model, year, avgPrice, engine, transmission, capacity, type);
        this.id = id;
    }

    public Car(Maker maker, Model model, Integer year, Integer avgPrice, Engine engine, Transmission transmission, Integer capacity, CarType type) {
        this.maker = maker;
        this.model = model;
        this.year = year;
        this.avgPrice = avgPrice;
        this.engine = engine;
        this.transmission = transmission;
        this.capacity = capacity;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }
}
