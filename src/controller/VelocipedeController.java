package controller;
import model.Velocipede;
import java.util.ArrayList;
import java.util.List;

public class VelocipedeController {
    private static int velocipedeIdCounter = 1;
    private final List<Velocipede> frota;

    public VelocipedeController() {
        this.frota = new ArrayList<>();
    }

    /**
     * Adiciona um novo velocípede à frota, verificando também a validade dos dados fornecidos.
     * @param tipo é o tipo de velocípede ("Bicicleta" ou "Trotinete").
     * @param estado é o estado do velocípede ("Disponível", "Alugado" ou "Manutenção").
     * @param bateria é o nível da bateria (entre 0 e 100%).
     * @param localizacaoPonto é a localização (coordenadas) definida pelo gestor ou técnico como um ponto de entrega/recolha.
     * @param localizacao é a localização (coordenadas) atual do velocípede.
     * @return true se o velocípede foi adicionado com sucesso, false caso contrário.
     */
    public boolean adicionarVelocipede(String tipo, String estado, int bateria, String localizacaoPonto, String localizacao) {
        if (!(tipo.equalsIgnoreCase("Bicicleta") || tipo.equalsIgnoreCase("Trotinete"))) {
            return false;
        }

        if (!(estado.equalsIgnoreCase("Disponível") || estado.equalsIgnoreCase("Alugado")
                || estado.equalsIgnoreCase("Manutenção"))) {
            return false;
        }

        if (bateria < 0 || bateria > 100) {
            return false;
        }

        String[] coords = localizacaoPonto.split(",");
        double latitude = Double.parseDouble(coords[0].trim());
        double longitude = Double.parseDouble(coords[1].trim());

        if (latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
            return false;
        }

        Velocipede velocipede = new Velocipede(tipo, estado, bateria, localizacaoPonto, localizacao);
        velocipede.setId(velocipedeIdCounter++);
        frota.add(velocipede);

        return true;
    }

