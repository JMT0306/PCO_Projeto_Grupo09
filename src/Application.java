import config.Config;
import controller.*;
import view.MainView;

public class Application {
    public static void main(String[] args) {
        // iniciar os controladores
        UtilizadorController utilizadorController = new UtilizadorController();
        VelocipedeController velocipedeController = new VelocipedeController();
        CampanhaPromocionalController campanhaPromocionalController = new CampanhaPromocionalController();
        PagamentoController pagamentoController = new PagamentoController();
        AluguerController aluguerController = new AluguerController();
        PercursoController percursoController = new PercursoController();

        // carregar os utilizadores e velocípedes default na aplicação
        utilizadorController.carregarUtilizadores(Config.defaultUtilizadores());
        velocipedeController.carregarVelocipedes(Config.defaultVelocipedes());

        // inicializar a visualização principal
        MainView mainView = new MainView(utilizadorController, velocipedeController, campanhaPromocionalController,
                pagamentoController, aluguerController, percursoController);

        // mostrar o menu principal
        mainView.mostrarMenu();

        System.out.println("Sistema encerrado. Obrigado por utilizar!");
    }
}
