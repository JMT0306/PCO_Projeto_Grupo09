import controller.CampanhaPromocionalController;
import controller.UtilizadorController;
import controller.VelocipedeController;
import view.MainView;

public class Application {
    public static void main(String[] args) {
        // Inicializar os controladores
        UtilizadorController utilizadorController = new UtilizadorController();
        VelocipedeController velocipedeController = new VelocipedeController();
        CampanhaPromocionalController campanhaPromocionalController = new CampanhaPromocionalController();

        // Inicializar a visualização principal
        MainView mainView = new MainView(utilizadorController, velocipedeController, campanhaPromocionalController);

        // Mostrar o menu principal
        mainView.mostrarMenu();

        System.out.println("Sistema encerrado. Obrigado por utilizar!");
    }
}
