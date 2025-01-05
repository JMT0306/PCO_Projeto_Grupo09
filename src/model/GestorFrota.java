package model;

public class GestorFrota extends Utilizador {

    public GestorFrota(String primeiroNome, String ultimoNome, String email, String password, String permissao) {
        super(primeiroNome, ultimoNome, email, password, permissao);
    }

    public void adicionarVelocipede(Velocipede velocipede) {
        System.out.println("Velocípede " + velocipede.getTipo() + " adicionado.");
    }

    public void atualizarVelocipede(Velocipede velocipede, String novoEstado) {
        velocipede.setEstado(novoEstado);
        System.out.println("Velocípede " + velocipede.getTipo() + " atualizado para " + novoEstado + ".");
    }

    public void removerVelocipede(Velocipede velocipede) {
        System.out.println("Velocípede " + velocipede.getTipo() + " removido.");
    }
}
