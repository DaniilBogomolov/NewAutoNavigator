package models;

public class Engine {
    private Long id;
    private EngineType engineType;

    public Engine(EngineType engineType) {
        this.engineType = engineType;
    }

    public Engine(Long id, EngineType engineType) {
        this.id = id;
        this.engineType = engineType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return engineType.toString().toLowerCase();
    }
}
