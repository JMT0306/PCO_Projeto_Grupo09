package view;

import controller.CampanhaPromocionalController;
import controller.UtilizadorController;
import controller.VelocipedeController;
import model.CampanhaPromocional;
import model.Utilizador;
import model.Velocipede;

import java.util.Scanner;

public class MainViewOriginal {
    private final UtilizadorController utilizadorController;
    private final VelocipedeController velocipedeController;
    private final CampanhaPromocionalController campanhaPromocionalController;
    private final Scanner scanner;

    public MainViewOriginal(UtilizadorController utilizadorController, VelocipedeController velocipedeController, CampanhaPromocionalController campanhaPromocionalController) {
        this.utilizadorController = utilizadorController;
        this.velocipedeController = velocipedeController;
        this.campanhaPromocionalController = campanhaPromocionalController;
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
                case 1 -> manageVehicles();
                case 2 -> manageVehicles();
                case 3 -> manageCampaigns();
                case 4 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 4);
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
                System.out.print("Tipo (Bicicleta/Trotinete): ");
                String tipo = scanner.nextLine();
                System.out.print("Status (Disponível, Alugado, Em manutenção): ");
                String estado = scanner.nextLine();
                System.out.print("Nível de Bateria (%): ");
                int bateria = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Localização: ");
                String localizacao = scanner.nextLine();

                //Velocipede velocipede = new Velocipede(tipo, estado, bateria, localizacao);
                //velocipedeController.addVehicle(velocipede);
            }
            case 2 -> {
                System.out.println("Lista de Veículos:");
                velocipedeController.listarVelocipedes();
            }
            case 3 -> velocipedeController.listarVelocipedes();
            case 4 -> {
                System.out.print("ID do Veículo: ");
                String id = scanner.nextLine();
                System.out.print("Nova Localização: ");
                String newLocation = scanner.nextLine();
                //velocipedeController.distributeVehicles(id, newLocation);
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

                CampanhaPromocional campanhaPromocional = new CampanhaPromocional(name, type, details);
                //campanhaPromocionalController.adicionarCampanha(campanhaPromocional);
            }
            case 2 -> {
                System.out.println("Lista de Campanhas:");
                for (CampanhaPromocional campaign : campanhaPromocionalController.listarCampanhas()) {
                    System.out.println(campaign.getNome() + " - " + campaign.getDetalhes());
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

                CampanhaPromocional updatedCampaign = new CampanhaPromocional(name, type, details);
                //campaignController.updateCampaign(id, updatedCampaign);
            }
            case 4 -> {
                System.out.print("ID da Campanha: ");
                String id = scanner.nextLine();
                //campaignController.removeCampaign(id);
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}
