package model;

public class Vehicle {
    private String id;
    private String type; // Bicicleta ou Trotinete
    private String status; // Disponível, Alugado, Em manutenção
    private int batteryLevel; // Percentagem da bateria
    private String location; // Localização atual do veículo

    public Vehicle(String id, String type, String status, int batteryLevel, String location) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.batteryLevel = batteryLevel;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
