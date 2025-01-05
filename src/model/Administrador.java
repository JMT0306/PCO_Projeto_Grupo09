package model;

public class Administrador extends Utilizador {

    public Administrador(String primeiroNome, String ultimoNome, String email, String password, String permissao) {
        super(primeiroNome, ultimoNome, email, password, permissao);
    }

    public void gerirUtilizadores() {
        // Implementação da gestão de utilizadores
        System.out.println("Gestão de utilizadores realizada com sucesso.");
    }

    public void atribuirPermissao(Utilizador utilizador, String permissao) {
        utilizador.setPermissao(permissao);
        System.out.println("Permissão atribuída ao utilizador " + utilizador.getPrimeiroNome());
    }

    public void revogarPermissao(Utilizador utilizador) {
        utilizador.setPermissao("default");
        System.out.println("Permissão revogada ao utilizador " + utilizador.getPrimeiroNome());
    }
}