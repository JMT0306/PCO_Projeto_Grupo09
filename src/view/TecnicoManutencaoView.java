package view;
import controller.VelocipedeController;
import model.Velocipede;
import java.util.List;
import java.util.Scanner;

public class TecnicoManutencaoView {
    private final VelocipedeController velocipedeController;
    private final Scanner scanner;

    public TecnicoManutencaoView(VelocipedeController velocipedeController) {
        this.velocipedeController = velocipedeController;
        this.scanner = new Scanner(System.in);
    }
    public void mostrarTecnicoManutencaoMenu() {
        int option;
        do {
            System.out.println("\n--- Menu do Técnico de Manutenção ---");
            System.out.println("1. Listar veículos disponíveis para alugar");
            System.out.println("2. Terminar sessão");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> verListaVelocipedesDisponiveis();
                case 2 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private void verListaVelocipedesDisponiveis() {
        System.out.println("\n--- Lista de Velocípedes Disponíveis para Alugar ---");

        List<Velocipede> velocipedesAtivos = velocipedeController.listarVelocipedesAtivos();

        if (velocipedesAtivos.isEmpty()) {
            System.out.println("Não há velocípedes disponíveis para alugar.");
        } else {
            for (Velocipede velocipede : velocipedesAtivos) {
                System.out.println("(ID: " + velocipede.getId() + ") - " + "(Bateria: " + velocipede.getBateria() + "%) - "
                        + "(Localização: " + velocipede.getLocalizacao() + ")");
            }
        }

        System.out.print("\nID do velocípede para manutenção: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (velocipedeController.carregarBateria(id)) {
            for (Velocipede velocipede : velocipedesAtivos) {
                if (velocipede.getId() == id) {
                    System.out.println("Carregamento iniciado.");
                    velocipede.setEstado("Manutenção");
                    System.out.println("Carregamento finalizado com sucesso.");
                    velocipede.setEstado("Disponível");
                    distribuirVelocipedes(id);
                    break;
                }
            }
        } else {
            System.out.println("Erro: Velocípede não encontrado.");
        }
    }

    private void distribuirVelocipedes(int id) {
        System.out.println("\n--- Distribuir Velocípede Carregado ---");

        System.out.print("Nova Latitude (de -90 a 90): ");
        double latitude = Double.parseDouble(scanner.nextLine());

        System.out.print("Nova Longitude (de -180 a 180): ");
        double longitude = Double.parseDouble(scanner.nextLine());

        String novaLocalizacao = latitude + "," + longitude;

        boolean sucesso = velocipedeController.atualizarLocalizacaoVelocipede(id, novaLocalizacao);

        if (sucesso) {
            System.out.println("Velocípede distribuído com sucesso.");
        } else {
            System.out.println("Erro: Não foi possível distribuir o velocípede.");
        }
    }
}
