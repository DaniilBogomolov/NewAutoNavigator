package models;

public class CarBuilder {
    private Long id;
    private Maker maker;
    private Model model;
    private Integer year;
    private Integer avgPrice;
    private Engine engine;
    private Transmission transmission;
    private Integer capacity;
    private CarType type;
    private String imagePath;

    public CarBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CarBuilder setMaker(Maker maker) {
        this.maker = maker;
        return this;
    }

    public CarBuilder setModel(Model model) {
        this.model = model;
        return this;
    }

    public CarBuilder setYear(Integer year) {
        this.year = year;
        return this;
    }

    public CarBuilder setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public CarBuilder setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public CarBuilder setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public CarBuilder setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public CarBuilder setType(CarType type) {
        this.type = type;
        return this;
    }

    public CarBuilder setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public Car createCar() {
        return new Car(id, maker, model, year, avgPrice, engine, transmission, capacity, type, imagePath);
    }
}