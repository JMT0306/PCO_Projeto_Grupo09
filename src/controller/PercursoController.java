package controller;
import model.Percurso;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PercursoController {
    private final List<Percurso> percursos;

    public PercursoController() {
        this.percursos = new ArrayList<>();
    }

    // Método para iniciar o percurso
    public void iniciarPercurso(String localizacaoAtual) {
        Percurso percurso = new Percurso(localizacaoAtual);
        percurso.setAtivo(true);
        percursos.add(percurso);

        new Thread(() -> {
            String localizacao = localizacaoAtual;
            while (percurso.isAtivo()) {
                String[] coords = localizacao.split(",");
                double latitude = Double.parseDouble(coords[0]) + 0.01;
                double longitude = Double.parseDouble(coords[1]) + 0.01;

                localizacao = latitude + "," + longitude;
                percurso.getCoordenadas().add(localizacao);
                System.out.println("\nNovas coordenadas: " + localizacao);
                System.out.println("Escreva 'p' para parar o percurso");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (percurso.isAtivo()) {
            if ("p".equalsIgnoreCase(scanner.nextLine())) {
                percurso.setAtivo(false);
                System.out.println("Percurso interrompido.");
            }
        }

        System.out.println("Todas as coordenadas do percurso:");
        percurso.getCoordenadas().forEach(System.out::println);
    }

    // Método para adicionar coordenada ao percurso
    public void adicionarCoordenada(Percurso percurso, double latitude, double longitude) {
        String coordenada = latitude + "," + longitude;
        percurso.getCoordenadas().add(coordenada);
    }

    // Método para listar todos os percursos
    public List<Percurso> listarPercursos() {
        return percursos;
    }
}


