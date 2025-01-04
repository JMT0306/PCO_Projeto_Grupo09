package view;

import controller.VelocipedeController;
import model.Velocipede;

import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private final VelocipedeController velocipedeController;
    private final Scanner scanner;

    public ClienteView(VelocipedeController velocipedeController) {
        this.velocipedeController = velocipedeController;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarClienteMenu() {
        int option;
        do {
            System.out.println("\n--- Menu do Cliente ---");
            System.out.println("1. Alugar Velocípede");
            System.out.println("2. Efetuar Percurso");
            System.out.println("3. Devolver Velocípede");
            System.out.println("4. Terminar Sessão");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (option) {
                case 1 -> alugarVelocipede();
                //case 2 -> efetuarPercurso();
                case 3 -> devolverVelocipede();
                case 4 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (true);
    }

    private void alugarVelocipede() {
        System.out.println("\n--- Alugar Velocípede ---");
        List<Velocipede> velocipedesDisponiveis = velocipedeController.listarVelocipedesAtivos();

        if (velocipedesDisponiveis.isEmpty()) {
            System.out.println("Não há velocípedes disponíveis para aluguer.");
            return;
        }

        System.out.println("Velocípedes disponíveis:");
        for (Velocipede v : velocipedesDisponiveis) {
            System.out.println("ID: " + v.getId() + " - Tipo: " + v.getTipo() + " - Bateria: " + v.getBateria() + "%");
        }

        System.out.print("\nEscolha o ID do velocípede que deseja alugar: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Simula o aluguer do velocípede
        String resultado = velocipedeController.removerVelocipede(id);
        System.out.println(resultado);
    }
// tem de se ver ainda a logica para isso
//    private void efetuarPercurso() {
//        System.out.println("\n--- Efetuar Percurso ---");
//
//        System.out.print("Informe o ID do velocípede alugado: ");
//        int id = Integer.parseInt(scanner.nextLine());
//
//        Velocipede velocipede = velocipedeController.obterVelocipedePorId(id);
//        if (velocipede == null || !velocipede.getEstado().equalsIgnoreCase("Alugado")) {
//            System.out.println("Erro: Velocípede não encontrado ou não está alugado.");
//            return;
//        }
//
//        System.out.println("Percurso iniciado...");
//        List<String> coordenadasPercorridas = new ArrayList<>();
//        double latAtual = 37.7412; // Exemplo: Coordenadas iniciais
//        double lonAtual = -25.6756;
//        coordenadasPercorridas.add(latAtual + "," + lonAtual);
//
//        String continuar = "s";
//        while (continuar.equalsIgnoreCase("s")) {
//            try {
//                Thread.sleep(2000); // Simula a espera de 2 segundos entre cada ponto
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                System.out.println("Erro na simulação do percurso.");
//                return;
//            }
//
//            double[] novaCoordenada = gerarNovaCoordenada(latAtual, lonAtual);
//            latAtual = novaCoordenada[0];
//            lonAtual = novaCoordenada[1];
//            coordenadasPercorridas.add(latAtual + "," + lonAtual);
//
//            System.out.printf("Nova coordenada: %.6f, %.6f%n", latAtual, lonAtual);
//            System.out.print("Pretende continuar o percurso? (s/n): ");
//            continuar = scanner.nextLine();
//        }
//
//        // Finalizar percurso
//        double precoTotal = calcularPreco(coordenadasPercorridas.size());
//        System.out.printf("Percurso concluído. Total de pontos percorridos: %d. Preço total: %.2f EUR%n",
//                coordenadasPercorridas.size(), precoTotal);
//
//
//        System.out.println("Coordenadas do percurso:");
//        for (String coord : coordenadasPercorridas) {
//            System.out.println(coord);
//        }
//    }
    private double[] gerarNovaCoordenada(double lat, double lon) {
        double raio = 0.001; // Aproximadamente 100 metros
        double angulo = Math.random() * 2 * Math.PI;

        double novoLat = lat + raio * Math.cos(angulo);
        double novoLon = lon + raio * Math.sin(angulo) / Math.cos(Math.toRadians(lat));

        return new double[]{novoLat, novoLon};
    }

    private double calcularPreco(int pontosPercorridos) {
        final double precoPorPonto = 0.5; // Preço de 0.5 EUR por ponto percorrido
        return pontosPercorridos * precoPorPonto;
    }

    private void devolverVelocipede() {
        System.out.println("\n--- Devolver Velocípede ---");

        System.out.print("Informe o ID do velocípede devolvido: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Latitude: ");
        double latitude = Double.parseDouble(scanner.nextLine());

        System.out.print("Longitude: ");
        double longitude = Double.parseDouble(scanner.nextLine());

        String novaLocalizacao = latitude + "," + longitude;

        // exemplo de coordenadas de origem
        double latOrigem = 37.7412;  // Ponta Delgada
        double lonOrigem = -25.6756;

        // Cálculo da distância
        double distancia = calcularDistancia(latOrigem, lonOrigem, latitude, longitude);

        if (distancia > 1.0) { // Limite de 1 km
            System.out.println("Erro: O ponto de devolução está fora do raio permitido de 1 km.");
            return;
        }

        boolean sucesso = velocipedeController.atualizarLocalizacaoVelocipede(id, novaLocalizacao);
        if (sucesso) {
            System.out.println("Velocípede devolvido e localização atualizada com sucesso.");
        } else {
            System.out.println("Erro ao devolver velocípede. Verifique os dados.");
        }
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // Raio da Terra em km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Return da distância em km
    }

}
