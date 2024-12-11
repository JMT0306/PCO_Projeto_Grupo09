package utils;

import java.util.Random;

public class GeoUtils {
    private static final double EARTH_RADIUS_KM = 6371.0; // Raio da Terra em quilômetros

    /**
     * Calcula a distância entre duas coordenadas geográficas.
     * Utiliza a fórmula de Haversine.
     *
     * @param lat1 Latitude do ponto 1.
     * @param lon1 Longitude do ponto 1.
     * @param lat2 Latitude do ponto 2.
     * @param lon2 Longitude do ponto 2.
     * @return Distância entre os dois pontos em quilômetros.
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    /**
     * Gera coordenadas geográficas aleatórias próximas a um ponto de referência.
     *
     * @param lat Latitude do ponto de referência.
     * @param lon Longitude do ponto de referência.
     * @param maxDistanceKm Distância máxima em quilômetros para gerar as novas coordenadas.
     * @return Array de double com latitude [0] e longitude [1].
     */
    public static double[] generateNearbyCoordinates(double lat, double lon, double maxDistanceKm) {
        Random random = new Random();

        // Converte a distância máxima para graus
        double maxDistanceDegrees = maxDistanceKm / EARTH_RADIUS_KM * (180 / Math.PI);

        // Gera um deslocamento aleatório em latitude e longitude
        double deltaLat = (random.nextDouble() - 0.5) * 2 * maxDistanceDegrees;
        double deltaLon = (random.nextDouble() - 0.5) * 2 * maxDistanceDegrees
                / Math.cos(Math.toRadians(lat));

        double newLat = lat + deltaLat;
        double newLon = lon + deltaLon;

        return new double[]{newLat, newLon};
    }

    /**
     * Verifica se duas coordenadas estão dentro de uma distância máxima.
     *
     * @param lat1 Latitude do ponto 1.
     * @param lon1 Longitude do ponto 1.
     * @param lat2 Latitude do ponto 2.
     * @param lon2 Longitude do ponto 2.
     * @param maxDistanceKm Distância máxima em quilômetros.
     * @return true se os pontos estiverem dentro da distância máxima, false caso contrário.
     */
    public static boolean isWithinDistance(double lat1, double lon1, double lat2, double lon2, double maxDistanceKm) {
        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        return distance <= maxDistanceKm;
    }
}
