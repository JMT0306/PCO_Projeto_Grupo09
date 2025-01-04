package view;

import controller.VelocipedeController;
import model.Velocipede;

import java.util.Scanner;

public class VehicleView {
    private final VelocipedeController velocipedeController;
    private final Scanner scanner;

    public VehicleView(VelocipedeController velocipedeController) {
        this.velocipedeController = velocipedeController;
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

                case 5 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 5);
    }


}
