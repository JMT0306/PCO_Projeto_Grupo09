package view;

import controller.CampaignController;
import controller.UserController;
import controller.VehicleController;
import model.Campaign;
import model.User;
import model.Vehicle;

import java.util.Scanner;

public class MainView {
    private final UserController userController;
    private final VehicleController vehicleController;
    private final CampaignController campaignController;
    private final Scanner scanner;

    public MainView(UserController userController, VehicleController vehicleController, CampaignController campaignController) {
        this.userController = userController;
        this.vehicleController = vehicleController;
        this.campaignController = campaignController;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int option;
        do {
            System.out.println("\n=== Sistema de Gestão de Velocípedes ===");
            System.out.println("1. Gerir Utilizadores");
            System.out.println("2. Gerir Veículos");
            System.out.println("3. Gerir Campanhas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (option) {
                case 1 -> manageUsers();
                case 2 -> manageVehicles();
                case 3 -> manageCampaigns();
                case 4 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 4);
    }

    private void manageUsers() {
        System.out.println("\n--- Gerir Utilizadores ---");
        System.out.println("1. Registar Utilizador");
        System.out.println("2. Listar Utilizadores");
        System.out.println("3. Atribuir Permissão");
        System.out.println("4. Revogar Permissão");
        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> {
                System.out.print("Nome: ");
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                System.out.print("Permissão (Cliente, Gestor, Técnico, Comercial): ");
                String role = scanner.nextLine();

                User user = new User(String.valueOf(System.currentTimeMillis()), name, email, password, role);
                userController.registerUser(user);
            }
            case 2 -> {
                System.out.println("Lista de Utilizadores:");
                for (User user : userController.listUsers()) {
                    System.out.println(user.getName() + " - " + user.getRole());
                }
            }
            case 3 -> {
                System.out.print("Email do Utilizador: ");
                String email = scanner.nextLine();
                System.out.print("Nova Permissão: ");
                String role = scanner.nextLine();
                userController.assignRole(email, role);
            }
            case 4 -> {
                System.out.print("Email do Utilizador: ");
                String email = scanner.nextLine();
                userController.revokeRole(email);
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void manageVehicles() {
        System.out.println("\n--- Gerir Veículos ---");
        System.out.println("1. Adicionar Veículo");
        System.out.println("2. Listar Veículos");
        System.out.println("3. Monitorizar Veículos");
        System.out.println("4. Distribuir Veículo");
        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> {
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
                vehicleController.addVehicle(vehicle);
            }
            case 2 -> {
                System.out.println("Lista de Veículos:");
                for (Vehicle vehicle : vehicleController.listVehicles()) {
                    System.out.println(vehicle.getId() + " - " + vehicle.getType() + " - " + vehicle.getStatus());
                }
            }
            case 3 -> vehicleController.monitorVehicles();
            case 4 -> {
                System.out.print("ID do Veículo: ");
                String id = scanner.nextLine();
                System.out.print("Nova Localização: ");
                String newLocation = scanner.nextLine();
                vehicleController.distributeVehicles(id, newLocation);
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void manageCampaigns() {
        System.out.println("\n--- Gerir Campanhas ---");
        System.out.println("1. Adicionar Campanha");
        System.out.println("2. Listar Campanhas");
        System.out.println("3. Atualizar Campanha");
        System.out.println("4. Remover Campanha");
        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> {
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Nome: ");
                String name = scanner.nextLine();
                System.out.print("Tipo: ");
                String type = scanner.nextLine();
                System.out.print("Detalhes: ");
                String details = scanner.nextLine();

                Campaign campaign = new Campaign(id, name, type, details);
                campaignController.addCampaign(campaign);
            }
            case 2 -> {
                System.out.println("Lista de Campanhas:");
                for (Campaign campaign : campaignController.listCampaigns()) {
                    System.out.println(campaign.getName() + " - " + campaign.getDetails());
                }
            }
            case 3 -> {
                System.out.print("ID da Campanha: ");
                String id = scanner.nextLine();
                System.out.print("Novo Nome: ");
                String name = scanner.nextLine();
                System.out.print("Novo Tipo: ");
                String type = scanner.nextLine();
                System.out.print("Novos Detalhes: ");
                String details = scanner.nextLine();

                Campaign updatedCampaign = new Campaign(id, name, type, details);
                campaignController.updateCampaign(id, updatedCampaign);
            }
            case 4 -> {
                System.out.print("ID da Campanha: ");
                String id = scanner.nextLine();
                campaignController.removeCampaign(id);
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}
