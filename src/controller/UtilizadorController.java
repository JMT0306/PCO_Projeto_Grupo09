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

    /**
     * Regista um novo utilizador no sistema.
     * @param primeiroNome primeiro nome do utilizador.
     * @param ultimoNome último nome do utilizador.
     * @param email endereço de email do utilizador.
     * @param password palavra-passe do utilizador.
     * @param permissao o nível de permissão do utilizador (Cliente, Administrador, etc).
     * @return true se o utilizador foi registado com sucesso, false se o email ou o nome completo já existir.
     */
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

    /**
     * Faz a autenticação de um utilizador com base no nome completo e na palavra-passe fornecida.
     * É verificado se o nome completo e a palavra-passe fornecida correspondem a um utilizador existente.
     * @param nomeUtilizador nome completo do utilizador (primeiro nome e último nome).
     * @param password palavra-passe do utilizador.
     * @return o utilizador autenticado se o nome e a password forem válidos, ou null caso contrário.
     */
    public Utilizador autenticarUtilizador(String nomeUtilizador, String password) {
        for (Utilizador utilizador : utilizadores) {
            String nomeCompleto = utilizador.getPrimeiroNome() + " " + utilizador.getUltimoNome();

            if (nomeCompleto.equals(nomeUtilizador) && utilizador.getPassword().equals(password)) {
                return utilizador;
            }
        }
        return null;
    }

    /**
     * Atribui uma permissão a um utilizador específico, caso seja uma permissão válida.
     * @param id o id do utilizador a atribuir a permissão.
     * @param permissao o nome do tipo de permissão a atribuir ao utilizador.
     * @return true se a permissão foi atribuída com sucesso, false caso contrário.
     */
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

    /**
     * Revoga a permissão de um utilizador específico.
     * É procurado o utilizador com o ID fornecido e, se o utilizador for encontrado, a sua permissão é removida (fica null).
     * @param id ID do utilizador a revogar a permissão.
     * @return true se a permissão foi revogada com sucesso, false caso contrário.
     */
    public boolean revogarPermissao(int id) {
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getId() == id) {
                utilizador.setPermissao(null);
                return true;
            }
        }
        return false;
    }

    /**
     * Lista todos os utilizadores registados no sistema.
     * @return lista com todos os utilizadores.
     */
    public List<Utilizador> listarUtilizadores() {
        return utilizadores;
    }

    /**
     * Carrega uma lista de utilizadores no sistema, atribuindo um ID único a cada um.
     * @param utilizadores lista de utilizadores a serem carregados no sistema.
     */
    public void carregarUtilizadores(List<Utilizador> utilizadores) {
        for (Utilizador utilizador : utilizadores) {
            utilizador.setId(utilizadorIdCounter++);
            this.utilizadores.add(utilizador);
        }
    }
}
