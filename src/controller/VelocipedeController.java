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

    // Método para adicionar um novo velocípede à frota
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

    // Método para atualizar a localização de um velocípede
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

    // Método para remover um velocípede existente da frota
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

    // Método para listar os velocípedes ativos
    public List<Velocipede> listarVelocipedesAtivos() {
        List<Velocipede> velocipedesAtivos = new ArrayList<>();
        for (Velocipede velocipede : frota) {
            if (velocipede.getEstado().equalsIgnoreCase("Disponível")) {
                velocipedesAtivos.add(velocipede);
            }
        }
        return velocipedesAtivos;
    }

    // Método para encontrar os pontos de maior e menor concentração
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

    // Método para contar o número de velocípedes na mesma localização
    private int contarVelocipedesPorLocalizacao(String localizacao) {
        int contador = 0;

        for (Velocipede velocipede : frota) {
            if (velocipede.getEstado().equalsIgnoreCase("Disponível") && velocipede.getLocalizacao().equals(localizacao)) {
                contador++;
            }
        }

        return contador;
    }

    // Método que verifica se o velocípede está fora do ponto válido
    private boolean isForaDoPontoValido(Velocipede velocipede) {
        String localizacao = velocipede.getLocalizacao();
        String localizacaoPonto = velocipede.getLocalizacaoPonto();

        return !localizacao.equals(localizacaoPonto);
    }

    // Método para encontrar e retornar os velocípedes ativos que estão fora dos pontos válidos de localização
    public List<Velocipede> encontrarForaDosPontosValidos() {
        List<Velocipede> foraDosPontos = new ArrayList<>();

        for (Velocipede velocipede : listarVelocipedesAtivos()) {
            if (isForaDoPontoValido(velocipede)) {
                foraDosPontos.add(velocipede);
            }
        }

        return foraDosPontos;
    }

    // Método para listar todos os velocípedes na frota
    public List<Velocipede> listarVelocipedes() {
        return frota;
    }

    // Método para carregar os utilizadores na aplicação (classe Config)
    public void carregarVelocipedes(List<Velocipede> velocipedes) {
        for (Velocipede velocipede : velocipedes) {
            velocipede.setId(velocipedeIdCounter++);
            this.frota.add(velocipede);
        }
    }

    // Método para carregar a bateria de um velocípede específico
    public boolean carregarBateria(int id) {
        for (Velocipede velocipede : frota) {
            if (velocipede.getId() == id) {
                velocipede.carregarBateria();
                return true;
            }
        }
        return false;
    }

    // Marca o velocípede como alugado
    public void alterarEstadoAlugado(int id) {
        for (Velocipede velocipede : frota) {
            if (velocipede.getId() == id) {
                velocipede.setEstado("Alugado");
                return;
            }
        }
    }
}
