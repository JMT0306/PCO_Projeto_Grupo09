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

    /**
     * Inicia um novo percurso a partir da localização dada, atualizando as suas coordenadas de forma automática.
     * As coordenadas são atualizadas a cada 2 segundos, aumentando um pouco a latitude e a longitude.
     * Quando o cliente parar o percurso, escrevendo "p", o percurso para e todas as coordenadas registadas são mostradas ao mesmo.
     * @param localizacaoAtual localização inicial do percurso, no formato "latitude,longitude".
     */
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

        System.out.println("\nTodas as coordenadas do percurso:");
        percurso.getCoordenadas().forEach(System.out::println);
    }
}
