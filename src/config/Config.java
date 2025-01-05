package config;
import model.Utilizador;
import model.Velocipede;
import java.util.List;

public class Config {

    public static List<Utilizador> defaultUtilizadores() {
        return List.of(
                new Utilizador("a", "a", "andre@email.com", "a", "Administrador"),
                new Utilizador("b", "b", "joao@email.com", "b", "Comercial"),
                new Utilizador("c", "c", "maria@email.com", "c", "GestorFrota"),
                new Utilizador("d", "d", "joana@email.com", "d", "TécnicoManutenção"),
                new Utilizador("e", "e", "rodrigo@email.com", "e", "Cliente")
        );
    }

    public static List<Velocipede> defaultVelocipedes() {
        return List.of(
                new Velocipede("Bicicleta", "Disponível", 78, "2.245, 3.444", "2.245, 3.444"),
                new Velocipede("Trotinete", "Alugado", 55, "-23.550, -46.633", "-23.550, -46.633"),
                new Velocipede("Bicicleta", "Manutenção", 45, "40.712, -74.006", "40.712, -74.006"),
                new Velocipede("Trotinete", "Disponível", 92, "48.856, 2.352", "48.856, 2.352"),
                new Velocipede("Trotinete", "Disponível", 41, "48.856, 2.352", "48.856, 2.352"),
                new Velocipede("Trotinete", "Disponível", 99, "48.856, 2.352", "48.856, 2.352"),
                new Velocipede("Trotinete", "Disponível", 89, "48.856, 2.352", "48.856, 2.352"),
                new Velocipede("Bicicleta", "Disponível", 77, "48.856, 2.352", "48.856, 2.352"),
                new Velocipede("Bicicleta", "Disponível", 67, "-33.868, 151.209", "-33.868, 151.209"),
                new Velocipede("Bicicleta", "Disponível", 34, "-33.868, 151.209", "-33.868, 151.209"),
                new Velocipede("Trotinete", "Disponível", 34, "-12.454, 27.390", "-3.868, 15.209")
        );
    }
}
