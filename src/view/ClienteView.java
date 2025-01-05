package view;
import controller.AluguerController;
import controller.PagamentoController;
import controller.PercursoController;
import controller.VelocipedeController;
import model.Percurso;
import model.Velocipede;
import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private final VelocipedeController velocipedeController;
    private final PagamentoController pagamentoController;
    private final AluguerController aluguerController;
    private final PercursoController percursoController;
    private final Scanner scanner;
    private final int clienteId;

    public ClienteView(VelocipedeController velocipedeController, PagamentoController pagamentoController, int clienteId,
                       AluguerController aluguerController, PercursoController percursoController) {
        this.velocipedeController = velocipedeController;
        this.pagamentoController = new PagamentoController();
        this.aluguerController = new AluguerController();
        this.percursoController = new PercursoController();
        this.scanner = new Scanner(System.in);
        this.clienteId = clienteId;
    }

    public void mostrarClienteMenu() {
        int option;
        do {
            System.out.println("\n--- Menu do Cliente ---");
            System.out.println("1. Alugar Velocípede");
            System.out.println("2. Efetuar Percurso");
            System.out.println("3. Devolver Velocípede");
            System.out.println("4. Terminar Sessão");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // limpar o buffer

            switch (option) {
                case 1 -> alugarVelocipede();
                case 2 -> efetuarPercurso();
                //case 3 -> devolverVelocipede();
                case 4 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private void alugarVelocipede() {
        System.out.println("Cliente autenticado: " + clienteId);
        System.out.println("\n--- Alugar Velocípede ---");
        List<Velocipede> velocipedesAtivos = velocipedeController.listarVelocipedesAtivos();

        if (velocipedesAtivos.isEmpty()) {
            System.out.println("Não há velocípedes disponíveis para aluguer.");
            return;
        }

        for (Velocipede velocipede : velocipedesAtivos) {
            System.out.println("(ID: " + velocipede.getId() + ") - (Tipo: " + velocipede.getTipo() +
                    ") - (Bateria: " + velocipede.getBateria() + "%) - (Localização: " + velocipede.getLocalizacao() + ")");
        }

        System.out.print("\nEscolha o ID do velocípede que deseja alugar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Confirmar aluguer do velocípede (ID: " + id + ") (s/n)? ");
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("s")) {
            System.out.println("Aluguel cancelado.");
            return;
        }

        System.out.print("\nPagamento do aluguer (cartão de crédito):\n");

        System.out.print("Nome do titular do cartão: ");
        String nomeTitular = scanner.nextLine();

        System.out.print("Número do cartão de crédito (16 dígitos): ");
        String numeroCartao = scanner.nextLine();

        System.out.print("Data de validade (MM/AA) (ex: 01/10): ");
        String validade = scanner.nextLine();

        System.out.print("CVV (3 dígitos): ");
        String cvv = scanner.nextLine();

        boolean pagamentoValido = pagamentoController.processarPagamento(nomeTitular, numeroCartao, validade, cvv);
        if (!pagamentoValido) {
            System.out.println("Erro no processamento do pagamento.");
        } else {
            System.out.println("Pagamento autorizado.");

            aluguerController.criarAluguer(id, clienteId);

            velocipedeController.alterarEstadoAlugado(id);

            System.out.println("Aluguer confirmado.");
        }
    }

    private void efetuarPercurso() {
        System.out.println("\n--- Efetuar Percurso ---");

        boolean temAluguerAtivo = aluguerController.verificarAluguerAtivo(clienteId);
        if (!temAluguerAtivo) {
            System.out.println("Não tem um velocípede alugado. Por favor, alugue um velocípede primeiro.");
            return;
        }

        List<Percurso> percursos = percursoController.listarPercursos();
        System.out.println("Iniciando o percurso com o velocípede alugado...");
        percursoController.iniciarPercurso();
        System.out.println(percursos);
    }
}
