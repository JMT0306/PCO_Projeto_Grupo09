package view;

import controller.UserController;
import model.User;

import java.util.Scanner;

public class UserView {
    private final UserController userController;
    private final Scanner scanner;

    public UserView(UserController userController) {
        this.userController = userController;
        this.scanner = new Scanner(System.in);
    }

    public void displayUserMenu() {
        int option;
        do {
            System.out.println("\n--- Menu de Utilizadores ---");
            System.out.println("1. Registar Utilizador");
            System.out.println("2. Listar Utilizadores");
            System.out.println("3. Atribuir Permissão");
            System.out.println("4. Revogar Permissão");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (option) {
                case 1 -> registerUser();
                case 2 -> listUsers();
                case 3 -> assignPermission();
                case 4 -> revokePermission();
                case 5 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 5);
    }

    private void registerUser() {
        System.out.println("\n--- Registar Utilizador ---");
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Permissão (Cliente, Gestor, Técnico, Comercial): ");
        String role = scanner.nextLine();

        User user = new User(String.valueOf(System.currentTimeMillis()), name, email, password, role);
        boolean success = userController.registerUser(user);
        if (success) {
            System.out.println("Utilizador registado com sucesso!");
        } else {
            System.out.println("Erro: O email já está registado.");
        }
    }

    private void listUsers() {
        System.out.println("\n--- Lista de Utilizadores ---");
        for (User user : userController.listUsers()) {
            System.out.println(user.getName() + " - " + user.getEmail() + " - " + (user.getRole() == null ? "Sem permissão" : user.getRole()));
        }
    }

    private void assignPermission() {
        System.out.println("\n--- Atribuir Permissão ---");
        System.out.print("Email do Utilizador: ");
        String email = scanner.nextLine();
        System.out.print("Nova Permissão: ");
        String role = scanner.nextLine();
        boolean success = userController.assignRole(email, role);
        if (success) {
            System.out.println("Permissão atribuída com sucesso.");
        } else {
            System.out.println("Erro: Utilizador não encontrado.");
        }
    }

    private void revokePermission() {
        System.out.println("\n--- Revogar Permissão ---");
        System.out.print("Email do Utilizador: ");
        String email = scanner.nextLine();
        boolean success = userController.revokeRole(email);
        if (success) {
            System.out.println("Permissão revogada com sucesso.");
        } else {
            System.out.println("Erro: Utilizador não encontrado.");
        }
    }
}
