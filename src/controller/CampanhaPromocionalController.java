package controller;
import model.CampanhaPromocional;
import java.util.ArrayList;
import java.util.List;

public class CampanhaPromocionalController {
    private static int campaignIdCounter = 1;
    private final List<CampanhaPromocional> campanhaPromocionals;

    public CampanhaPromocionalController() {
        this.campanhaPromocionals = new ArrayList<>();
    }

    // Método para adicionar uma nova campanha
    public boolean adicionarCampanha(String nome, String tipo, String detalhes) {
        for (CampanhaPromocional campanha : campanhaPromocionals) {
            if (campanha.getNome().equalsIgnoreCase(nome)) {
                return false;
            }
        }

        CampanhaPromocional campanha = new CampanhaPromocional(nome, tipo, detalhes);
        campanha.setId(campaignIdCounter++);
        campanhaPromocionals.add(campanha);
        return true;
    }

    // Método para atualizar uma campanha existente
    public boolean atualizarCampanha(int id, String nome, String tipo, String detalhes) {
        for (CampanhaPromocional campanhaPromocional : campanhaPromocionals) {
            if (campanhaPromocional.getId() == id) {
                campanhaPromocional.setNome(nome);
                campanhaPromocional.setTipo(tipo);
                campanhaPromocional.setDetalhes(detalhes);
                return true;
            }
        }
        return false;
    }

    // Método para remover uma campanha
    public boolean removerCampanha(int id) {
        for (int i = 0; i < campanhaPromocionals.size(); i++) {
            if (campanhaPromocionals.get(i).getId() == id) {
                campanhaPromocionals.remove(i);
                return true;
            }
        }
        return false;
    }

    // Método para listar todas as campanhas
    public List<CampanhaPromocional> listarCampanhas() {
        return campanhaPromocionals;
    }
}
