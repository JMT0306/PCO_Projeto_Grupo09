package model;

public class TecnicoManutencao extends Utilizador {

    public TecnicoManutencao(String primeiroNome, String ultimoNome, String email, String password, String permissao) {
        super(primeiroNome, ultimoNome, email, password, permissao);
    }

    public void monitorizarVelocipede(Velocipede velocipede) {
        System.out.println("Monitorização do velocípede " + velocipede.getId() + " em andamento.");
    }

    public void repararVelocipede(Velocipede velocipede) {
        velocipede.setEstado("Reparado");
        System.out.println("Velocípede " + velocipede.getId() + " reparado.");
    }
}
