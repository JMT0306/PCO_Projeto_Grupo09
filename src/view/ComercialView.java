package view;
import controller.CampanhaPromocionalController;
import model.CampanhaPromocional;
import java.util.List;
import java.util.Scanner;

public class ComercialView {
    private final CampanhaPromocionalController campanhaPromocionalController;
    private final Scanner scanner;

    public ComercialView(CampanhaPromocionalController campanhaPromocionalController) {
        this.campanhaPromocionalController = campanhaPromocionalController;
        this.scanner = new Scanner(System.in);
    }
    public void mostrarComercialMenu() {
        int option;
        do {
            System.out.println("\n--- Menu do Comercial ---");
            System.out.println("1. Listar Campanhas");
            System.out.println("2. Adicionar Campanha");
            System.out.println("3. Atualizar Campanha");
            System.out.println("4. Remover Campanha");
            System.out.println("5. Terminar Sessão");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (option) {
                case 1 -> verCampanhas();
                case 2 -> adicionarCampanha();
                case 3 -> atualizarCampanha();
                case 4 -> removerCampanha();
                case 5 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private void verCampanhas() {
        System.out.println("\n--- Lista de Campanhas ---");

        List<CampanhaPromocional> campanhas = campanhaPromocionalController.listarCampanhas();

        if (campanhas.isEmpty()) {
            System.out.println("Não há campanhas disponíveis.");
        } else {
            for (CampanhaPromocional campanha : campanhas) {
                System.out.println("ID: " + campanha.getId() + " - " + "(Nome: " + campanha.getNome() + ") - " +
                        "(Tipo: " + campanha.getTipo() + ") - " + "(Detalhes: " + campanha.getDetalhes() + ")");
            }
        }
    }

    private void adicionarCampanha() {
        System.out.println("\n--- Adicionar Campanha ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Detalhes: ");
        String detalhes = scanner.nextLine();

        boolean success = campanhaPromocionalController.adicionarCampanha(nome, tipo, detalhes);

        if (success) {
            System.out.println("Nova campanha adicionada com sucesso");
        } else {
            System.out.println("Erro: Já existe uma campanha com o mesmo nome.");
        }
    }

    private void atualizarCampanha() {
        System.out.println("\n--- Atualizar Campanha ---");

        if (campanhaPromocionalController.listarCampanhas().isEmpty()) {
            System.out.println("Não há campanhas disponíveis.");
            return;
        } else {
            verCampanhas();
        }

        System.out.print("\nID da campanha: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Novos Detalhes: ");
        String detalhes = scanner.nextLine();

        boolean success = campanhaPromocionalController.atualizarCampanha(id, nome, tipo, detalhes);

        if (success) {
            System.out.println("Dados da campanha atualizados com sucesso.");
        } else {
            System.out.println("Falha na atualização da campanha. Campanha não encontrada.");
        }
    }

    private void removerCampanha() {
        System.out.println("\n--- Remover Campanha ---");

        if (campanhaPromocionalController.listarCampanhas().isEmpty()) {
            System.out.println("Não há campanhas disponíveis.");
            return;
        } else {
            verCampanhas();
        }

        System.out.print("\nID da campanha: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Deseja mesmo eliminar a campanha (s/n)? ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            boolean success = campanhaPromocionalController.removerCampanha(id);
            if (success) {
                System.out.println("Campanha removida com sucesso");
            } else {
                System.out.println("Falha na remoção da campanha. Campanha não encontrada.");
            }
        } else {
            System.out.println("Eliminação da campanha cancelada.");
        }
    }
}
