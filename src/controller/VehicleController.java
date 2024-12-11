package controller;

import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleController {
    private List<Vehicle> vehicles;

    public VehicleController() {
        this.vehicles = new ArrayList<>();
    }

    // Método para adicionar um novo veículo
    public boolean addVehicle(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(vehicle.getId())) {
                System.out.println("Erro: Veículo com ID " + vehicle.getId() + " já existe.");
                return false;
            }
        }
        vehicles.add(vehicle);
        System.out.println("Veículo adicionado com sucesso: " + vehicle.getId());
        return true;
    }

    // Método para remover um veículo
    public boolean removeVehicle(String id) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId().equals(id)) {
                System.out.println("Veículo removido com sucesso: " + vehicles.get(i).getId());
                vehicles.remove(i);
                return true;
            }
        }
        System.out.println("Erro: Veículo com ID " + id + " não encontrado.");
        return false;
    }

    // Método para listar todos os veículos
    public List<Vehicle> listVehicles() {
        return vehicles;
    }

    // Método para listar veículos disponíveis
    public List<Vehicle> listAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getStatus().equals("Disponível")) {
                availableVehicles.add(v);
            }
        }
        return availableVehicles;
    }

    // Método para monitorizar o estado dos veículos
    public void monitorVehicles() {
        System.out.println("Estado atual dos veículos:");
        for (Vehicle v : vehicles) {
            System.out.println("ID: " + v.getId() + ", Tipo: " + v.getType() + ", Bateria: " + v.getBatteryLevel() + "%, Estado: " + v.getStatus());
        }
    }

    // Método para distribuir veículos para novas localizações
    public boolean distributeVehicles(String id, String newLocation) {
        for (Vehicle v : vehicles) {
            if (v.getId().equals(id)) {
                v.setLocation(newLocation);
                System.out.println("Veículo " + id + " distribuído para a localização: " + newLocation);
                return true;
            }
        }
        System.out.println("Erro: Veículo com ID " + id + " não encontrado.");
        return false;
    }
}
