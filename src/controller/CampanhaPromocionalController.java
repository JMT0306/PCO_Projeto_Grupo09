package controller;
import model.CampanhaPromocional;
import java.util.ArrayList;
import java.util.List;

public class CampanhaPromocionalController {
    private static int campaignIdCounter = 1;
    private final List<CampanhaPromocional> campanhaPromocional;

    public CampanhaPromocionalController() {
        this.campanhaPromocional = new ArrayList<>();
    }

    /**
     * Adiciona uma nova campanha promocional, se não houver uma campanha com o mesmo nome.
     * @param nome o nome da campanha promocional.
     * @param tipo o tipo da campanha promocional.
     * @param detalhes os detalhes da campanha promocional.
     * @return true se a campanha for adicionada com sucesso, false se já houver uma campanha com o mesmo nome.
     */
    public boolean adicionarCampanha(String nome, String tipo, String detalhes) {
        for (CampanhaPromocional campanha : campanhaPromocional) {
            if (campanha.getNome().equalsIgnoreCase(nome)) {
                return false;
            }
        }

        CampanhaPromocional campanha = new CampanhaPromocional(nome, tipo, detalhes);
        campanha.setId(campaignIdCounter++);
        campanhaPromocional.add(campanha);
        return true;
    }

    /**
     * Atualiza os dados de uma campanha promocional existente.
     * @param id é o identificador da campanha a ser atualizada.
     * @param nome é o novo nome da campanha a atualizar.
     * @param tipo é o novo tipo da camapanha a atualizar.
     * @param detalhes é os novos detalhes da campanha a atualizar.
     * @return true se atualizar a campanha com sucesso, falso se o id da campanha fornecido não foi encontrado.
     */
    public boolean atualizarCampanha(int id, String nome, String tipo, String detalhes) {
        for (CampanhaPromocional campanhaPromocional : campanhaPromocional) {
            if (campanhaPromocional.getId() == id) {
                campanhaPromocional.setNome(nome);
                campanhaPromocional.setTipo(tipo);
                campanhaPromocional.setDetalhes(detalhes);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove uma campanha promocional existente com base num ID fornecido.
     * @param id é o id da camapanha a ser removida.
     * @return true se removeu a campanha com sucesso, false se nenhuma campanha com o id fornecido foi encontrada.
     */
    public boolean removerCampanha(int id) {
        for (int i = 0; i < campanhaPromocional.size(); i++) {
            if (campanhaPromocional.get(i).getId() == id) {
                campanhaPromocional.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna a lista de todas as campanhas promocionais registadas.
     * @return lista com todas as campanhas promocionais.
     */
    public List<CampanhaPromocional> listarCampanhas() {
        return campanhaPromocional;
    }
}
