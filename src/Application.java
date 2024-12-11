import controller.CampaignController;
import controller.UserController;
import controller.VehicleController;
import view.MainView;

public class Application {
    public static void main(String[] args) {
        // Inicializar os controladores
        UserController userController = new UserController();
        VehicleController vehicleController = new VehicleController();
        CampaignController campaignController = new CampaignController();

        // Inicializar a visualização principal
        MainView mainView = new MainView(userController, vehicleController, campaignController);

        // Exibir o menu principal
        mainView.displayMenu();

        System.out.println("Sistema encerrado. Obrigado por utilizar!");
    }
}
