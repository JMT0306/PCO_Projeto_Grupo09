package model;

public class Comercial extends Utilizador {

    public Comercial(String primeiroNome, String ultimoNome, String email, String password, String permissao) {
        super(primeiroNome, ultimoNome, email, password, permissao);
    }

    public void criarCampanha(CampanhaPromocional campanha) {
        System.out.println("Campanha " + campanha.getNome() + " criada com sucesso.");
    }

    public void atualizarCampanha(CampanhaPromocional campanha, String novosDetalhes) {
        campanha.setDetalhes(novosDetalhes);
        System.out.println("Campanha " + campanha.getNome() + " atualizada.");
    }

    public void eliminarCampanha(CampanhaPromocional campanha) {
        System.out.println("Campanha " + campanha.getNome() + " eliminada.");
    }
}