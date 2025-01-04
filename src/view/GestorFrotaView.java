package view;
import controller.VelocipedeController;
import model.CampanhaPromocional;
import model.Velocipede;

import java.util.List;
import java.util.Scanner;

public class GestorFrotaView {
    private final VelocipedeController velocipedeController;
    private final Scanner scanner;

    public GestorFrotaView(VelocipedeController velocipedeController) {
        this.velocipedeController = velocipedeController;
        this.scanner = new Scanner(System.in);
    }
    public void mostrarGestorFrotaMenu() {
        int option;
        do {
            System.out.println("\n--- Menu do Gestor da Frota ---");
            System.out.println("1. Listar Velocípedes");
            System.out.println("2. Adicionar Velocípede");
            System.out.println("3. Distribuir Velocípede");
            System.out.println("4. Remover Velocípede");
            System.out.println("5. Terminar Sessão");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (option) {
                case 1 -> verVelocipedes();
                case 2 -> adicionarVelocipede();
                case 3 -> distribuirVelocipedes();
                case 4 -> removerVelocipede();
                case 5 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private void verVelocipedes() {
        System.out.println("\n--- Lista de Velocípedes ---");

        List<Velocipede> velocipedes = velocipedeController.listarVelocipedes();

        if (velocipedes.isEmpty()) {
            System.out.println("Não há velocípedes disponíveis.");
        } else {
            for (Velocipede velocipede : velocipedes) {
                System.out.println("ID: " + velocipede.getId() + " - " + "(Tipo: " + velocipede.getTipo() + ") - " +
                        "(Estado: " + velocipede.getEstado() + ") - "  + "(Bateria: " + velocipede.getBateria() + "%) - "
                        + "(Localização: " + velocipede.getLocalizacao() + ")");
            }
        }
    }

    private void adicionarVelocipede() {
        System.out.println("\n--- Adicionar Velocípede ---");

        System.out.print("Tipo (Bicicleta/Trotinete): ");
        String tipo = scanner.nextLine();

        System.out.print("Estado (Disponível, Alugado, Manutenção): ");
        String estado = scanner.nextLine();

        System.out.print("Bateria (entre 0 a 100): ");
        int bateria = Integer.parseInt(scanner.nextLine());

        System.out.print("Localização (coordenadas): ");
        String localizacao = scanner.nextLine();

        boolean success = velocipedeController.adicionarVelocipede(tipo, estado, bateria, localizacao);

        if (success) {
            System.out.println("Velocípede registado na frota com sucesso.");
        } else {
            System.out.println("Erro: Foram encontrados erros no registo do velocípede.");
        }
    }

    private void distribuirVelocipedes() {
        System.out.println("\n--- Distribuir Velocípedes ---");

        List<Velocipede> ativos = velocipedeController.listarVelocipedesAtivos();

        if (ativos.isEmpty()) {
            System.out.println("Não há velocípedes ativos disponíveis para distribuição.");
            return;
        }

        System.out.println("Velocípedes ativos disponíveis:");
        for (Velocipede v : ativos) {
            System.out.println("ID: " + v.getId() + ", Localização atual: " + v.getLocalizacao());
        }

        System.out.print("ID do velocípede a distribuir: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nova localização (coordenadas): ");
        String novaLocalizacao = scanner.nextLine();

        boolean sucesso = velocipedeController.atualizarLocalizacaoVelocipede(id, novaLocalizacao);

        if (sucesso) {
            System.out.println("Velocípede distribuído com sucesso.");
        } else {
            System.out.println("Erro: Não foi possível distribuir o velocípede.");
        }
    }

    private void removerVelocipede() {
        System.out.println("\n--- Remover Velocípede ---");

        if (velocipedeController.listarVelocipedes().isEmpty()) {
            System.out.println("Não há velocípedes disponíveis.");
            return;
        } else {
            verVelocipedes();
        }

        System.out.print("\nID do velocípede: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Confirmar operação (s/n)? ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            String result = velocipedeController.removerVelocipede(id);

            System.out.println(result);
        } else {
            System.out.println("Eliminação do velocípede cancelada.");
        }
    }
}
