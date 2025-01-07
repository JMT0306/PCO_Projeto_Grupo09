package view;
import controller.*;
import model.Utilizador;
import model.Velocipede;
import java.util.List;
import java.util.Scanner;

public class MainView {
    private final UtilizadorController utilizadorController;
    private final VelocipedeController velocipedeController;
    private final CampanhaPromocionalController campanhaPromocionalController;
    private final PagamentoController pagamentoController;
    private final AluguerController aluguerController;
    private final PercursoController percursoController;
    private final Scanner scanner;

    public MainView(UtilizadorController utilizadorController, VelocipedeController velocipedeController,
                    CampanhaPromocionalController campanhaPromocionalController, PagamentoController pagamentoController,
                    AluguerController aluguerController, PercursoController percursoController) {
        this.utilizadorController = utilizadorController;
        this.velocipedeController = velocipedeController;
        this.campanhaPromocionalController = campanhaPromocionalController;
        this.pagamentoController = pagamentoController;
        this.aluguerController = aluguerController;
        this.percursoController = percursoController;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int option;
        do {
            System.out.println("\n=== Sistema de Gestão de Velocípedes ===");
            System.out.println("1. Registar Utilizador");
            System.out.println("2. Autenticar Utilizador");
            System.out.println("3. Consultar Velocípedes");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> iniciarRegisto();
                case 2 -> iniciarSessao();
                case 3 -> consultarVelocipedes();
                case 4 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 4);
    }

    private void iniciarRegisto() {
        System.out.println("\n--- Registar Utilizador ---");

        System.out.print("Primeiro Nome: ");
        String primeiroNome = scanner.nextLine();
        System.out.print("Último Nome: ");
        String ultimoNome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        String permissao = "Cliente";

        System.out.println("Um link de confirmação foi enviado para o seu email: " + email);

        boolean sucesso = utilizadorController.registarUtilizador(primeiroNome, ultimoNome, email, password, permissao);

        if (sucesso) {
            System.out.println("Registado como utilizador com sucesso.");
        } else {
            System.out.println("Erro no registo. O email pode já estar registado ou o nome completo já existir.");
        }
    }

    private void iniciarSessao() {
        System.out.println("\n--- Autenticar Utilizador ---");

        System.out.print("Nome de Utilizador (Primeiro e Último Nome): ");
        String nomeUtilizador = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Utilizador utilizador = utilizadorController.autenticarUtilizador(nomeUtilizador, password);

        if (utilizador != null) {
            if (utilizador.getPermissao() == null) {
                System.out.println("A sua conta não tem qualquer permissão. Contacte um Administrador.");
                mostrarMenu();
            } else {
                System.out.println("Autenticação autorizada.");
                System.out.println("Bem-vindo, " + utilizador.getPrimeiroNome() + " " + utilizador.getUltimoNome() + "!");
            }

            switch (utilizador.getPermissao()) {
                case "Administrador" -> {
                    AdministradorView administradorView = new AdministradorView(utilizadorController);
                    administradorView.mostrarAdminMenu();
                }
                case "Comercial" -> {
                    ComercialView comercialView = new ComercialView(campanhaPromocionalController);
                    comercialView.mostrarComercialMenu();
                }
                case "GestorFrota" -> {
                    GestorFrotaView gestorFrotaView = new GestorFrotaView(velocipedeController);
                    gestorFrotaView.mostrarGestorFrotaMenu();
                }
                case "TécnicoManutenção" -> {
                    TecnicoManutencaoView tecnicoManutencaoView = new TecnicoManutencaoView(velocipedeController);
                    tecnicoManutencaoView.mostrarTecnicoManutencaoMenu();
                }
                case "Cliente" -> {
                    ClienteView clienteView = new ClienteView(velocipedeController, pagamentoController, utilizador.getId(),
                            aluguerController, percursoController);
                    clienteView.mostrarClienteMenu();
                }

                default -> System.out.println("Erro: Permissão desconhecida.");
            }
        } else {
            System.out.println("Erro: Credenciais inválidas.");
        }
    }

    private void consultarVelocipedes() {
        System.out.println("\n--- Consultar Velocípedes ---");

        List<Velocipede> velocipedes = velocipedeController.listarVelocipedesAtivos();

        if (velocipedes.isEmpty()) {
            System.out.println("Não há velocípedes registados.");
        } else {
            for (Velocipede velocipede : velocipedes) {
                System.out.println(("(Tipo: " + velocipede.getTipo() + ") - (Localização: " + velocipede.getLocalizacao()
                        + ")" + " - (Estado: " + velocipede.getEstado() + ")"));
            }
        }
    }
}
