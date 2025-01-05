package model;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utilizador {
    private final List<Aluguer> alugueres;

    public Cliente(String primeiroNome, String ultimoNome, String email, String password, String permissao) {
        super(primeiroNome, ultimoNome, email, password, permissao);
        this.alugueres = new ArrayList<>();
    }

    public void adicionarAluguer(Aluguer aluguer) {
        alugueres.add(aluguer);
    }

    public List<Aluguer> getAlugueres() {
        return alugueres;
    }
}
