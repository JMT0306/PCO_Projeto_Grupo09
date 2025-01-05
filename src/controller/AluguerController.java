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

    // Método para criar um novo aluguer
    public void criarAluguer(int idVelocipede, int idCliente) {
        Aluguer aluguer = new Aluguer(aluguerIdCounter++, idVelocipede, idCliente);
        alugueres.add(aluguer);
    }

    // Método para verificar se o cliente tem um aluguer ativo
    public boolean verificarAluguerAtivo(int idCliente) {
        for (Aluguer aluguer : alugueres) {
            if (aluguer.getIdCliente() == idCliente) {
                return true;
            }
        }
        return false;
    }

    // Método para listar todos os alugueres
    public List<Aluguer> listarAlugueres() {
        return alugueres;
    }
}

