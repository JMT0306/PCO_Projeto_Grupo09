package view;
import controller.UtilizadorController;
import model.Utilizador;
import java.util.Scanner;

public class AdministradorView {
    private final UtilizadorController utilizadorController;
    private final Scanner scanner;

    public AdministradorView(UtilizadorController utilizadorController) {
        this.utilizadorController = utilizadorController;
        this.scanner = new Scanner(System.in);
    }
    public void mostrarAdminMenu() {
        int option;
        do {
            System.out.println("\n--- Menu do Admin ---");
            System.out.println("1. Listar Utilizadores");
            System.out.println("2. Atribuir Permissão");
            System.out.println("3. Revogar Permissão");
            System.out.println("4. Terminar Sessão");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> verUtilizadores();
                case 2 -> atribuirPermissao();
                case 3 -> revogarPermissao();
                case 4 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private void verUtilizadores() {
        System.out.println("\n--- Lista de Utilizadores ---");

        for (Utilizador utilizador : utilizadorController.listarUtilizadores()) {
            System.out.println("ID: " + utilizador.getId() + " - " + "(Nome: " + utilizador.getPrimeiroNome() + ") - " +
                    "(Email: " + utilizador.getEmail() + ") - " + "(Permissão: " + (utilizador.getPermissao() == null ?
                    "Sem Permissão" : utilizador.getPermissao()) + ")");
        }
    }

    private void atribuirPermissao() {
        System.out.println("\n--- Atribuir Permissão ---");

        verUtilizadores();

        System.out.print("\nID do utilizador: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Escreva a permissão a atribuir (GestorFrota, TécnicoManutenção ou Comercial): ");
        String permissao = scanner.nextLine();

        System.out.print("Confirmar atribuição (s/n)? ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            boolean success = utilizadorController.atribuirPermissao(id, permissao);
            if (success) {
                System.out.println("Permissão atribuída com sucesso.");
            } else {
                System.out.println("Erro: Utilizador não encontrado ou permissão inválida.");
            }
        } else {
            System.out.println("Atribuição de permissão cancelada.");
        }
    }

    private void revogarPermissao() {
        System.out.println("\n--- Revogar Permissão ---");

        verUtilizadores();

        System.out.print("\nID do utilizador: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Confirmar revogação (s/n)? ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            boolean success = utilizadorController.revogarPermissao(id);
            if (success) {
                System.out.println("Permissão revogada com sucesso.");
            } else {
                System.out.println("Erro: Utilizador não encontrado.");
            }
        } else {
            System.out.println("Revogação de permissão cancelada.");
        }
    }
}
