package controller;
import model.Velocipede;
import java.util.ArrayList;
import java.util.List;

public class VelocipedeController {
    private static int velocipedeIdCounter = 1;
    private final List<Velocipede> velocipedes;

    public VelocipedeController() {
        this.velocipedes = new ArrayList<>();
    }

    // Método para adicionar um novo velocípede
    public boolean adicionarVelocipede(String tipo, String estado, int bateria, String localizacao) {
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

        Velocipede velocipede = new Velocipede(tipo, estado, bateria, localizacao);
        velocipede.setId(velocipedeIdCounter++);
        velocipedes.add(velocipede);

        return true;
    }

    public String removerVelocipede(int id) {
        for (int i = 0; i < velocipedes.size(); i++) {
            Velocipede velocipede = velocipedes.get(i);
            if (velocipede.getId() == id) {
                if (velocipede.getEstado().equalsIgnoreCase("Disponível")) {
                    velocipedes.remove(i);
                    return "Velocípede removido com sucesso.";
                } else {
                    return "Velocípede em uso, não pode ser removido.";
                }
            }
        }
        return "Velocípede não encontrado.";
    }

    public List<Velocipede> listarVelocipedesAtivos() {
        List<Velocipede> velocipedesAtivos = new ArrayList<>();
        for (Velocipede velocipede : velocipedes) {
            if (velocipede.getEstado().equalsIgnoreCase("Disponível")) {
                velocipedesAtivos.add(velocipede);
            }
        }
        return velocipedesAtivos;
    }

    public boolean atualizarLocalizacaoVelocipede(int id, String novaLocalizacao) {
        for (Velocipede velocipede : velocipedes) {
            if (velocipede.getId() == id && velocipede.getEstado().equalsIgnoreCase("Disponível")) {
                velocipede.setLocalizacao(novaLocalizacao);
                return true;
            }
        }
        return false;
    }



    // Método para listar todos os velocípedes
    public List<Velocipede> listarVelocipedes() {
        return velocipedes;
    }
}





/*
    // Método para listar veículos disponíveis
    public List<Velocipede> listAvailableVehicles() {
        List<Velocipede> availableVelocipedes = new ArrayList<>();
        for (Velocipede v : velocipedes) {
            if (v.getStatus().equals("Disponível")) {
                availableVelocipedes.add(v);
            }
        }
        return availableVelocipedes;
    }

    // Método para monitorizar o estado dos veículos
    public void monitorVehicles() {
        System.out.println("Estado atual dos veículos:");
        for (Velocipede v : velocipedes) {
            System.out.println("ID: " + v.getId() + ", Tipo: " + v.getType() + ", Bateria: " + v.getBatteryLevel() + "%, Estado: " + v.getStatus());
        }
    }

    // Método para distribuir veículos para novas localizações
    public boolean distributeVehicles(String id, String newLocation) {
        for (Velocipede v : velocipedes) {
            if (v.getId().equals(id)) {
                v.setLocation(newLocation);
                System.out.println("Veículo " + id + " distribuído para a localização: " + newLocation);
                return true;
            }
        }
        System.out.println("Erro: Veículo com ID " + id + " não encontrado.");
        return false;
    }
     */
