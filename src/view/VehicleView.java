package view;

import controller.VehicleController;
import model.Vehicle;

import java.util.Scanner;

public class VehicleView {
    private final VehicleController vehicleController;
    private final Scanner scanner;

    public VehicleView(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
        this.scanner = new Scanner(System.in);
    }

    public void displayVehicleMenu() {
        int option;
        do {
            System.out.println("\n--- Menu de Veículos ---");
            System.out.println("1. Adicionar Veículo");
            System.out.println("2. Listar Veículos");
            System.out.println("3. Monitorizar Veículos");
            System.out.println("4. Distribuir Veículo");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (option) {
                case 1 -> addVehicle();
                case 2 -> listVehicles();
                case 3 -> monitorVehicles();
                case 4 -> distributeVehicle();
                case 5 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 5);
    }

    private void addVehicle() {
        System.out.println("\n--- Adicionar Veículo ---");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Tipo (Bicicleta/Trotinete): ");
        String type = scanner.nextLine();
        System.out.print("Status (Disponível, Alugado, Em manutenção): ");
        String status = scanner.nextLine();
        System.out.print("Nível de Bateria (%): ");
        int batteryLevel = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Localização: ");
        String location = scanner.nextLine();

        Vehicle vehicle = new Vehicle(id, type, status, batteryLevel, location);
        boolean success = vehicleController.addVehicle(vehicle);
        if (success) {
            System.out.println("Veículo adicionado com sucesso!");
        } else {
            System.out.println("Erro: Veículo com ID " + id + " já existe.");
        }
    }

    private void listVehicles() {
        System.out.println("\n--- Lista de Veículos ---");
        for (Vehicle vehicle : vehicleController.listVehicles()) {
            System.out.println(vehicle.getId() + " - " + vehicle.getType() + " - " + vehicle.getStatus() + " - Localização: " + vehicle.getLocation());
        }
    }

    private void monitorVehicles() {
        System.out.println("\n--- Monitorizar Veículos ---");
        vehicleController.monitorVehicles();
    }

    private void distributeVehicle() {
        System.out.println("\n--- Distribuir Veículo ---");
        System.out.print("ID do Veículo: ");
        String id = scanner.nextLine();
        System.out.print("Nova Localização: ");
        String newLocation = scanner.nextLine();

        boolean success = vehicleController.distributeVehicles(id, newLocation);
        if (success) {
            System.out.println("Veículo distribuído com sucesso!");
        } else {
            System.out.println("Erro: Veículo com ID " + id + " não encontrado.");
        }
    }
}
