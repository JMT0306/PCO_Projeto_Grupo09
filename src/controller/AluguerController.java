package controller;
import model.Aluguer;
import java.util.ArrayList;
import java.util.List;

public class AluguerController {
    private static int aluguerIdCounter = 1;
    private final List<Aluguer> alugueres;

    public AluguerController() {
        this.alugueres = new ArrayList<>();
    }

    /**
     * Cria um novo aluguer e adiciona à lista de alugueres.
     * @param idVelocipede id do velocípede que será alugado.
     * @param idCliente id do cliente que está a alugar.
     */
    public void criarAluguer(int idVelocipede, int idCliente) {
        Aluguer aluguer = new Aluguer(aluguerIdCounter++, idVelocipede, idCliente);
        alugueres.add(aluguer);
    }

    /**
     * Verifica se o cliente tem um aluguer ativo.
     * @param idCliente id do cliente que será verificado.
     * @return true se tiver aluguer ativo, falso caso não.
     */
    public boolean verificarAluguerAtivo(int idCliente) {
        for (Aluguer aluguer : alugueres) {
            if (aluguer.isAtivo() && aluguer.getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtem o ID do velocípede associado ao cliente.
     * @param idCliente id do cliente.
     * @return id do velocípede associado ao cliente ou -1 se o cliente não tiver um aluguer ativo.
     */
    public int obterVelocipedeId(int idCliente) {
        for (Aluguer aluguer : alugueres) {
            if (aluguer.getIdCliente() == idCliente) {
                return aluguer.getVelocipedeId();
            }
        }
        return -1;
    }

    /**
     * Desativa o aluguer associado ao cliente, marcando-o como não ativo.
     * @param idCliente id do cliente cujo aluguer será desativado.
     */
    public void desativarAluguer(int idCliente) {
        for (Aluguer aluguer: alugueres) {
            if (aluguer.getIdCliente() == idCliente) {
                aluguer.setAtivo(false);
            }
        }
    }
}
