package models;

public class Transmission {
    private Long id;
    private TransmissionType transmissionType;
    private String name;

    public Transmission(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
        this.name = transmissionType.toString();
    }

    public Transmission(Long id, TransmissionType transmissionType) {
        this.id = id;
        this.transmissionType = transmissionType;
        this.name = transmissionType.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
