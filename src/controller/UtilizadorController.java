package controller;
import model.Utilizador;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorController {
    private final List<Utilizador> utilizadores;
    private static int utilizadorIdCounter = 1;

    public UtilizadorController() {
        this.utilizadores = new ArrayList<>();
    }

    // Método para registar um novo utilizador
    public boolean registarUtilizador(String primeiroNome, String ultimoNome, String email, String password, String permissao) {
        Utilizador utilizador = new Utilizador(primeiroNome, ultimoNome, email, password, permissao);
        utilizador.setId(utilizadorIdCounter++);

        for (Utilizador existingUser : utilizadores) {
            if (existingUser.getEmail().equals(utilizador.getEmail())) {
                return false;
            }
            if (existingUser.getPrimeiroNome().equals(utilizador.getPrimeiroNome()) &&
                    existingUser.getUltimoNome().equals(utilizador.getUltimoNome())) {
                return false;
            }
        }

        utilizadores.add(utilizador);
        return true;
    }

    // Método para autenticar um utilizador
    public Utilizador autenticarUtilizador(String nomeUtilizador, String password) {
        for (Utilizador utilizador : utilizadores) {
            String nomeCompleto = utilizador.getPrimeiroNome() + " " + utilizador.getUltimoNome();

            if (nomeCompleto.equals(nomeUtilizador) && utilizador.getPassword().equals(password)) {
                return utilizador;
            }
        }
        return null;
    }

    // Método para atribuir uma permissão a um utilizador
    public boolean atribuirPermissao(int id, String permissao) {
        if (!permissao.equals("GestorFrota") && !permissao.equals("TécnicoManutenção") && !permissao.equals("Comercial")) {
            return false;
        }
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getId() == id) {
                utilizador.setPermissao(permissao);
                return true;
            }
        }
        return false;
    }

    // Método para revogar uma permissão de um utilizador
    public boolean revogarPermissao(int id) {
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getId() == id) {
                utilizador.setPermissao(null);
                return true;
            }
        }
        return false;
    }

    // Método para listar todos os utilizadores
    public List<Utilizador> listarUtilizadores() {
        return utilizadores;
    }

    // Método para carregar os utilizadores na aplicação (classe Config)
    public void carregarUtilizadores(List<Utilizador> utilizadores) {
        for (Utilizador utilizador : utilizadores) {
            utilizador.setId(utilizadorIdCounter++);
            this.utilizadores.add(utilizador);
        }
    }
}