    /**
     * Atualiza a localização de um velocípede na frota.
     * Procura o velocípede com o ID fornecido e, se este estiver disponível, atualiza a sua localização.
     * @param id ID do velocípede a atualizar a localização.
     * @param novaLocalizacao a nova localização do velocípede.
     * @return true se a localização foi atualizada com sucesso, false caso contrário.
     */
    public boolean atualizarLocalizacaoVelocipede(int id, String novaLocalizacao) {
        for (Velocipede velocipede : frota) {
            if (velocipede.getId() == id && velocipede.getEstado().equalsIgnoreCase("Disponível")) {
                velocipede.setLocalizacaoPonto(novaLocalizacao);
                velocipede.setLocalizacao(novaLocalizacao);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um velocípede da frota, se estiver disponível.
     * @param id o ID do velocípede a remover.
     * @return uma mensagem indicando sucesso ou a razão pela qual a remoção não foi possível.
     */
    public String removerVelocipede(int id) {
        for (int i = 0; i < frota.size(); i++) {
            Velocipede velocipede = frota.get(i);
            if (velocipede.getId() == id) {
                if (velocipede.getEstado().equalsIgnoreCase("Disponível")) {
                    frota.remove(i);
                    return "Velocípede removido com sucesso.";
                } else {
                    return "Velocípede em uso, não pode ser removido.";
                }
            }
        }
        return "Velocípede não encontrado.";
    }

    /**
     * Lista os velocípedes que estão disponíveis na frota.
     * @return lista de velocípedes disponíveis.
     */
    public List<Velocipede> listarVelocipedesAtivos() {
        List<Velocipede> velocipedesAtivos = new ArrayList<>();
        for (Velocipede velocipede : frota) {
            if (velocipede.getEstado().equalsIgnoreCase("Disponível")) {
                velocipedesAtivos.add(velocipede);
            }
        }
        return velocipedesAtivos;
    }

    /**
     * Encontra as localizações com maior e menor concentração de velocípedes ativos na frota.
     * Verifica todas as localizações dos velocípedes disponíveis e calcula a quantidade de velocípedes em cada local.
     * @return string com a maior e menor concentração de velocípedes e o número de velocípedes em cada local.
     */
    public String encontrarConcentracao() {
        List<Velocipede> velocipedesAtivos = listarVelocipedesAtivos();
        String maiorLocal = "", menorLocal = "";
        int maiorQtd = 0, menorQtd = Integer.MAX_VALUE;

        for (Velocipede velocipede : velocipedesAtivos) {
            String localizacao = velocipede.getLocalizacao();
            int count = contarVelocipedesPorLocalizacao(localizacao);

            if (count > maiorQtd) {
                maiorQtd = count;
                maiorLocal = localizacao;
            }
            if (count < menorQtd) {
                menorQtd = count;
                menorLocal = localizacao;
            }
        }

        return "Maior concentração: " + maiorLocal + " (" + maiorQtd + " velocípedes)\n" +
                "Menor concentração: " + menorLocal + " (" + menorQtd + " velocípedes)";
    }

    /**
     * Conta o número de velocípedes disponíveis em uma determinada localização.
     * @param localizacao é a localização para contar os velocípedes disponíveis.
     * @return o número de velocípedes disponíveis na localização especificada.
     */
    private int contarVelocipedesPorLocalizacao(String localizacao) {
        int contador = 0;

        for (Velocipede velocipede : frota) {
            if (velocipede.getEstado().equalsIgnoreCase("Disponível") && velocipede.getLocalizacao().equals(localizacao)) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Verifica se o velocípede está fora do ponto válido de localização.
     * Compara a localização atual do velocípede com o ponto de localização inicial.
     * @param velocipede o velocípede a verificar.
     * @return true se o velocípede estiver fora do ponto válido, false caso contrário.
     */
    private boolean isForaDoPontoValido(Velocipede velocipede) {
        String localizacao = velocipede.getLocalizacao();
        String localizacaoPonto = velocipede.getLocalizacaoPonto();

        return !localizacao.equals(localizacaoPonto);
    }

    /**
     * Encontra e retorna os velocípedes ativos que estão fora dos pontos válidos de localização.
     * Percorre todos os velocípedes ativos e verifica se a sua localização atual está fora do ponto de localização inicial.
     * @return lista de velocípedes que estão fora do ponto válido de localização.
     */
    public List<Velocipede> encontrarForaDosPontosValidos() {
        List<Velocipede> foraDosPontos = new ArrayList<>();

        for (Velocipede velocipede : listarVelocipedesAtivos()) {
            if (isForaDoPontoValido(velocipede)) {
                foraDosPontos.add(velocipede);
            }
        }

        return foraDosPontos;
    }

    /**
     * Lista todos os velocípedes presentes na frota.
     * @return lista de todos os velocípedes na frota.
     */
    public List<Velocipede> listarVelocipedes() {
        return frota;
    }

    /**
     * Carrega a bateria de um velocípede específico.
     * @param id o ID do velocípede a ser carregado.
     * @return true se a bateria foi carregada com sucesso, false se o velocípede não foi encontrado.
     */
    public boolean carregarBateria(int id) {
        for (Velocipede velocipede : frota) {
            if (velocipede.getId() == id) {
                velocipede.carregarBateria();
                return true;
            }
        }
        return false;
    }

    /**
     * Marca o velocípede como "Alugado".
     * @param id o ID do velocípede a ser marcado como alugado.
     */
    public void alterarEstadoAlugado(int id) {
        for (Velocipede velocipede : frota) {
            if (velocipede.getId() == id) {
                velocipede.setEstado("Alugado");
                return;
            }
        }
    }

    /**
     * Marca o velocípede como "Disponível".
     * @param id o ID do velocípede a ser marcado como disponível.
     */
    public void alterarEstadoDisponivel(int id) {
        for (Velocipede velocipede : frota) {
            if (velocipede.getId() == id) {
                velocipede.setEstado("Disponível");
                return;
            }
        }
    }

    /**
     * Obtém a localização atual de um velocípede pelo seu ID.
     * @param velocipedeId ID do velocípede cuja localização atual se deseja obter.
     * @return a localização atual do velocípede, ou null se o velocípede não for encontrado.
     */
    public String obterLocalizacaoAtual(int velocipedeId) {
        for (Velocipede velocipede : frota) {
            if (velocipede.getId() == velocipedeId) {
                return velocipede.getLocalizacao();
            }
        }
        return null;
    }

    /**
     * Carrega uma lista de velocípedes no sistema, atribuindo um ID único a cada um.
     * @param velocipedes lista de velocípedes a serem carregados no sistema.
     */
    public void carregarVelocipedes(List<Velocipede> velocipedes) {
        for (Velocipede velocipede : velocipedes) {
            velocipede.setId(velocipedeIdCounter++);
            this.frota.add(velocipede);
        }
    }
}
